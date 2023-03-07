package ua.kirillbiliashov.internetprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kirillbiliashov.internetprovider.domain.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}
