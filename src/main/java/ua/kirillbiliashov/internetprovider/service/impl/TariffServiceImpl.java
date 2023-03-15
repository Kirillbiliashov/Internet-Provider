package ua.kirillbiliashov.internetprovider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.repository.TariffRepository;
import ua.kirillbiliashov.internetprovider.service.TariffService;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TariffServiceImpl implements TariffService {

  private final TariffRepository tariffRepository;

  @Autowired
  public TariffServiceImpl(TariffRepository tariffRepository) {
    this.tariffRepository = tariffRepository;
  }

  @Override
  public Optional<Tariff> find(int id) {
    return tariffRepository.findById(id);
  }

  @Override
  @Transactional
  public void delete(int id) {
    tariffRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void save(Tariff tariff) {
    tariffRepository.save(tariff);
  }

}
