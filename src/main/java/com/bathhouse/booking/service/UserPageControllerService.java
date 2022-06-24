package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.CabinCreationDto;
import com.bathhouse.booking.model.User;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface UserPageControllerService {
    User findUserByUsername(String username);
    void updateUser(User user, String username, String mobile);
    void updateBath(Bathhouse bathhouse, MultipartFile image);
    void addBathhouse(CabinCreationDto cabins, MultipartFile image, Principal principal);
    String uploadImage(MultipartFile image);
    void addModelAttributes(User user, Model model, int cabin_number);
}
