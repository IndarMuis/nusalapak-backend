package com.nusalapak.service.impl;

//import com.nusalapak.entity.Account;
//import com.nusalapak.repository.AccountRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final AccountRepository accountRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String userEmail = null;
//        String password = null;
//        List<GrantedAuthority> authorities = null;
//        List<Account> accounts = accountRepository.findByEmail(username);
//
//        if (accounts.size() == 0) {
//            throw new UsernameNotFoundException("User details not found for the email : " + username);
//        } else {
//            userEmail = accounts.get(0).getEmail();
//            password = accounts.get(0).getPassword();
//            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(accounts.get(0).getRole().getName()));
//        }
//        return new User(userEmail, password, authorities);
//    }
//}
