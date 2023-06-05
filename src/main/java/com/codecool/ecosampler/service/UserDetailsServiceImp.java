package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.repository.UserRepository;
import com.codecool.ecosampler.utilities.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User doesn't exist with username: " + username
                ));
        return UserMapper.toAuthUser(user);
    }
}
