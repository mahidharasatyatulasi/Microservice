package com.cg.blogging.service;

import com.cg.blogging.entities.User;
import com.cg.blogging.repository.IUserRepository;

public interface IUserService  {
public  User addNewUser(User user);
public User signIn(User user);
public User signOut(User user);
}
