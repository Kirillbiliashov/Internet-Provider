package ua.kirillbiliashov.internetprovider.dto;

public class PostSubscriberDTO {

  private String firstName;
  private String lastName;
  private AccountDTO account;

  public String getFirstName() {
    return firstName;
  }

  public PostSubscriberDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public PostSubscriberDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public AccountDTO getAccount() {
    return account;
  }

  public PostSubscriberDTO setAccount(AccountDTO account) {
    this.account = account;
    return this;
  }

}
