package com.example.coffeeshop.service;

import com.example.coffeeshop.model.dto.UserViewDto;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserViewDto> findAllUserAndCountOfOrdersByCountDesc() {
        return userRepository.findAllByOrdersCountDescending()
                .stream()
                .map(user -> {
                    UserViewDto userViewDto = new UserViewDto();
                    userViewDto.setUsername(user.getUsername());
                    userViewDto.setCountOfOrders(user.getOrders().size());
                    return userViewDto;
                })
                .collect(Collectors.toList());
    }
}
