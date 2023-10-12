package com.nusalapak.service.impl;

import com.nusalapak.dto.request.CreateAdminRequest;
import com.nusalapak.entity.Account;
import com.nusalapak.entity.Admin;
import com.nusalapak.entity.Role;
import com.nusalapak.repository.AccountRepository;
import com.nusalapak.repository.AdminRepository;
import com.nusalapak.repository.RoleRepository;
import com.nusalapak.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public void create(CreateAdminRequest request) {

        if (!request.getPassword().equalsIgnoreCase(request.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "passwords are not the same");
        }

        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is registered");
        }

        Role role = roleRepository.findById(1).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "role is not found")
        );

        Account account = Account.builder()
                .email(request.getEmail())
                .role(role)
                .password(request.getPassword()).build();
        accountRepository.save(account);

        Admin admin = Admin.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .account(account)
                .build();
        adminRepository.save(admin);
    }
}
