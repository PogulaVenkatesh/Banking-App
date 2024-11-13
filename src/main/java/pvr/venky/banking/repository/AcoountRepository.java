package pvr.venky.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pvr.venky.banking.entity.Account;

public interface AcoountRepository extends JpaRepository<Account, Long>{

}
