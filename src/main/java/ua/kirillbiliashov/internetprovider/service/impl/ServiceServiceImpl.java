package ua.kirillbiliashov.internetprovider.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.kirillbiliashov.internetprovider.domain.Service;
import ua.kirillbiliashov.internetprovider.repository.ServiceRepository;
import ua.kirillbiliashov.internetprovider.service.ServiceService;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class ServiceServiceImpl implements ServiceService {

  private final ServiceRepository serviceRepository;

  public ServiceServiceImpl(ServiceRepository serviceRepository) {
    this.serviceRepository = serviceRepository;
  }


  @Override
  public List<Service> findAll() {
    return serviceRepository.findAll();
  }

  @Override
  public Optional<Service> find(int id) {
    return serviceRepository.findById(id);
  }

}
