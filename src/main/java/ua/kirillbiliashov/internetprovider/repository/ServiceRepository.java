package ua.kirillbiliashov.internetprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kirillbiliashov.internetprovider.domain.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
