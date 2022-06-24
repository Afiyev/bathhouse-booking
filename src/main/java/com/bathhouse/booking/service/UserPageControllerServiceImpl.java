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
        if (fileName != null) {
            bathhouse.setImage("/images/" + fileName);
        }
//        bathhouseRepository.save(bathhouse);
    }

    @Override
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
    public void addModelAttributes(User user, Model model, int cabin_number) {
        List<Bathhouse> bathhouses = new ArrayList<>();

        Cabin cabin = new Cabin();
        cabin.setCapacity(CabynTypes.MEDIUM.toString());
        cabin.setPrice(3000);
        cabin.setId(45);

        Cabin cabin1 = new Cabin();
        cabin1.setCapacity(CabynTypes.SMALL.toString());
        cabin1.setPrice(2500);
        cabin1.setId(123);

        List<Cabin> cabins = new ArrayList<>();
        cabins.add(cabin);
        cabins.add(cabin1);

        Bathhouse bathhouse3 = new Bathhouse();
        bathhouse3.setImage("/images/bath1.jpg");
        bathhouse3.setAddress("698 Candlewood Lane, Cabot Cove, Maine.");
        bathhouse3.setCity(Cities.ALMATY.toString());
        bathhouse3.setDescription("The history of bath in Japan begins in the 6th Century with the introduction of Buddist purification rituals. The custom was believed to cleanse the body and spirit to promote improved health using heat and steam. This why many temples in Japan have baths.");
        bathhouse3.setName("Japanese Bathhouse");
        bathhouse3.setPhone_number("+7(707) 458 56 32");
        bathhouse3.setCabins(cabins);
        bathhouses.add(bathhouse3);
        bathhouses.add(bathhouse3);
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
