package com.ToDo.todomanagement.Services;

import com.ToDo.todomanagement.Dto.LoginDto;
import com.ToDo.todomanagement.Dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
