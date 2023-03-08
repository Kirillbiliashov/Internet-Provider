package ua.kirillbiliashov.internetprovider.controller;

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

  @Autowired
  public SubscribersController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping
  public List<GetSubscriberDTO> subscribers() {
    List<Person> subscribers = personRepository.findAll();
    return subscribers
        .stream()
        .map(subscriber -> new GetSubscriberDTO()
            .setFirstName(subscriber.getFirstName())
            .setLastName(subscriber.getLastName())
            .setBalance(subscriber.getBalance())
            .setAccount(mapToAccountDTO(subscriber.getAccount()))
            .setTariffs(getTariffDTOList(subscriber.getTariffs())))
        .toList();
  }

  private List<GetTariffDTO> getTariffDTOList(List<Tariff> tariffs) {
    return tariffs.stream().map(tariff -> new GetTariffDTO()
        .setId(tariff.getId())
        .setName(tariff.getName())
        .setDuration(tariff.getDuration())
        .setPrice(tariff.getPrice())).toList();
  }

  private AccountDTO mapToAccountDTO(Account account) {
    return new AccountDTO()
        .setEmail(account.getEmail())
        .setPassword(account.getPassword());
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetSubscriberDTO> subscriber(@PathVariable int id) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Person person = optPerson.get();
    Account account = person.getAccount();
    GetSubscriberDTO subscriberDTO = new GetSubscriberDTO()
        .setFirstName(person.getFirstName())
        .setLastName(person.getLastName())
        .setBalance(person.getBalance())
        .setAccount(new AccountDTO()
            .setEmail(account.getEmail())
            .setPassword(account.getPassword()))
        .setTariffs(getTariffDTOList(person.getTariffs()));
    return new ResponseEntity<>(subscriberDTO, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> register(@RequestBody PostSubscriberDTO postSubscriberDTO) {
    Account account = new Account();
    account.setEmail(postSubscriberDTO.getEmail());
    account.setPassword(postSubscriberDTO.getPassword());
    account.setRole(Role.ROLE_ADMIN);
    Person person = new Person();
    person.setFirstName(postSubscriberDTO.getFirstName());
    person.setLastName(postSubscriberDTO.getLastName());
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
