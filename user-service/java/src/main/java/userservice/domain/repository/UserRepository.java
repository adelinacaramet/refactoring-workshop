package userservice.domain.repository;

import java.util.List;

import userservice.domain.model.User;

public interface UserRepository {

    User find(long id);

    List<User> findAll();

    void store(User user);

    void delete(long id);
}
