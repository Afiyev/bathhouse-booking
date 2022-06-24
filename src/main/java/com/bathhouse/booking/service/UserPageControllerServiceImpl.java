package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.User;
import com.bathhouse.booking.repository.BathhouseRepository;
import com.bathhouse.booking.repository.RoleRepository;
import com.bathhouse.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
    public void updateBath(Bathhouse bathhouse, MultipartFile image) {
        String fileName = uploadImage(image);
        if (fileName != null){
            bathhouse.setImage("/images/"+fileName);
        }
        //        bathhouseRepository.save(bathhouse);
    }

    @Override
    public String uploadImage(MultipartFile image) {
        String upload_path = "C:\\java_projects\\source\\bathhouse-booking\\src\\main\\resources\\static\\images\\";
        String fileName = null;
        if(!image.isEmpty()){
             fileName = StringUtils.cleanPath(image.getOriginalFilename());
            System.out.println(fileName);

            try{
                Path path = Paths.get(upload_path + fileName);
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
