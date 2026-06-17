package bizmart.backend.auth.service;

import org.springframework.stereotype.Service;

import bizmart.backend.auth.dto.RegistrationRequest;
import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.repository.UserRepository;
import bizmart.backend.common.exception.InvalidCredentialsException;
import bizmart.backend.common.exception.UserAlreadyExistsException;
import bizmart.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import bizmart.backend.auth.dto.AuthenticationResponse;
import bizmart.backend.auth.dto.LoginRequest;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public User registerUser(RegistrationRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException("Email already registered");
		}

		if (userRepository.existsByMobile(request.getMobile())) {
			throw new UserAlreadyExistsException("Mobile already registered");
		}

		User user = User.builder().fullName(request.getFullName()).email(request.getEmail()).mobile(request.getMobile())
				.password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).isActive(true).build();

		return userRepository.save(user);
	}

	public AuthenticationResponse login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new InvalidCredentialsException("User not found"));

		boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!passwordMatches) {
			throw new InvalidCredentialsException("Invalid Password");
		}

		
		
		String token =
		        jwtService.generateToken(
		                user.getEmail(),user.getRole().name());

		return new AuthenticationResponse(token);
	}

}
