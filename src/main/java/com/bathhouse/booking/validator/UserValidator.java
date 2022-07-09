package com.bathhouse.booking.validator;

import com.bathhouse.booking.model.User;
import com.bathhouse.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User userDetails = userRepository.findUserByUsername(user.getUsername());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (userDetails != null) {
            errors.rejectValue("username", "Duplicate.user.username");
        }
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.user.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile_number", "Required");
        String pattern = "(\\+7|8)[0-9]{10}";
        if (!user.getMobile_number().matches(pattern)) {
            errors.rejectValue("mobile_number", "Pattern.user.mobile_number");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        String passwordPattern = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$\n" +
                "\n";
        if (!user.getPassword().matches(passwordPattern)) {
            errors.rejectValue("password", "Pattern.user.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "Required");
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match.user.confirmPassword");
        }
    }
}
