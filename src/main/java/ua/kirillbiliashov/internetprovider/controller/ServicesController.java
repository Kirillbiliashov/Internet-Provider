package ua.kirillbiliashov.internetprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kirillbiliashov.internetprovider.assemblers.ServiceDTOAssembler;
import ua.kirillbiliashov.internetprovider.assemblers.TariffDTOAssembler;
import ua.kirillbiliashov.internetprovider.domain.Service;
import ua.kirillbiliashov.internetprovider.dto.GetTariffDTO;
import ua.kirillbiliashov.internetprovider.dto.ServiceDTO;
import ua.kirillbiliashov.internetprovider.repository.ServiceRepository;
import ua.kirillbiliashov.internetprovider.service.ServiceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServicesController {

  private final ServiceDTOAssembler serviceDTOAssembler;
  private final TariffDTOAssembler tariffDTOAssembler;
  private final ServiceService serviceService;

  @Autowired
  public ServicesController(ServiceDTOAssembler serviceDTOAssembler,
                            TariffDTOAssembler tariffDTOAssembler,
                            ServiceService serviceService) {
    this.serviceDTOAssembler = serviceDTOAssembler;
    this.tariffDTOAssembler = tariffDTOAssembler;
    this.serviceService = serviceService;
  }

  @GetMapping
  public CollectionModel<ServiceDTO> services() {
    List<Service> services = serviceService.findAll();
    return serviceDTOAssembler.toCollectionModel(services);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ServiceDTO> service(@PathVariable int id) {
    Optional<Service> optService = serviceService.find(id);
    if (optService.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok(serviceDTOAssembler.toModel(optService.get()));
  }

  @GetMapping("/{serviceId}/tariffs")
  public ResponseEntity<CollectionModel<GetTariffDTO>> tariffs(@PathVariable int serviceId) {
    Optional<Service> optService = serviceService.find(serviceId);
    if (optService.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    CollectionModel<GetTariffDTO> collectionModel =
        tariffDTOAssembler.toCollectionModel(optService.get().getTariffs());
    return new ResponseEntity<>(collectionModel, HttpStatus.OK);
  }

}
