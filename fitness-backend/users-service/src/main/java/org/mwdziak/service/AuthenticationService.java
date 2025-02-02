package org.mwdziak.service;


import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.BlacklistedToken;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.domain.User;
import org.mwdziak.dto.AuthenticationResponse;
import org.mwdziak.dto.TokensDTO;
import org.mwdziak.exception.TokenBlacklistedException;
import org.mwdziak.exception.UserAlreadyExistsException;
import org.mwdziak.repository.BlacklistedTokenRepository;
import org.mwdziak.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.mwdziak.dto.AuthenticationRequest;
import org.mwdziak.dto.RegistrationRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BlacklistedTokenRepository blacklistedTokenRepository;
    public AuthenticationResponse register(RegistrationRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        var user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(encoder.encode(request.getPassword()))
                .nutritionalGoals(new NutritionalGoals())
            .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var expirationDate = jwtService.extractExpiration(refreshToken);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .refreshToken(refreshToken)
            .expirationDate(expirationDate.toString())
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var userOptional = repository.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect password");
        }

        var user = userOptional.get();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var expirationDate = jwtService.extractExpiration(refreshToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .expirationDate(expirationDate.toString())
                .build();
    }

    public AuthenticationResponse refresh(TokensDTO request) {
        var email = jwtService.extractUsername(request.getRefreshToken());
        var isTokenBlacklisted = blacklistedTokenRepository.existsByToken(request.getRefreshToken());
        if (isTokenBlacklisted) {
            throw new TokenBlacklistedException("Token is blacklisted");
        }
        blacklistedTokenRepository.save(new BlacklistedToken(request.getToken()));
        blacklistedTokenRepository.save(new BlacklistedToken(request.getRefreshToken()));
        var user = repository.findByEmail(email).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var expirationDate = jwtService.extractExpiration(refreshToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .expirationDate(expirationDate.toString())
                .build();

    }

    public void logout(TokensDTO request) {
        blacklistedTokenRepository.save(new BlacklistedToken(request.getToken()));
        blacklistedTokenRepository.save(new BlacklistedToken(request.getRefreshToken()));
    }

}
