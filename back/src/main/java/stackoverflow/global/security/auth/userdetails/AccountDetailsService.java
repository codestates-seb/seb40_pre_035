package stackoverflow.global.security.auth.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;
import stackoverflow.global.security.auth.utils.CustomAuthorityUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class AccountDetailsService implements UserDetailsService {
    private final CustomAuthorityUtils authorityUtils;
    private final AccountRepository accountRepository;

    public AccountDetailsService(CustomAuthorityUtils authorityUtils, AccountRepository accountRepository) {
        this.authorityUtils = authorityUtils;
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByEmail(username);
        Account findAccount = optionalAccount.orElseThrow(() -> new RuntimeException("No User Exist"));

        return new AccountDetails(findAccount);
    }

    private final class AccountDetails extends Account implements UserDetails {

        AccountDetails(Account account) {
            setId(account.getId());
            setEmail(account.getEmail());
            setPassword(account.getPassword());
            setRole(account.getRole());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(this::getRole);
            return authorities;
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
