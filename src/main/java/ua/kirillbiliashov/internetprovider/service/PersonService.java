package ua.kirillbiliashov.internetprovider.service;

import ua.kirillbiliashov.internetprovider.domain.Person;
import ua.kirillbiliashov.internetprovider.domain.Tariff;

import java.util.List;
import java.util.Optional;

public interface PersonService {
  List<Person> findAll();

  List<Person> findBlocked();

  Optional<Person> find(int id);

  void register(Person person);

  boolean changeBlock(int id, boolean isBlocked);

  boolean replenishBalance(int id, int sum);

  boolean subscribe(int personId, int tariffId);
}
