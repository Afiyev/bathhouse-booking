package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.User;

import java.util.Set;

public interface UserPageControllerService {
    User findUserByUsername(String username);
    void updateUser(User user, String username, String mobile);
    void updateBath(Bathhouse bathhouse);
}
