package stackoverflow.global.security.auth.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import stackoverflow.domain.account.Account;
import stackoverflow.global.security.auth.utils.CustomAuthorityUtils;

import java.util.Collection;
import java.util.Optional;

@Component
public class AccountDetailsService implements UserDetailsService {
    private final CustomAuthorityUtils authorityUtils;
//    private final AccountRepository accountRepository;

    public AccountDetailsService(CustomAuthorityUtils authorityUtils) {
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Account> optionalAccount = accountRepository.findByEmail(username);
//        Account findAccount = optionalAccount.orElseThrow(() -> new RuntimeException("No User Exist"));
//
//        return new AccountDetails(findAccount);
        return null;
    }

    private final class AccountDetails extends Account implements UserDetails {

        AccountDetails(Account account) {
            setAccountId(account.getAccountId());
            setEmail(account.getEmail());
            setPassword(account.getPassword());
            setRoles(account.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return null;
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    }
}
