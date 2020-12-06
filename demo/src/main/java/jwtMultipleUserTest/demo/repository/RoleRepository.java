package jwtMultipleUserTest.demo.repository;

import jwtMultipleUserTest.demo.model.Role;
import jwtMultipleUserTest.demo.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
