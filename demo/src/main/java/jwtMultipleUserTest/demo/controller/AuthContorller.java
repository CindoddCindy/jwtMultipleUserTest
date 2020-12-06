package jwtMultipleUserTest.demo.controller;

import jwtMultipleUserTest.demo.exception.AppException;
import jwtMultipleUserTest.demo.exception.BadRequestException;
import jwtMultipleUserTest.demo.model.Customers;
import jwtMultipleUserTest.demo.model.Role;
import jwtMultipleUserTest.demo.model.RoleName;
import jwtMultipleUserTest.demo.payload.ApiResponse;
import jwtMultipleUserTest.demo.payload.JwtAuthenticationResponse;
import jwtMultipleUserTest.demo.payload.LoginRequest;
import jwtMultipleUserTest.demo.payload.SignUpRequest;
import jwtMultipleUserTest.demo.repository.CustomersRepository;
import jwtMultipleUserTest.demo.repository.RoleRepository;
import jwtMultipleUserTest.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("http://localhost:8082")
@RestController
@RequestMapping("/api/auth")
public class AuthContorller {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getCustomersPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    private String getJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    @GetMapping("/user")
    public ResponseEntity<?> user(HttpServletRequest request) {
        String bearerToken = this.getJwt(request);
        if (bearerToken == null) {
            return ResponseEntity.status(403).body("akses tidak diizinkan.");
        }

        long userId = tokenProvider.getUserIdFromJWT(bearerToken);
        Optional<Customers> customers = customersRepository.findByIdIn(userId);

        return ResponseEntity.ok(customers);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
       /* if(customersRepository.existsByUsername(signUpRequest.getNameCustomers())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(customersRepository.existsByEmail(signUpRequest.getEmailCustomers())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Customers customers = new Customers(signUpRequest.getNameCustomers(), signUpRequest.getEmailCustomers(),
                signUpRequest.getPhoneCustomers(), signUpRequest.getPasswordCustomers());

        customers.setPasswordCustomers(passwordEncoder.encode(customers.getPasswordCustomers()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        customers.setRoles(Collections.singleton(userRole));

        Customers result = customersRepository.save(customers);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getNameCustomers()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

        */

        if(customersRepository.existsByUsername(signUpRequest.getNameCustomers()))
            return ResponseEntity.badRequest().body(new BadRequestException("Error: Username is already taken!"));
        if(customersRepository.existsByEmail(signUpRequest.getEmailCustomers()))
            return ResponseEntity.badRequest().body(new BadRequestException("Error: Email is already taken!"));
        // Create new user's acconut
        Customers customers = new Customers(signUpRequest.getNameCustomers(), signUpRequest.getEmailCustomers(),signUpRequest.getPhoneCustomers()
                passwordEncoder.encode(signUpRequest.getNameCustomers()));
        Set<String> strRole = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRole == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_SELLER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRole.forEach(role -> {
                switch(role){
                    case "buyer":
                        Role buyerRole = roleRepository.findByName(RoleName.ROLE_BUYER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(buyerRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_SELLER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        customers.setRoles(roles);
        customersRepository.save(customers);

        return ResponseEntity.ok(new BadRequestException("User registered successfully!"));
    }
}
