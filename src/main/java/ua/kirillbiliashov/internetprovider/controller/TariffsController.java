package ua.kirillbiliashov.internetprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kirillbiliashov.internetprovider.domain.Person;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.dto.GetTariffDTO;
import ua.kirillbiliashov.internetprovider.dto.PostTariffDTO;
import ua.kirillbiliashov.internetprovider.repository.PersonRepository;
import ua.kirillbiliashov.internetprovider.repository.ServiceRepository;
import ua.kirillbiliashov.internetprovider.repository.TariffRepository;

import java.util.Optional;

@RestController
@RequestMapping("/tariffs")
public class TariffsController {

  private final TariffRepository tariffRepository;
  private final ServiceRepository serviceRepository;
  private final PersonRepository personRepository;

  @Autowired
  public TariffsController(TariffRepository tariffRepository,
                           ServiceRepository serviceRepository,
                           PersonRepository personRepository) {
    this.tariffRepository = tariffRepository;
    this.serviceRepository = serviceRepository;
    this.personRepository = personRepository;
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetTariffDTO> tariff(@PathVariable int id) {
    Optional<Tariff> optTariff = tariffRepository.findById(id);
    if (optTariff.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Tariff tariff = optTariff.get();
    GetTariffDTO tariffDTO = new GetTariffDTO()
        .setId(tariff.getId())
        .setName(tariff.getName())
        .setPrice(tariff.getPrice())
        .setDuration(tariff.getDuration());
    return new ResponseEntity<>(tariffDTO, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addTariff(@RequestBody PostTariffDTO postTariffDTO) {
    Tariff tariff = new Tariff();
    map(tariff, postTariffDTO);
    tariffRepository.save(tariff);
    return ResponseEntity.ok(HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<HttpStatus> updateTariff(@PathVariable int id,
                                                 @RequestBody PostTariffDTO postTariffDTO) {
    Optional<Tariff> optTariff = tariffRepository.findById(id);
    if (optTariff.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Tariff tariff = optTariff.get();
    map(tariff, postTariffDTO);
    tariffRepository.save(tariff);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  private void map(Tariff tariff, PostTariffDTO postTariffDTO) {
    tariff.setName(postTariffDTO.getName());
    tariff.setDuration(postTariffDTO.getDuration());
    tariff.setPrice(postTariffDTO.getPrice());
    tariff.setService(serviceRepository.getReferenceById(postTariffDTO.getServiceId()));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteTariff(@PathVariable int id) {
    tariffRepository.deleteById(id);
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/{tariffId}/subscribe/{personId}")
  public ResponseEntity<HttpStatus> subscribe(@PathVariable int tariffId,
                                              @PathVariable int personId) {
    Optional<Tariff> optTariff = tariffRepository.findById(tariffId);
    Optional<Person> optPerson = personRepository.findById(personId);
    if (optTariff.isEmpty() || optPerson.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Person person = optPerson.get();
    Tariff tariff = optTariff.get();
    person.getTariffs().add(tariff);
    personRepository.save(person);
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
