package ua.kirillbiliashov.internetprovider.service;

import ua.kirillbiliashov.internetprovider.domain.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
  List<Service> findAll();

  Optional<Service> find(int id);
}
