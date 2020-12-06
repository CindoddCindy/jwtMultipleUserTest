package jwtMultipleUserTest.demo.security;

import jwtMultipleUserTest.demo.model.Customers;
import jwtMultipleUserTest.demo.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomCustomersDetailService implements UserDetailsService {

    @Autowired
    CustomersRepository customersRepository;// dari repo


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // Let people login with either username or email
        Customers customers = customersRepository.findByUsernameOrEmail(s, s)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + s)
                );
        return UserPrincipal.create(customers);;
    }

    @Transactional
    public UserDetails loadUserById(Long cutomersIdn) {
        Customers customers = customersRepository.findByIdIn(cutomersIdn).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + cutomersIdn)
        );

        return UserPrincipal.create(customers);
    }
}
