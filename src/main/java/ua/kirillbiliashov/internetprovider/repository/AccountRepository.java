package ua.kirillbiliashov.internetprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kirillbiliashov.internetprovider.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
