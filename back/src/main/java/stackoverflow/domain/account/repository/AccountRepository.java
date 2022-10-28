package stackoverflow.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.domain.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
