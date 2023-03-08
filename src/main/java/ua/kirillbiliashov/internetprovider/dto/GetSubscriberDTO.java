package ua.kirillbiliashov.internetprovider.dto;

import java.util.List;

public class GetSubscriberDTO {

  private int id;
  private String firstName;
  private String lastName;
  private int balance;
  private AccountDTO account;
  private List<GetTariffDTO> tariffs;

  public int getId() {
    return id;
  }

  public GetSubscriberDTO setId(int id) {
    this.id = id;
    return this;
  }

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

  public List<GetTariffDTO> getTariffs() {
    return tariffs;
  }

  public GetSubscriberDTO setTariffs(List<GetTariffDTO> tariffs) {
    this.tariffs = tariffs;
    return this;
  }

  public int getBalance() {
    return balance;
  }

  public GetSubscriberDTO setBalance(int balance) {
    this.balance = balance;
    return this;
  }

}
