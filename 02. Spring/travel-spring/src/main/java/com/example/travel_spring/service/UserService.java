
package com.example.travel_spring.service;

import com.example.travel_spring.entity.User;
import com.example.travel_spring.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Получение всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Поиск пользователя по ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Добавление или редактирование пользователя
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Удаление пользователя
    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
