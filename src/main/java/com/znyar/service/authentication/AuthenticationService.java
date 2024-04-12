package com.znyar.service.authentication;

import com.znyar.model.User;

public interface AuthenticationService {

    void signUp(String login, String password);
    void signIn(String login, String password);

}
