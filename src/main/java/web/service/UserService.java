package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void addUser(User user);
    void deleteById(Long id);
    void updateUser(User user);
    User findById(Long id);
}
