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
import ua.kirillbiliashov.internetprovider.service.PersonService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/subscribers")
public class SubscribersController {

  private final SubscriberDTOAssembler subscriberDTOAssembler;
  private final PersonService personService;

  @Autowired
  public SubscribersController(SubscriberDTOAssembler subscriberDTOAssembler,
                               PersonService personService) {
    this.subscriberDTOAssembler = subscriberDTOAssembler;
    this.personService = personService;
  }

  @GetMapping
  public CollectionModel<GetSubscriberDTO> subscribers() {
    List<Person> subscribers = personService.findAll();
    CollectionModel<GetSubscriberDTO> collectionModel =
        subscriberDTOAssembler.toCollectionModel(subscribers);
    collectionModel.add(linkTo(methodOn(SubscribersController.class).blockedSubscribers())
        .withRel("blocked subscribers"));
    return collectionModel;
  }

  @GetMapping("/blocked")
  public CollectionModel<GetSubscriberDTO> blockedSubscribers() {
    List<Person> subscribers = personService.findBlocked();
    CollectionModel<GetSubscriberDTO> collectionModel =
        subscriberDTOAssembler.toCollectionModel(subscribers);
    collectionModel.add(linkTo(methodOn(SubscribersController.class).subscribers())
        .withRel("subscribers"));
    return collectionModel;
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetSubscriberDTO> subscriber(@PathVariable int id) {
    Optional<Person> optPerson = personService.find(id);
    if (optPerson.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    GetSubscriberDTO subscriberDTO = subscriberDTOAssembler.toModel(optPerson.get());
    return new ResponseEntity<>(subscriberDTO, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> register(@RequestBody Person person) {
    personService.register(person);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PatchMapping("/block/{id}")
  public ResponseEntity<HttpStatus> blockSubscriber(@PathVariable int id) {
    boolean isChange = personService.changeBlock(id, true);
    return isChange ? ResponseEntity.ok(HttpStatus.OK) :
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PatchMapping("/unlock/{id}")
  public ResponseEntity<HttpStatus> unlockSubscriber(@PathVariable int id) {
    boolean isChange = personService.changeBlock(id, false);
    return isChange ? ResponseEntity.ok(HttpStatus.OK) :
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PatchMapping("/replenish/{id}")
  public ResponseEntity<HttpStatus> replenishBalance(@PathVariable int id,
      @RequestParam("replenishSum") int sum) {
    boolean isChange = personService.replenishBalance(id, sum);
    return isChange ? ResponseEntity.ok(HttpStatus.OK) :
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
