package ua.kirillbiliashov.internetprovider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.repository.ServiceRepository;
import ua.kirillbiliashov.internetprovider.repository.TariffRepository;
import ua.kirillbiliashov.internetprovider.service.TariffService;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TariffServiceImpl implements TariffService {

  private final TariffRepository tariffRepository;
  private final ServiceRepository serviceRepository;

  @Autowired
  public TariffServiceImpl(TariffRepository tariffRepository,
                           ServiceRepository serviceRepository) {
    this.tariffRepository = tariffRepository;
    this.serviceRepository = serviceRepository;
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

  @Override
  @Transactional
  public boolean update(int id, Tariff newTariff) {
    Optional<Tariff> optTariff = tariffRepository.findById(id);
    if (optTariff.isEmpty()) return false;
    Tariff tariff = optTariff.get();
    int newServiceId = newTariff.getService().getId();
    tariff.setName(newTariff.getName())
        .setPrice(newTariff.getPrice())
        .setDuration(newTariff.getDuration())
        .setService(serviceRepository.getReferenceById(newServiceId));
    return true;
  }

}
