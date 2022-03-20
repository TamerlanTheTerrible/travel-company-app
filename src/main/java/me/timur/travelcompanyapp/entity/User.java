package me.timur.travelcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Temurbek Ismoilov on 05/02/22.
 */

@Transient
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole role;

    private Boolean isAccountNonExpired;

    private Boolean isAccountNonBlocked;

    private Boolean isCredentialsNonExpired;

    private Boolean isEnabled;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        return this.role.getGrantedAuthorities();
    }
}
