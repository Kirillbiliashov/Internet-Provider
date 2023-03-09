package ua.kirillbiliashov.internetprovider.dto;

public class PostTariffDTO {

  private String name;
  private double price;
  private int duration;
  private ServiceDTO service;

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

  public ServiceDTO getService() {
    return service;
  }

  public PostTariffDTO setService(ServiceDTO service) {
    this.service = service;
    return this;
  }
}
