package ua.kirillbiliashov.internetprovider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.config.HateoasAwareSpringDataWebConfiguration;
import ua.kirillbiliashov.internetprovider.domain.Tariff;

@SpringBootApplication
public class InternetProviderApplication extends HateoasAwareSpringDataWebConfiguration {

  public InternetProviderApplication(ApplicationContext context, ObjectFactory<ConversionService> conversionService) {
    super(context, conversionService);
  }

  public static void main(String[] args) {
    SpringApplication.run(InternetProviderApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }



}
