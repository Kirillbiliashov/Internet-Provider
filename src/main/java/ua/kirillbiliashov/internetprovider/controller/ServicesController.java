package ua.kirillbiliashov.internetprovider.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ua.kirillbiliashov.internetprovider.domain.Service;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.dto.GetTariffDTO;
import ua.kirillbiliashov.internetprovider.dto.ServiceDTO;
import ua.kirillbiliashov.internetprovider.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServicesController {

  private final ServiceRepository serviceRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public ServicesController(ServiceRepository serviceRepository,
                            ModelMapper modelMapper) {
    this.serviceRepository = serviceRepository;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public List<ServiceDTO> services() {
    List<Service> services = serviceRepository.findAll();
    return services.stream()
        .map(service -> modelMapper.map(service, ServiceDTO.class))
        .toList();
  }

  @GetMapping("/{serviceId}/tariffs")
  public ResponseEntity<List<GetTariffDTO>> tariffs(@PathVariable int serviceId) {
    Optional<Service> optService = serviceRepository.findById(serviceId);
    if (optService.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    List<GetTariffDTO> dtoList = getTariffDTOList(optService.get().getTariffs());
    return new ResponseEntity<>(dtoList, HttpStatus.OK);
  }

  private List<GetTariffDTO> getTariffDTOList(List<Tariff> tariffs) {
    return tariffs
        .stream()
        .map(tariff -> modelMapper.map(tariff, GetTariffDTO.class))
        .toList();
  }

}
