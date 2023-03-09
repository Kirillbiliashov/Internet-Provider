package ua.kirillbiliashov.internetprovider.dto;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Relation(collectionRelation = "services")
public class ServiceDTO extends RepresentationModel<ServiceDTO> {

  private String name;
  private String description;
  private CollectionModel<GetTariffDTO> tariffs;

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

  public CollectionModel<GetTariffDTO> getTariffs() {
    return tariffs;
  }

  public ServiceDTO setTariffs(CollectionModel<GetTariffDTO> tariffs) {
    this.tariffs = tariffs;
    return this;
  }

}
