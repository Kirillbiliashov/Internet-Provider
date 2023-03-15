package ua.kirillbiliashov.internetprovider.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person extends AbstractEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "is_blocked")
  private boolean isBlocked;

  private int balance;

  @ManyToMany
  @JoinTable(name = "person_tariff", joinColumns = @JoinColumn(name = "person_id"),
      inverseJoinColumns = @JoinColumn(name = "tariff_id"))
  private List<Tariff> tariffs;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;

  public String getFirstName() {
    return firstName;
  }

  public Person setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Person setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public Person setBlocked(boolean blocked) {
    isBlocked = blocked;
    return this;
  }

  public int getBalance() {
    return balance;
  }

  public Person setBalance(int balance) {
    this.balance = balance;
    return this;
  }

  public Account getAccount() {
    return account;
  }

  public Person setAccount(Account account) {
    this.account = account;
    return this;
  }

  public List<Tariff> getTariffs() {
    return tariffs;
  }

  public Person setTariffs(List<Tariff> tariffs) {
    this.tariffs = tariffs;
    return this;
  }
}
