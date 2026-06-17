package bizmart.backend.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bizmart.backend.auth.dto.RegistrationRequest;
import bizmart.backend.auth.entity.User;
import bizmart.backend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import bizmart.backend.auth.dto.AuthenticationResponse;
import bizmart.backend.auth.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegistrationRequest request) {

		User savedUser = authService.registerUser(request);

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
		
		AuthenticationResponse response = authService.login(request);
		
		return ResponseEntity.ok(response);
		
	}

}
