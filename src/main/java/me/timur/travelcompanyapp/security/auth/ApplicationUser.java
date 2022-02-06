package me.timur.travelcompanyapp.security.auth;

import me.timur.travelcompanyapp.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Temurbek Ismoilov on 28/01/22.
 */

public record ApplicationUser(
        String username,
        String password,
        Set<SimpleGrantedAuthority> grantedAuthorities,
        boolean isAccountNonExpired,
        boolean isAccountNonBlocked,
        boolean isCredentialsNonExpired,
        boolean isEnabled
) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonBlocked;
    }

    public static ApplicationUser fromEntity(User user) {
        return new ApplicationUser(
                user.getUsername(),
                user.getPassword(),
                user.getGrantedAuthorities(),
                user.getIsAccountNonExpired(),
                user.getIsAccountNonBlocked(),
                user.getIsCredentialsNonExpired(),
                user.getIsEnabled()
        );
    }
}
