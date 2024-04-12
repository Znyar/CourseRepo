package com.znyar.service.user_settings;

import com.znyar.model.User;

public interface UserSettingsService {

    void updateLoginAndPassword(User user, String login, String password);
    void deleteUser(User user);

    void findAllUsers();

}
