package ua.kirillbiliashov.internetprovider.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tariff")
public class Tariff extends AbstractEntity {

  private String name;
  private double price;
  private int duration;

  @ManyToOne
  @JoinColumn(name = "service_id", referencedColumnName = "id")
  private Service service;

  @ManyToMany


  public String getName() {
    return name;
  }

  public Tariff setName(String name) {
    this.name = name;
    return this;
  }

  public double getPrice() {
    return price;
  }

  public Tariff setPrice(double price) {
    this.price = price;
    return this;
  }

  public int getDuration() {
    return duration;
  }

  public Tariff setDuration(int duration) {
    this.duration = duration;
    return this;
  }

  public Service getService() {
    return service;
  }

  public Tariff setService(Service service) {
    this.service = service;
    return this;
  }

}
