package com.nusalapak.service;

import com.nusalapak.dto.response.AccountDetailResponse;

public interface AccountService {

    AccountDetailResponse findByEmail(String email);

}
