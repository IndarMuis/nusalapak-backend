package com.nusalapak.service;

import com.nusalapak.dto.response.AccountResponse;

public interface AccountService {

    AccountResponse findByEmail(String email);

}
