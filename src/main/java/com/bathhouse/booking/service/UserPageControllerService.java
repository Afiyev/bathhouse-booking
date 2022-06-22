package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Reservation;
import com.bathhouse.booking.model.User;

import java.util.Set;

public interface UserPageControllerService {
    User findUserByUsername(String username);
    void update(User user, String username, String mobile);
}
