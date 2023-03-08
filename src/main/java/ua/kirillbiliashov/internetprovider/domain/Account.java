package ua.kirillbiliashov.internetprovider.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;

  public String getEmail() {
    return email;
  }

  public Account setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Account setPassword(String password) {
    this.password = password;
    return this;
  }

  public Role getRole() {
    return role;
  }

  public Account setRole(Role role) {
    this.role = role;
    return this;
  }

}
