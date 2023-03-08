package ua.kirillbiliashov.internetprovider.dto;

import java.util.List;

public class ServiceDTO {

  private int id;
  private String name;
  private String description;
  private List<GetTariffDTO> tariffs;

  public int getId() {
    return id;
  }

  public ServiceDTO setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ServiceDTO setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ServiceDTO setDescription(String description) {
    this.description = description;
    return this;
  }

  public List<GetTariffDTO> getTariffs() {
    return tariffs;
  }

  public ServiceDTO setTariffs(List<GetTariffDTO> tariffs) {
    this.tariffs = tariffs;
    return this;
  }
}
