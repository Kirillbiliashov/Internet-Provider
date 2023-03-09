package ua.kirillbiliashov.internetprovider.assemblers;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ua.kirillbiliashov.internetprovider.controller.ServicesController;
import ua.kirillbiliashov.internetprovider.controller.TariffsController;
import ua.kirillbiliashov.internetprovider.domain.Service;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.dto.GetTariffDTO;
import ua.kirillbiliashov.internetprovider.dto.ServiceDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TariffDTOAssembler implements RepresentationModelAssembler<Tariff, GetTariffDTO> {

  private final ModelMapper modelMapper;

  public TariffDTOAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public GetTariffDTO toModel(Tariff entity) {
    GetTariffDTO tariffDTO = modelMapper.map(entity, GetTariffDTO.class);
    tariffDTO
        .add(linkTo(methodOn(TariffsController.class)
            .tariff(entity.getId()))
            .withSelfRel());
    return tariffDTO;
  }

}
