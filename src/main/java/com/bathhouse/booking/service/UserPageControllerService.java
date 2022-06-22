package com.bathhouse.booking.service;

import com.bathhouse.booking.model.User;

public interface UserPageControllerService {
    User findUserByUsername(String username);
}
