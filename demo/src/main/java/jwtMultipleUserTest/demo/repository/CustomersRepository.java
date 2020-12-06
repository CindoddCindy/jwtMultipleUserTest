package jwtMultipleUserTest.demo.repository;

import jwtMultipleUserTest.demo.model.Customers;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository  extends JpaRepository<Customers, Long>{
        Optional<Customers> findByEmail(String emailCustomers);

        Optional<Customers> findByUsernameOrEmail(String nameCustomers, String emailCustomers);

        List<Customers> findByIdIn(List<Long> CustomersIds);

        Optional<Customers> findByUsername(String nameCustomers);

        Boolean existsByUsername(String nameCustomers);

        Boolean existsByEmail(String emailCustomers);
}
