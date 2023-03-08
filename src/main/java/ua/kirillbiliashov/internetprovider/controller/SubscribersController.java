package ua.kirillbiliashov.internetprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kirillbiliashov.internetprovider.domain.Account;
import ua.kirillbiliashov.internetprovider.domain.Person;
import ua.kirillbiliashov.internetprovider.domain.Role;
import ua.kirillbiliashov.internetprovider.dto.SubscriberDTO;
import ua.kirillbiliashov.internetprovider.repository.AccountRepository;
import ua.kirillbiliashov.internetprovider.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscribers")
public class SubscribersController {

  private final PersonRepository personRepository;

  @Autowired
  public SubscribersController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping
  public List<Person> subscribers() {
    return personRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> subscriber(@PathVariable int id) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(optPerson.get(), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> register(@RequestBody SubscriberDTO subscriberDTO) {
    Account account = new Account();
    account.setEmail(subscriberDTO.getEmail());
    account.setPassword(subscriberDTO.getPassword());
    account.setRole(Role.ROLE_ADMIN);
    Person person = new Person();
    person.setFirstName(subscriberDTO.getFirstName());
    person.setLastName(subscriberDTO.getLastName());
    person.setAccount(account);
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
