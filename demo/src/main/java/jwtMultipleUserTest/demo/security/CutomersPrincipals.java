package jwtMultipleUserTest.demo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jwtMultipleUserTest.demo.model.Customers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class CutomersPrincipals implements UserDetails {

    private Long idCustomers;

    private String nameCustomers;

    @JsonIgnore
    private String emailCustomers;


    private String phoneCustomers;


    @JsonIgnore
    private String passwordCustomers;

    private Collection<? extends GrantedAuthority> authorities;

    public CutomersPrincipals(Long idCustomers, String nameCustomers, String emailCustomers, String phoneCustomers, String passwordCustomers, Collection<? extends GrantedAuthority> authorities) {
        this.idCustomers = idCustomers;
        this.nameCustomers = nameCustomers;
        this.emailCustomers = emailCustomers;
        this.phoneCustomers = phoneCustomers;
        this.passwordCustomers = passwordCustomers;
        this.authorities = authorities;
    }

    public static CutomersPrincipals create(Customers customers) {
        List<GrantedAuthority> authorities = customers.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getNameRole().name())
        ).collect(Collectors.toList());

        return new CutomersPrincipals(
                customers.getIdCustomers(),
                customers.getNameCustomers(),
                customers.getEmailCustomers(),
                customers.getPhoneCustomers(),
                customers.getPasswordCustomers(),
                authorities
        );
    }

    public Long getIdCustomers() {
        return idCustomers;
    }

    public void setIdCustomers(Long idCustomers) {
        this.idCustomers = idCustomers;
    }

    public String getNameCustomers() {
        return nameCustomers;
    }

    public void setNameCustomers(String nameCustomers) {
        this.nameCustomers = nameCustomers;
    }

    public String getEmailCustomers() {
        return emailCustomers;
    }

    public void setEmailCustomers(String emailCustomers) {
        this.emailCustomers = emailCustomers;
    }

    public String getPhoneCustomers() {
        return phoneCustomers;
    }

    public void setPhoneCustomers(String phoneCustomers) {
        this.phoneCustomers = phoneCustomers;
    }

    public String getPasswordCustomers() {
        return passwordCustomers;
    }

    public void setPasswordCustomers(String passwordCustomers) {
        this.passwordCustomers = passwordCustomers;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passwordCustomers;
    }

    @Override
    public String getUsername() {
        return nameCustomers;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CutomersPrincipals that = (CutomersPrincipals) o;
        return Objects.equals(idCustomers, that.idCustomers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCustomers);
    }
}
