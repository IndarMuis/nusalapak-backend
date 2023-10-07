package com.nusalapak.service.impl;

import com.nusalapak.dto.request.CreateCustomerRequest;
import com.nusalapak.entity.Account;
import com.nusalapak.entity.Customer;
import com.nusalapak.entity.Role;
import com.nusalapak.repository.AccountRepository;
import com.nusalapak.repository.CustomerRepository;
import com.nusalapak.repository.RoleRepository;
import com.nusalapak.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void create(CreateCustomerRequest request) {
        if (!request.getPassword().equalsIgnoreCase(request.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "passwords are not the same");
        }

        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is registered");
        }

        Role role = roleRepository.findById(3).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "role is not found")
        );

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Account account = Account.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .isEnabled(true)
                .role(role).build();
        accountRepository.save(account);

        Customer customer = Customer.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .account(account).build();
        customerRepository.save(customer);
    }
}
