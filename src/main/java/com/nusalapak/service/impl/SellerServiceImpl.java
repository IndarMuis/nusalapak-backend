package com.nusalapak.service.impl;

import com.nusalapak.dto.request.CreateSellerRequest;
import com.nusalapak.entity.Account;
import com.nusalapak.entity.Role;
import com.nusalapak.entity.Seller;
import com.nusalapak.repository.AccountRepository;
import com.nusalapak.repository.RoleRepository;
import com.nusalapak.repository.SellerRepository;
import com.nusalapak.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void create(CreateSellerRequest request) {

        if (!request.getPassword().equalsIgnoreCase(request.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "passwords are not the same");
        }

        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is registered");
        }

        Role role = roleRepository.findById(2).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "role is not found")
        );

        String encodePassword = passwordEncoder.encode(request.getPassword());
        Account account = Account.builder()
                .email(request.getEmail())
                .password(encodePassword)
                .isEnabled(true)
                .role(role)
                .build();

        accountRepository.save(account);

        Seller seller = new Seller();
        seller.setName(request.getName());
        seller.setPhone(request.getPhone());
        seller.setAddress(request.getAddress());
        seller.setAccount(account);

        sellerRepository.save(seller);

    }
}
