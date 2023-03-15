package ua.kirillbiliashov.internetprovider.service;

import ua.kirillbiliashov.internetprovider.domain.Tariff;

import java.util.Optional;

public interface TariffService {
  Optional<Tariff> find(int id);
  void delete(int id);
  void save(Tariff tariff);
}
