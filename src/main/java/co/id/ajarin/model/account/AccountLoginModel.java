package co.id.ajarin.model.account;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginModel extends OutputRepositoryModel implements UserDetails {
    
    public AccountLoginModel(AccountRegisterEntity account) {
        this.email = account.getEmail();
        this.password = account.getPassword();
        System.out.println(Role.valueOf(account.getRole()));
        this.role = Role.valueOf(account.getRole());
    }
    
    private String email;
    private String password;
    private String token;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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
