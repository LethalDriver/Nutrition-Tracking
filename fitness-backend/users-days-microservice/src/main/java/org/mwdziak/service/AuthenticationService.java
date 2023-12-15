package org.mwdziak.service;


import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.User;
import org.mwdziak.dto.AuthenticationResponse;
import org.mwdziak.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public AuthenticationResponse register(RegistrationRequest request) {
        var user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(encoder.encode(request.getPassword()))
            .hydrationGoal(request.getHydrationGoal())
            .caloriesGoal(request.getCaloriesGoal())
            .proteinGoal(request.getProteinGoal())
            .fatGoal(request.getFatGoal())
            .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .email(request.getEmail())
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .email(request.getEmail())
            .build();
    }

}
