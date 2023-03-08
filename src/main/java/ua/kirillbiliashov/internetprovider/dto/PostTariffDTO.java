package ua.kirillbiliashov.internetprovider.dto;

public class PostTariffDTO {

  private String name;
  private double price;
  private int duration;
  private int serviceId;

  public String getName() {
    return name;
  }

  public PostTariffDTO setName(String name) {
    this.name = name;
    return this;
  }

  public double getPrice() {
    return price;
  }

  public PostTariffDTO setPrice(double price) {
    this.price = price;
    return this;
  }

  public int getDuration() {
    return duration;
  }

  public PostTariffDTO setDuration(int duration) {
    this.duration = duration;
    return this;
  }

  public int getServiceId() {
    return serviceId;
  }

  public PostTariffDTO setServiceId(int serviceId) {
    this.serviceId = serviceId;
    return this;
  }

}
