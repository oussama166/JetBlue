package com.jetblue.jetblue_server.SERVICE;

import com.jetblue.jetblue_server.Configuration.Assets.JWTService;
import com.jetblue.jetblue_server.Constums.AppException;
import com.jetblue.jetblue_server.DOA.Modules.Security.Role;
import com.jetblue.jetblue_server.DOA.Modules.User;
import com.jetblue.jetblue_server.DOA.Repository.RepositoryUser;
import com.jetblue.jetblue_server.DTOS.CredentialDto;
import com.jetblue.jetblue_server.DTO.UserDto;
import com.jetblue.jetblue_server.Mapper.UserMapper;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceUser implements ManagerUser {
    //Logger
    private final Logger logger = LoggerFactory.getLogger(ServiceUser.class);
    //Injection
    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JWTService jwtService;


    @Override
    public boolean EmailExist(String email) {
        Optional<User> byEmail = repositoryUser.findByEmail(email);
        logger.info("{}", byEmail.isPresent());
        return byEmail.isPresent();
    }

    @Override
    public UserDto login(CredentialDto credentials) {
        // TODO : Replace all this by the authenticationManager

        // ANCHOR  - START
        User user = repositoryUser
                .findByEmail(credentials.Email())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        // ANCHOR  - END

        if (passwordEncoder.matches(CharBuffer.wrap(credentials.Password()), user.getPassword())) {
            UserDto userDto = userMapper.toUserDto(user);
            String tokenUser = jwtService.generateToken(user);
            userDto.setToken(tokenUser);
            return userDto;
        }

        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);
    }

    // get user
    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = repositoryUser.findById(id);
        return optionalUser.orElse(null);
    }

    //get a list of users
    @Override
    public List<User> Users() {
        return repositoryUser.findAll();
    }

    //set list of users
    @Override
    public List<Long> setUsers(List<User> users) {
        List<Long> userList = new ArrayList<>();
        try {
            repositoryUser.saveAll(users);
            userList = users.stream().map(el -> Long.valueOf(el.getUserId())).toList();
            return userList;
        } catch (Exception e) {
            return userList;
        }
    }

    // set one user
    @Override
    public UserDto setUser(User user) {
        Optional<User> userData = repositoryUser
                .findByEmail(user.getEmail());
        if (userData.isPresent()) {
            throw new AppException("User Already exist !!!", HttpStatus.BAD_REQUEST);
        } else {
            // Adding new in case if the user is not in the db
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setLastName(user.getLastName());
            newUser.setGender(user.getGender());
            newUser.setEmail(user.getEmail());
            newUser.setBirthDay(user.getBirthDay());
            newUser.setAddress(user.getAddress());
            newUser.setCity(user.getCity());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setGender(user.getGender());
            newUser.setState(user.getState());
            newUser.setCity(user.getCity());
            newUser.setCountry(user.getCountry());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setRole(Role.USER);
            // Adding the new user in db
            repositoryUser.save(newUser);

            // Generate token for user
            String userToken = jwtService.generateToken(user);

            // Map user to user Dto
            UserDto userDto = userMapper.toUserDto(newUser);

            // Set Token to the user dto
            userDto.setToken(userToken);

            System.out.println(userMapper.toUserDto(newUser));
            return userDto;
        }
    }

    // delete user by info
    @Override
    public User deleteUser(User user) {
        Optional<User> userOption = repositoryUser.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userOption.isPresent()) {
            repositoryUser.delete(user);
        }
        return null;
    }

    // update user
    @Override
    public User updateUser(User user) {
        Optional<User> userOption = repositoryUser.findById((long) user.getUserId());

        if (userOption.isPresent()) {
            userOption.get().setName(user.getName());
            userOption.get().setLastName(user.getLastName());
            userOption.get().setEmail(user.getEmail());
            userOption.get().setBirthDay(user.getBirthDay());
            userOption.get().setGender(user.getGender());
            repositoryUser.save(userOption.get());
        }
        return null;
    }

    @Override
    public boolean userExsistByEmail(String email) {
        return repositoryUser.Email(email);
    }
}
