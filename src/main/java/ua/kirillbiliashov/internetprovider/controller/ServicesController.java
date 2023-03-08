package ua.kirillbiliashov.internetprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kirillbiliashov.internetprovider.domain.Service;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.dto.GetTariffDTO;
import ua.kirillbiliashov.internetprovider.dto.ServiceDTO;
import ua.kirillbiliashov.internetprovider.repository.ServiceRepository;
import ua.kirillbiliashov.internetprovider.repository.TariffRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServicesController {

  private final ServiceRepository serviceRepository;

  @Autowired
  public ServicesController(ServiceRepository serviceRepository) {
    this.serviceRepository = serviceRepository;
  }

  @GetMapping
  public List<ServiceDTO> services() {
    List<Service> services = serviceRepository.findAll();
    return services.stream()
        .map(service -> new ServiceDTO()
        .setId(service.getId())
        .setName(service.getName())
        .setDescription(service.getDescription())
        .setTariffs(getTariffDTOList(service.getTariffs()))).toList();
  }

  @GetMapping("/{serviceId}/tariffs")
  public ResponseEntity<List<GetTariffDTO>> tariffs(@PathVariable int serviceId) {
    Optional<Service> optService = serviceRepository.findById(serviceId);
    if (optService.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(getTariffDTOList(optService.get().getTariffs()), HttpStatus.OK);
  }

  private List<GetTariffDTO> getTariffDTOList(List<Tariff> tariffs) {
    return tariffs.stream().map(tariff -> new GetTariffDTO()
        .setId(tariff.getId())
        .setName(tariff.getName())
        .setDuration(tariff.getDuration())
        .setPrice(tariff.getPrice())).toList();
  }

}
