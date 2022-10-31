package stackoverflow.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account addAccount(Account account) {
        verifyAccountExist(account.getEmail());

        return accountRepository.save(account);
    }

    public Account modifyAccount(Account modifyAccount) {
        Account account = findAccount(modifyAccount.getId());

        Optional.ofNullable(modifyAccount.getPassword())
                        .ifPresent(password -> account.setPassword(password));
        Optional.ofNullable(modifyAccount.getProfile())
                        .ifPresent(profile -> account.setProfile(profile));
        Optional.ofNullable(modifyAccount.getNickname())
                        .ifPresent(nickName -> account.setNickname(nickName));

        return accountRepository.save(account);
    }

    public Account findAccount(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        return optionalAccount.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));
    }

    private void verifyAccountExist(String email) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);

        if (optionalAccount.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.ACCOUNT_EXIST);
        }
    }

    public Account setDefaultProperties(Account account) {  //이후에 구체화 예정
        account.setProfile("/path/default");
        account.setRole("USER");

        return account;
    }
}
