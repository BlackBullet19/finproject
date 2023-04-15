package org.finproject.service;

import org.finproject.entity.ProgramUser;
import org.finproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ProgramUser> list() {
        return userRepository.findAll();
    }

    @Override
    public ProgramUser createUser(ProgramUser user) {
        if(user.getLogin().isEmpty() || user.getName().isEmpty() || user.getPassword().isEmpty()){
            throw new IllegalArgumentException("At least one entered parameter was 0 or empty");
        }
        ProgramUser newUser = new ProgramUser(user.getName(), user.getLogin(), user.getPassword());
        ProgramUser savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public ProgramUser getUser(long userId) {
        if(userId == 0) throw new IllegalArgumentException("Entered value can not be 0");
        if(!existsByUserId(userId)){
            throw new NoSuchElementException("User with ID " + userId + " not found");
        }
        return userRepository.findByUserId(userId);
    }

    @Override
    public ProgramUser updateUser(long userId, ProgramUser user) {
        if(userId == 0) throw new IllegalArgumentException("Entered value can not be 0");
        if(!existsByUserId(userId)){
            throw new NoSuchElementException("User with ID " + userId + " not found");
        }
        if(user.getLogin().isEmpty() || user.getName().isEmpty() || user.getPassword().isEmpty()){
            throw new IllegalArgumentException("At least one entered parameter was 0 or empty");
        }
        ProgramUser existingUser = userRepository.findByUserId(userId);
        existingUser.setLogin(user.getLogin());
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(long userId) {
        if(userId == 0) throw new IllegalArgumentException("Entered value can not be 0");
        if(!userRepository.existsByUserId(userId)){
            throw new NoSuchElementException("User with ID " + userId + " not found");
        }
        userRepository.delete(getUser(userId));
    }

    @Override
    public boolean existsByUserId(long userId) {
        if(userId == 0) throw new IllegalArgumentException("Entered value can not be 0");
        if(!userRepository.existsByUserId(userId)){
            throw new NoSuchElementException("User with ID " + userId + " not found");
        }
        return userRepository.existsByUserId(userId);
    }
}
