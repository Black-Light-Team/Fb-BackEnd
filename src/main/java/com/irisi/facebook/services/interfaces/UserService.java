package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto getUser (String id);
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUser(String id);
    List<UserDto> allUsers();
}