package net.steveperkins.fitnessjiffy.service;

import net.steveperkins.fitnessjiffy.domain.User;
import net.steveperkins.fitnessjiffy.dto.UserDTO;
import net.steveperkins.fitnessjiffy.repository.UserRepository;
import net.steveperkins.fitnessjiffy.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeightRepository weightRepository;

    @Autowired
    private Converter<User, UserDTO> userDTOConverter;

    public UserDTO getUser(UUID userId) {
        User user = userRepository.findOne(userId);
        return userToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        for(User user : userRepository.findAll()) {
            users.add(userToDTO(user));
        }
        return users;
    }

    private UserDTO userToDTO(User user) {
        return userDTOConverter.convert(user);
    }

    private List<UserDTO> userToDTO(List<User> users) {
        List<UserDTO> dtos = new ArrayList<>();
        for(User user : users) {
            dtos.add(userDTOConverter.convert(user));
        }
        return dtos;
    }

}