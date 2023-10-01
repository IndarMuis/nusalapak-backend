package com.nusalapak.security.provider;

import com.nusalapak.entity.Account;
import com.nusalapak.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<Account> accounts = accountRepository.findByEmail(email);

        if (accounts.size() > 0) {
            if (passwordEncoder.matches(password, accounts.get(0).getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(accounts.get(0).getRole().getName()));
                return new UsernamePasswordAuthenticationToken(email, password, authorities);
            } else {
                throw new BadCredentialsException("invalid username or password");
            }
        } else {
            throw new BadCredentialsException("account not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
