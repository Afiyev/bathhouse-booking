package com.bathhouse.booking.service;

import com.bathhouse.booking.enums.CabynTypes;
import com.bathhouse.booking.enums.Cities;
import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.Cabin;
import com.bathhouse.booking.model.CabinCreationDto;
import com.bathhouse.booking.model.User;
import com.bathhouse.booking.repository.BathhouseRepository;
import com.bathhouse.booking.repository.RoleRepository;
import com.bathhouse.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPageControllerServiceImpl implements UserPageControllerService {
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
    @Transactional
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    @Transactional
    public void updateUser(User user, String username, String mobile) {
        user.setUsername(username);
        user.setMobile_number(mobile);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateBath(Bathhouse bathhouse, MultipartFile image) {
        String fileName = uploadImage(image);
        if (fileName != null) {
            bathhouse.setImage("/images/" + fileName);
        }
        bathhouseRepository.save(bathhouse);
    }

    @Override
    @Transactional
    public String uploadImage(MultipartFile image) {
        String upload_path = "C:\\java_projects\\source\\bathhouse-booking\\src\\main\\resources\\static\\images\\";
        String fileName = null;
        if (!image.isEmpty()) {
            fileName = StringUtils.cleanPath(image.getOriginalFilename());

            try {
                Path path = Paths.get(upload_path + fileName);
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    @Override
    @Transactional
    public void addBathhouse(CabinCreationDto cabins, MultipartFile image, Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName());
        Bathhouse bathhouse = cabins.getBathhouse();
        for(Cabin cabin : cabins.getCabins()){
            cabin.setBathhouse(bathhouse);
        }
        bathhouse.setCabins(cabins.getCabins());
        bathhouse.setUser(user);
        String fileName = uploadImage(image);
        if (fileName != null) {
            bathhouse.setImage("/images/" + fileName);
        }
        bathhouseRepository.save(bathhouse);
    }

    @Override
    @Transactional
    public void addModelAttributes(User user, Model model, int cabin_number) {
        List<Bathhouse> bathhouses = bathhouseRepository.findAllByUser(user);
        CabinCreationDto cabinsForm = new CabinCreationDto();

        model.addAttribute("bathhouses", bathhouses);
        model.addAttribute("bathhouse", "bathhouse");
        int it = 0;
        for (Bathhouse bath : bathhouses) {
            String m = "bathhouse" + it;
            model.addAttribute(m, bath);
            it++;
        }

        cabinsForm.setCabin_number(cabin_number);
        for (int i = 0; i < cabin_number; i++) {
            cabinsForm.addCabin(new Cabin());
        }
        model.addAttribute("cabins", cabinsForm);
    }
}
