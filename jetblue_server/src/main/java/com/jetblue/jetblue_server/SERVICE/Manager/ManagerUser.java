package com.jetblue.jetblue_server.SERVICE.Manager;

import com.jetblue.jetblue_server.DOA.Modules.User;
import com.jetblue.jetblue_server.DTOS.CredentialDto;
import com.jetblue.jetblue_server.DTO.UserDto;

import java.util.List;


public interface ManagerUser {
    /***
     * This function is for check if the email is already exist or not
     * @param email
     * @return
     */
    boolean EmailExist(String email);
    /**
     *  This function for login using the credential info from login page (email and password)
     * @param credentials
     * @return UserDto
     */
    UserDto login(CredentialDto credentials);

    /**
     * This function to get all Users in the db
     *
     * @params
     * @return List<User>
     */
    List<User> Users();

    /**
     * This function to get all Users in the db by the ID
     *
     * @params long id
     * @return User
     */
    User getUser(Long id);

    // set one user
    UserDto setUser(User user);

    //set list of user
    List<Long> setUsers(List<User> users);

    // delete user by information
    User deleteUser(User user);

    // update info of user
    User updateUser(User user);

    boolean userExsistByEmail(String email);

}
