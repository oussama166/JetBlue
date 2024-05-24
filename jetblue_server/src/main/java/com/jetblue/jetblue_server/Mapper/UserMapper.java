package com.jetblue.jetblue_server.Mapper;

import com.jetblue.jetblue_server.DOA.Modules.User;
import com.jetblue.jetblue_server.DTO.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    ModelMapper mm = new ModelMapper();

    public UserDto toUserDto(User user) {
        return mm.map(user, UserDto.class);
    }

    public User toUser(UserDto userDto) {
        return mm.map(userDto, User.class);
    }
}
