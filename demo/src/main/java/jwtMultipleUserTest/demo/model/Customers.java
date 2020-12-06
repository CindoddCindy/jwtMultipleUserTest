package jwtMultipleUserTest.demo.model;



import org.hibernate.annotations.Entity;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nameCustomers"
        }),
        @UniqueConstraint(columnNames = {
                "emailCustomers"
        })
})


public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomers;

    @NotBlank
    @Size(max = 40)
    private String nameCustomers;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String emailCustomers;

    @NotBlank
    @Size(max = 20)
    private String phoneCustomers;

    @NotBlank
    @Size(max = 100)
    private String passwordCustomers;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cutomers_roles",
            joinColumns = @JoinColumn(name = "customers_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Customers(String nameCustomers, String emailCustomers, String phoneCustomers, String passwordCustomers) {
        this.nameCustomers = nameCustomers;
        this.emailCustomers = emailCustomers;
        this.phoneCustomers = phoneCustomers;
        this.passwordCustomers = passwordCustomers;
    }

    public Customers() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
}
