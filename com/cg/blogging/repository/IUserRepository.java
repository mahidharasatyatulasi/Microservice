package com.cg.blogging.repository;

import com.cg.blogging.entities.User;

public interface IUserRepository {
public  User addNewUser(User user);
public User signIn(User user);
public User signOut(User user);
}
