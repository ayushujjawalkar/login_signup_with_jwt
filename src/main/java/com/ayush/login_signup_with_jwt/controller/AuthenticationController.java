package com.ayush.login_signup_with_jwt.controller;
import com.ayush.login_signup_with_jwt.dto.LoginUserDto;
import com.ayush.login_signup_with_jwt.dto.RegisterUserDto;
import com.ayush.login_signup_with_jwt.dto.VerifyUserDto;
import com.ayush.login_signup_with_jwt.responses.LoginResponse;
import com.ayush.login_signup_with_jwt.service.AuthenticationService;
import com.ayush.login_signup_with_jwt.service.JwtService;
import org.springframework.http.ResponseEntity;
// ✅ Correct import (your entity class)
import com.ayush.login_signup_with_jwt.model.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email)
    {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/test")
    public String test() {
        return "API is working!";
    }
}
