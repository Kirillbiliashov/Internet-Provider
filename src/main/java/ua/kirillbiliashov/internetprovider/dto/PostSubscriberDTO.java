package ua.kirillbiliashov.internetprovider.dto;

public class PostSubscriberDTO {

  private String email;
  private String password;
  private String firstName;
  private String lastName;

  public String getEmail() {
    return email;
  }

  public PostSubscriberDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public PostSubscriberDTO setPassword(String password) {
    this.password = password;
    return this;
  }

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
}
