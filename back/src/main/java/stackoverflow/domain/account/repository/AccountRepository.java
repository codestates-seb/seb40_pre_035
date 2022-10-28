package stackoverflow.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.domain.account.entity.Account;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    default Optional<Account> findByEmail(String email) {
        //DB에 데이터 없으므로 일단 mock 객체 넘겨줌
        Account account = new Account();
        account.setId(1);
        account.setEmail("mock@gmail.com");
        account.setPassword("mock1234");
        account.setNickname("mock nickname");
        account.setRole("USER");
        account.setProfile("/path/mock");
        account.setCreatedAt(LocalDateTime.now());
        account.setModifiedAt(LocalDateTime.now());

        return Optional.of(account);
    }
}
