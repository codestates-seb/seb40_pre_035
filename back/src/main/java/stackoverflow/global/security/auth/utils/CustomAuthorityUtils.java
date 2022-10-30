package stackoverflow.global.security.auth.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomAuthorityUtils {

    public Collection<GrantedAuthority> createAuthorities(String role) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_" + role);
        return authorities;
    }
}
