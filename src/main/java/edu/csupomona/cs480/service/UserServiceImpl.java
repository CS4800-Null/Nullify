package edu.csupomona.cs480.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.csupomona.cs480.dao.UserDao;
import edu.csupomona.cs480.model.Login;
import edu.csupomona.cs480.model.User;

public class UserServiceImpl implements UserService {
  @Autowired
  public UserDao userDao;

  public void register(User user) {
    userDao.register(user);
  }

  public User validateUser(Login login) {
    return userDao.validateUser(login);
  }

}