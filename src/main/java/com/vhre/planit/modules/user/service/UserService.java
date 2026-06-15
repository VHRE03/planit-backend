package com.vhre.planit.modules.user.service;

import com.vhre.planit.modules.user.dto.UserRequestDTO;
import com.vhre.planit.modules.user.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO create(UserRequestDTO requestDTO);
    UserResponseDTO getById(UUID id);
    List<UserResponseDTO> getAll();
    UserResponseDTO update(UUID id, UserRequestDTO requestDTO);
    void delete(UUID id);
}
