package com.brandwatch.user.domain.repository;

import java.util.List;

import com.brandwatch.user.domain.model.User;

public interface UserRepository {

    User find(long id);

    List<User> findAll();

    void store(User user);

    void delete(long id);
}
