package ua.kirillbiliashov.internetprovider.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "service")
public class Service extends AbstractEntity {

  private String name;
  private String description;

  @OneToMany(mappedBy = "service")
  private List<Tariff> tariffs;

  public String getName() {
    return name;
  }

  public Service setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Service setDescription(String description) {
    this.description = description;
    return this;
  }

  public List<Tariff> getTariffs() {
    return tariffs;
  }

  public Service setTariffs(List<Tariff> tariffs) {
    this.tariffs = tariffs;
    return this;
  }
}
