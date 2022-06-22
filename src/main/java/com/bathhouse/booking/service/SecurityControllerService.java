package com.bathhouse.booking.service;

import com.bathhouse.booking.model.User;

public interface SecurityControllerService {
    void saveUser(User user);
    User findUserByUsername(String username);
}
