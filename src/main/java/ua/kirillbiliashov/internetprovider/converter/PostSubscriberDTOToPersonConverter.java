package ua.kirillbiliashov.internetprovider.converter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import ua.kirillbiliashov.internetprovider.domain.Person;
import ua.kirillbiliashov.internetprovider.dto.PostSubscriberDTO;

@Component
public class PostSubscriberDTOToPersonConverter extends DTOToEntityConverter<PostSubscriberDTO, Person> {

  protected PostSubscriberDTOToPersonConverter(ModelMapper modelMapper) {
    super(modelMapper);
    this.targetClass = Person.class;
  }

}
