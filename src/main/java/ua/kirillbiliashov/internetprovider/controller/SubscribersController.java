package ua.kirillbiliashov.internetprovider.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kirillbiliashov.internetprovider.domain.Account;
import ua.kirillbiliashov.internetprovider.domain.Person;
import ua.kirillbiliashov.internetprovider.domain.Role;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.dto.AccountDTO;
import ua.kirillbiliashov.internetprovider.dto.GetSubscriberDTO;
import ua.kirillbiliashov.internetprovider.dto.GetTariffDTO;
import ua.kirillbiliashov.internetprovider.dto.PostSubscriberDTO;
import ua.kirillbiliashov.internetprovider.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscribers")
public class SubscribersController {

  private final PersonRepository personRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public SubscribersController(PersonRepository personRepository,
                               ModelMapper modelMapper) {
    this.personRepository = personRepository;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public List<GetSubscriberDTO> subscribers() {
    List<Person> subscribers = personRepository.findAll();
    return subscribers
        .stream()
        .map(subscriber -> modelMapper.map(subscriber, GetSubscriberDTO.class))
        .toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetSubscriberDTO> subscriber(@PathVariable int id) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Person person = optPerson.get();
    GetSubscriberDTO subscriberDTO = modelMapper.map(person, GetSubscriberDTO.class);
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
