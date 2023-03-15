package ua.kirillbiliashov.internetprovider.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kirillbiliashov.internetprovider.assemblers.TariffDTOAssembler;
import ua.kirillbiliashov.internetprovider.domain.Service;
import ua.kirillbiliashov.internetprovider.domain.Tariff;
import ua.kirillbiliashov.internetprovider.dto.GetTariffDTO;
import ua.kirillbiliashov.internetprovider.dto.PostTariffDTO;
import ua.kirillbiliashov.internetprovider.repository.PersonRepository;
import ua.kirillbiliashov.internetprovider.repository.TariffRepository;
import ua.kirillbiliashov.internetprovider.service.PersonService;
import ua.kirillbiliashov.internetprovider.service.TariffService;

import java.util.Optional;

@RestController
@RequestMapping("/tariffs")
public class TariffsController {

  private final TariffDTOAssembler tariffDTOAssembler;
  private final PersonService personService;
  private final TariffService tariffService;

  @Autowired
  public TariffsController(TariffDTOAssembler tariffDTOAssembler,
                           PersonService personService,
                           TariffService tariffService) {
    this.tariffDTOAssembler = tariffDTOAssembler;
    this.personService = personService;
    this.tariffService = tariffService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetTariffDTO> tariff(@PathVariable int id) {
    Optional<Tariff> optTariff = tariffService.find(id);
    if (optTariff.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    GetTariffDTO tariffDTO = tariffDTOAssembler.toModel(optTariff.get());
    return new ResponseEntity<>(tariffDTO, HttpStatus.OK);
  }

  @PostMapping("/add")
  @ResponseStatus(code = HttpStatus.CREATED)
  public void addTariff(@RequestBody Tariff tariff) {
    tariffService.save(tariff);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<HttpStatus> updateTariff(@PathVariable int id,
                                                 @RequestBody Tariff tariff) {
    boolean isChange = tariffService.update(id, tariff);
    return isChange ? ResponseEntity.ok(HttpStatus.OK) :
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteTariff(@PathVariable int id) {
    tariffService.delete(id);
  }

  @PostMapping("/{tariffId}/subscribe/{personId}")
  public ResponseEntity<HttpStatus> subscribe(@PathVariable int tariffId,
                                              @PathVariable int personId) {
    boolean isChange = personService.subscribe(personId, tariffId);
    return isChange ? ResponseEntity.ok(HttpStatus.OK) :
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
