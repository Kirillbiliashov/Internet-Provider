package ua.kirillbiliashov.internetprovider.dto;

public class GetTariffDTO {

  private int id;
  private String name;
  private double price;
  private int duration;

  public int getId() {
    return id;
  }

  public GetTariffDTO setId(int id) {
    this.id = id;
    return this;
  }

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
