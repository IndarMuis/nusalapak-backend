package com.nusalapak.service.impl;

import com.nusalapak.entity.Account;
import com.nusalapak.entity.Authority;
import com.nusalapak.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid email or password"));
        return new User(account.getEmail(), account.getPassword(), mapAuthorityToGrantedAuthority(account.getAuthorities()));
    }

    private Collection<GrantedAuthority> mapAuthorityToGrantedAuthority(Set<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }

}
