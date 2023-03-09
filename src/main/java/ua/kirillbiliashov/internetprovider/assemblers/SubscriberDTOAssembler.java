package ua.kirillbiliashov.internetprovider.assemblers;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.kirillbiliashov.internetprovider.controller.SubscribersController;
import ua.kirillbiliashov.internetprovider.domain.Person;
import ua.kirillbiliashov.internetprovider.dto.GetSubscriberDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubscriberDTOAssembler implements RepresentationModelAssembler<Person, GetSubscriberDTO> {

  private final ModelMapper modelMapper;
  private final TariffDTOAssembler tariffDTOAssembler;

  public SubscriberDTOAssembler(ModelMapper modelMapper,
                                TariffDTOAssembler tariffDTOAssembler) {
    this.modelMapper = modelMapper;
    this.tariffDTOAssembler = tariffDTOAssembler;
  }

  @Override
  public GetSubscriberDTO toModel(Person entity) {
    GetSubscriberDTO getSubscriberDTO = modelMapper.map(entity, GetSubscriberDTO.class);
    getSubscriberDTO.setTariffs(tariffDTOAssembler.toCollectionModel(entity.getTariffs()));
    getSubscriberDTO
        .add(linkTo(methodOn(SubscribersController.class)
            .subscriber(entity.getId()))
            .withSelfRel());
    return getSubscriberDTO;
  }

}
