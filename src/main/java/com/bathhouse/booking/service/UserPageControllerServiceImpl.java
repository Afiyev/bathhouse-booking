package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.User;
import com.bathhouse.booking.repository.BathhouseRepository;
import com.bathhouse.booking.repository.RoleRepository;
import com.bathhouse.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPageControllerServiceImpl implements UserPageControllerService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BathhouseRepository bathhouseRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserPageControllerServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                                         BCryptPasswordEncoder passwordEncoder,
                                         BathhouseRepository bathhouseRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.bathhouseRepository = bathhouseRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void updateUser(User user, String username, String mobile) {
        user.setUsername(username);
        user.setMobile_number(mobile);
        userRepository.save(user);
    }

    @Override
    public void updateBath(Bathhouse bathhouse) {
        bathhouseRepository.save(bathhouse);
    }
}
