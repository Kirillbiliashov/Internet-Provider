package ua.kirillbiliashov.internetprovider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kirillbiliashov.internetprovider.domain.Person;
import ua.kirillbiliashov.internetprovider.domain.Role;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.repository.PersonRepository;
import ua.kirillbiliashov.internetprovider.repository.TariffRepository;
import ua.kirillbiliashov.internetprovider.service.PersonService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;
  private final TariffRepository tariffRepository;

  @Autowired
  public PersonServiceImpl(PersonRepository personRepository,
                           TariffRepository tariffRepository) {
    this.personRepository = personRepository;
    this.tariffRepository = tariffRepository;
  }

  @Override
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @Override
  public List<Person> findBlocked() {
    return personRepository.findByIsBlockedTrue();
  }

  @Override
  public Optional<Person> find(int id) {
    return personRepository.findById(id);
  }

  @Override
  @Transactional
  public void register(Person person) {
    person.getAccount().setRole(Role.ROLE_SUBSCRIBER);
    personRepository.save(person);
  }

  @Override
  @Transactional
  public boolean changeBlock(int id, boolean isBlocked) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return false;
    Person person = optPerson.get();
    person.setBlocked(isBlocked);
    return true;
  }

  @Override
  @Transactional
  public boolean replenishBalance(int id, int sum) {
    Optional<Person> optPerson = personRepository.findById(id);
    if (optPerson.isEmpty()) return false;
    Person person = optPerson.get();
    person.setBalance(person.getBalance() + sum);
    return true;
  }

  @Override
  @Transactional
  public boolean subscribe(int personId, int tariffId) {
    Optional<Tariff> optTariff = tariffRepository.findById(tariffId);
    Optional<Person> optPerson = personRepository.findById(personId);
    if (optTariff.isEmpty() || optPerson.isEmpty()) return false;
    Person person = optPerson.get();
    Tariff tariff = optTariff.get();
    person.getTariffs().add(tariff);
    return true;
  }

}
