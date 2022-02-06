package me.timur.travelcompanyapp.security.auth;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static me.timur.travelcompanyapp.security.auth.ApplicationUserPermission.*;


/**
 * Created by Temurbek Ismoilov on 28/01/22.
 */

public enum ApplicationUserRole {
    TOUR_OPERATOR(Sets.newHashSet(TO_READ, TO_WRITE)),
    BOOKING(Sets.newHashSet()),
    ACCOUNTANT(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
