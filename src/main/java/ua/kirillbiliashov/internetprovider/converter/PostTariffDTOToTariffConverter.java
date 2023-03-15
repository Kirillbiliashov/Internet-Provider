package ua.kirillbiliashov.internetprovider.converter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.dto.PostTariffDTO;

@Component
public class PostTariffDTOToTariffConverter extends DTOToEntityConverter<PostTariffDTO, Tariff> {


  protected PostTariffDTOToTariffConverter(ModelMapper modelMapper) {
    super(modelMapper);
    this.targetClass = Tariff.class;
  }

}
