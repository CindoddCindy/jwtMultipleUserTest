package jwtMultipleUserTest.demo.model;


@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName nameRole;

    public Role() {
    }

    public Role(RoleName nameRole) {
        this.nameRole = nameRole;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public RoleName getNameRole() {
        return nameRole;
    }

    public void setNameRole(RoleName nameRole) {
        this.nameRole = nameRole;
    }
}
