package org.example.fundspark.service;

import jakarta.validation.*;
import org.example.fundspark.exception.PasswordNotCorrectException;
import org.example.fundspark.exception.UsernameNotFoundException;
import org.example.fundspark.model.Fundraiser;
import org.example.fundspark.model.FundraiserDTO;
import org.example.fundspark.model.User;
import org.example.fundspark.repository.FundraiserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImp implements UserService{

    private final FundraiserRepository fundraiserRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Validator validator;


    @Autowired
    public UserServiceImp(FundraiserRepository fundraiserRepository) {
        this.fundraiserRepository = fundraiserRepository;
//        this.bCryptPasswordEncoder = (BCryptPasswordEncoder) passwordEncoder; // Cast to BCryptPasswordEncoder
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Autowired
    private ValidatorFactory validatorFactory;

    @Override
    public void registerUser(User user) throws ConstraintViolationException{
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Fundraiser fundraiser = new Fundraiser(user);
        fundraiserRepository.save(fundraiser);
    }

//    @Override
//    public User loginUser(String username, String password) throws UsernameNotFoundException, PasswordNotCorrectException{
//        User user = fundraiserRepository.findUserByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
//            throw new PasswordNotCorrectException(username);
//        }
//        return user;
//    }
}
