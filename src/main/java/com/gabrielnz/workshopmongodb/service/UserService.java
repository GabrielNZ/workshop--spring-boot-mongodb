package com.gabrielnz.workshopmongodb.service;

import com.gabrielnz.workshopmongodb.domain.User;
import com.gabrielnz.workshopmongodb.dto.UserDTO;
import com.gabrielnz.workshopmongodb.repository.UserRepository;
import com.gabrielnz.workshopmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public User save(UserDTO userDTO) {
        User user = new User(null, userDTO.getName(),userDTO.getPassword(),userDTO.getEmail());
        return userRepository.save(user);
    }
}
