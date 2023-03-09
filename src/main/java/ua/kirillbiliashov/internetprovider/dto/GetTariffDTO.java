package ua.kirillbiliashov.internetprovider.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "tariffs")
public class GetTariffDTO extends RepresentationModel<GetTariffDTO> {

  private String name;
  private double price;
  private int duration;

  public String getName() {
    return name;
  }

  public GetTariffDTO setName(String name) {
    this.name = name;
    return this;
  }

  public double getPrice() {
    return price;
  }

  public GetTariffDTO setPrice(double price) {
    this.price = price;
    return this;
  }

  public int getDuration() {
    return duration;
  }

  public GetTariffDTO setDuration(int duration) {
    this.duration = duration;
    return this;
  }
}
