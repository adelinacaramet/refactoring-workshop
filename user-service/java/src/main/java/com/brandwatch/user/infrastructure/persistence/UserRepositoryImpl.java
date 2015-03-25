package com.brandwatch.user.infrastructure.persistence;

import java.util.List;

import com.google.common.base.Optional;
import org.springframework.stereotype.Repository;

import com.brandwatch.user.domain.model.User;
import com.brandwatch.user.domain.repository.UserRepository;
import com.brandwatch.user.infrastructure.exception.ResourceExistsException;
import com.brandwatch.user.infrastructure.exception.ResourceNotFoundException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    static List<User> users = UserData.getUsers();

    @Override
    public User find(long id) {
        for(User user : users) {
            if(id == user.getId()) {
                return user;
            }
        }
        throw new ResourceNotFoundException("Could not find user with id " + id + "." );
    }

    @Override
    public List<User> findAll() {
        if(users.size() > 0 ) {
            return users;
        }

        throw new ResourceExistsException("We could not find any users");
    }

    @Override
    public void store(User u) {
        if(users.size() > 0 ) {
            for(User user : users) {
                if(u.getId() == user.getId()) {
                    throw new ResourceExistsException("User with id " + u.getId() + " already exists.");
                }
            }
        }

        users.add(u);
    }

    @Override
    public void delete(long id) {
        if(users.size() > 0) {
            boolean flag = false;
            for(int i = users.size() - 1; i >= 0; i--) {
                if(id == users.get(i).getId()) {
                    flag = true;
                    users.remove(i);
                    continue;
                }
            }

            if(!flag) {
                throw new ResourceNotFoundException("Could not find user with id " + id + "." );
            }
        } else {
            throw new ResourceNotFoundException("Could not find user with id " + id + "." );
        }
    }
}
