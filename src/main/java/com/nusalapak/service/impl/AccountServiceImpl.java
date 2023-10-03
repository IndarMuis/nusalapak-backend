package com.nusalapak.service.impl;

import com.nusalapak.dto.response.AccountResponse;
import com.nusalapak.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountResponse findByEmail(String email) {
        return null;
    }
}
