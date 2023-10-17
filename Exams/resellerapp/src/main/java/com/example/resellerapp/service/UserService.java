package com.example.resellerapp.service;

import com.example.resellerapp.model.entity.User;
import com.example.resellerapp.model.service.UserServiceModel;
import com.example.resellerapp.repository.UserRepository;
import com.example.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }


    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
