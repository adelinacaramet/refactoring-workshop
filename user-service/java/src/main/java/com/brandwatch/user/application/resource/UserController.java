package com.brandwatch.user.application.resource;

import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brandwatch.user.domain.model.User;
import com.brandwatch.user.domain.model.UserBuilder;
import com.brandwatch.user.domain.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody User find(@PathVariable(value = "userId") long userId) {
        return userRepository.find(userId);
    }

    @RequestMapping(value="", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<User> findAll(@RequestParam(value="enabled", required = false) Boolean enabled) {
        if(enabled == null) {
            return userRepository.findAll();
        }
        if(enabled) {
            final List<User> enabledUsers = Lists.newArrayList();
            final List<User> allUsers = userRepository.findAll();
            for(User user : allUsers) {
                if(isUserEnabled(user)) {
                    enabledUsers.add(user);
                }
            }
            return enabledUsers;
        } else {
            final List<User> disabledUsers = Lists.newArrayList();
            final List<User> allUsers = userRepository.findAll();
            for(User user : allUsers) {
                if(!isUserEnabled(user)) {
                    disabledUsers.add(user);
                }
            }
            return disabledUsers;
        }
    }

    public boolean isUserEnabled(User user) {
        if(user.isEnabled()) {
            return true;
        }
        return false;
    }

    @RequestMapping(value="", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public void store(@RequestParam(value="id", required = true) long id,
        @RequestParam(value="username", required = true) String username,
        @RequestParam(value="email", required = true) String email,
        @RequestParam(value="firstName", required = true) String firstName,
        @RequestParam(value="lastName", required = true) String lastName,
        @RequestParam(value="phone", required = false) String phone) {

        final User user = new UserBuilder().withId(id)
                .withUsername(username)
                .withEmail(email)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPhone(phone)
                .withEnabled(true)
                .build();


        userRepository.store(user);
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(value = "userId") long userId) {
        userRepository.delete(userId);
    }
}
