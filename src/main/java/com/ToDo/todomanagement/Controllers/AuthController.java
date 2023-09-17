package com.ToDo.todomanagement.Controllers;

import com.ToDo.todomanagement.Dto.JwtAuthResponse;
import com.ToDo.todomanagement.Dto.LoginDto;
import com.ToDo.todomanagement.Dto.RegisterDto;
import com.ToDo.todomanagement.Services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken( token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.CREATED);
    }
}
