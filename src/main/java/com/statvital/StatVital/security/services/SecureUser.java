package com.statvital.StatVital.security.services;

import com.statvital.StatVital.data.model.HospitalAdmin;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
public class SecureUser implements UserDetails {
    private final HospitalAdmin hospitalAdmin;

    @Override
    public String getUsername() {
        return hospitalAdmin.getEmail();
    }
    @Override
    public String getPassword() {
        return hospitalAdmin.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        return authorityList;
    }




    @Override
    public boolean isAccountNonExpired() {
        return false;
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
