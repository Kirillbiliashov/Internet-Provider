package ua.kirillbiliashov.internetprovider.dto;

public class SubscriberDTO {

  private String email;
  private String password;
  private String firstName;
  private String lastName;

  public String getEmail() {
    return email;
  }

  public SubscriberDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public SubscriberDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public SubscriberDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public SubscriberDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }
}