package com.gabrielnz.workshopmongodb.service;

import com.gabrielnz.workshopmongodb.domain.User;
import com.gabrielnz.workshopmongodb.dto.UserDTO;
import com.gabrielnz.workshopmongodb.repository.UserRepository;
import com.gabrielnz.workshopmongodb.service.exception.DataBaseIntegrity;
import com.gabrielnz.workshopmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        User user = new User(userDTO.getId(), userDTO.getName(),userDTO.getPassword(),userDTO.getEmail());
        return userRepository.save(user);
    }

    public void delete(String id) {
        try {
            userRepository.deleteById(id);
        }catch(DataIntegrityViolationException e) {
            throw new DataBaseIntegrity("Database integrity violation");
        }
    }
    public User update(UserDTO userDTO) {
        if(userRepository.findById(userDTO.getId()).isEmpty()){
         throw new ObjectNotFoundException("User not found for update");
        }else {
            User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getPassword(), userDTO.getEmail());
            return userRepository.save(user);
        }
    }
}
