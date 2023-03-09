package ua.kirillbiliashov.internetprovider.dto;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Relation(collectionRelation = "subscribers")
public class GetSubscriberDTO extends RepresentationModel<GetSubscriberDTO> {

  private String firstName;
  private String lastName;
  private int balance;
  private boolean isBlocked;
  private AccountDTO account;
  private CollectionModel<GetTariffDTO> tariffs;

  public String getFirstName() {
    return firstName;
  }

  public GetSubscriberDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public GetSubscriberDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public AccountDTO getAccount() {
    return account;
  }

  public GetSubscriberDTO setAccount(AccountDTO account) {
    this.account = account;
    return this;
  }

  public int getBalance() {
    return balance;
  }

  public GetSubscriberDTO setBalance(int balance) {
    this.balance = balance;
    return this;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public GetSubscriberDTO setBlocked(boolean blocked) {
    isBlocked = blocked;
    return this;
  }

  public CollectionModel<GetTariffDTO> getTariffs() {
    return tariffs;
  }

  public GetSubscriberDTO setTariffs(CollectionModel<GetTariffDTO> tariffs) {
    this.tariffs = tariffs;
    return this;
  }
}
