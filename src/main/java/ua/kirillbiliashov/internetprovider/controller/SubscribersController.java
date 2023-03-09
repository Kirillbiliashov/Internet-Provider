package ua.kirillbiliashov.internetprovider.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kirillbiliashov.internetprovider.assemblers.SubscriberDTOAssembler;
import ua.kirillbiliashov.internetprovider.domain.Person;
import ua.kirillbiliashov.internetprovider.domain.Role;
import ua.kirillbiliashov.internetprovider.dto.GetSubscriberDTO;
import ua.kirillbiliashov.internetprovider.dto.PostSubscriberDTO;
import ua.kirillbiliashov.internetprovider.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/subscribers")
public class SubscribersController {

  private final PersonRepository personRepository;
  private final ModelMapper modelMapper;
  private final SubscriberDTOAssembler subscriberDTOAssembler;

  @Autowired
  public SubscribersController(PersonRepository personRepository,
                               ModelMapper modelMapper,
                               SubscriberDTOAssembler subscriberDTOAssembler) {
    this.personRepository = personRepository;
    this.modelMapper = modelMapper;
    this.subscriberDTOAssembler = subscriberDTOAssembler;
  }

  @GetMapping
  public CollectionModel<GetSubscriberDTO> subscribers() {
    List<Person> subscribers = personRepository.findAll();
    CollectionModel<GetSubscriberDTO> collectionModel =
        subscriberDTOAssembler.toCollectionModel(subscribers);
    collectionModel.add(linkTo(methodOn(SubscribersController.class).blockedSubscribers())
        .withRel("blocked subscribers"));
    return collectionModel;
  }

  @GetMapping("/blocked")
  public CollectionModel<GetSubscriberDTO> blockedSubscribers() {
    List<Person> subscribers = personRepository.findByIsBlockedTrue();
    CollectionModel<GetSubscriberDTO> collectionModel =
        subscriberDTOAssembler.toCollectionModel(subscribers);
    collectionModel.add(linkTo(methodOn(SubscribersController.class).subscribers())
        .withRel("subscribers"));
    return collectionModel;
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetSubscriberDTO> subscriber(@PathVariable int id) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    GetSubscriberDTO subscriberDTO = subscriberDTOAssembler.toModel(optPerson.get());
    return new ResponseEntity<>(subscriberDTO, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> register(@RequestBody PostSubscriberDTO postSubscriberDTO) {
    Person person = modelMapper.map(postSubscriberDTO, Person.class);
    person.getAccount().setRole(Role.ROLE_SUBSCRIBER);
    personRepository.save(person);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PatchMapping("/block/{id}")
  public ResponseEntity<HttpStatus> blockSubscriber(@PathVariable int id) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Person person = optPerson.get();
    person.setBlocked(true);
    personRepository.save(person);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PatchMapping("/unlock/{id}")
  public ResponseEntity<HttpStatus> unlockSubscriber(@PathVariable int id) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Person person = optPerson.get();
    person.setBlocked(false);
    personRepository.save(person);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PatchMapping("/replenish/{id}")
  public ResponseEntity<HttpStatus> replenishBalance(@PathVariable int id,
      @RequestParam("replenishSum") int sum) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Person person = optPerson.get();
    person.setBalance(person.getBalance() + sum);
    personRepository.save(person);
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
