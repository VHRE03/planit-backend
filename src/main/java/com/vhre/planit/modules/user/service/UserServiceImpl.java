package com.vhre.planit.modules.user.service;

import com.vhre.planit.core.exceptions.ResourceNotFoundException;
import com.vhre.planit.modules.user.dto.UserRequestDTO;
import com.vhre.planit.modules.user.dto.UserResponseDTO;
import com.vhre.planit.modules.user.entity.User;
import com.vhre.planit.modules.user.mapper.UserMapper;
import com.vhre.planit.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    @Transactional
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        User user = mapper.toEntity(requestDTO);
        User savedUser = repository.saveAndFlush(user);
        return mapper.toResponseDTO(savedUser);
    }

    public UserResponseDTO getById(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return mapper.toResponseDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    @Transactional
    public UserResponseDTO update(UUID id, UserRequestDTO requestDTO) {
        User existingUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        mapper.updateEntityFromDTO(requestDTO, existingUser);
        User updatedUser = repository.save(existingUser);
        return mapper.toResponseDTO(updatedUser);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. User not found with ID: " + id);
        }

        repository.deleteById(id);
    }
}
