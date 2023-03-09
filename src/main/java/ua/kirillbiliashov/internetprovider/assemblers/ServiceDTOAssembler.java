package ua.kirillbiliashov.internetprovider.assemblers;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.kirillbiliashov.internetprovider.controller.ServicesController;
import ua.kirillbiliashov.internetprovider.domain.Service;
import ua.kirillbiliashov.internetprovider.dto.ServiceDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ServiceDTOAssembler implements RepresentationModelAssembler<Service, ServiceDTO> {

  private final ModelMapper modelMapper;
  private final TariffDTOAssembler tariffDTOAssembler;

  public ServiceDTOAssembler(ModelMapper modelMapper,
                             TariffDTOAssembler tariffDTOAssembler) {
    this.modelMapper = modelMapper;
    this.tariffDTOAssembler = tariffDTOAssembler;
  }

  @Override
  public ServiceDTO toModel(Service entity) {
    ServiceDTO serviceDTO = modelMapper.map(entity, ServiceDTO.class);
    serviceDTO.setTariffs(tariffDTOAssembler.toCollectionModel(entity.getTariffs()));
    serviceDTO.add(linkTo(methodOn(ServicesController.class).service(entity.getId())).withSelfRel());
    serviceDTO.add(linkTo(methodOn(ServicesController.class).tariffs(entity.getId())).withRel("tariffs"));
    return serviceDTO;
  }

}
