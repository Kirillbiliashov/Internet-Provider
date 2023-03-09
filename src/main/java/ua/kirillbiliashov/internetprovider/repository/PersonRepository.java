package ua.kirillbiliashov.internetprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kirillbiliashov.internetprovider.domain.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
  List<Person> findByIsBlockedTrue();
}
