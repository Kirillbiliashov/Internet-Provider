package ua.kirillbiliashov.internetprovider.converter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import ua.kirillbiliashov.internetprovider.domain.AbstractEntity;

public abstract class DTOToEntityConverter<T, U> implements Converter<T, U> {

  private final ModelMapper modelMapper;
  protected Class<U> targetClass;

  protected DTOToEntityConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public U convert(MappingContext<T, U> mappingContext) {
    T source = mappingContext.getSource();
    return modelMapper.map(source, targetClass);
  }

}
