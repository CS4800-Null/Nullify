package edu.csupomona.cs480.dao;

import edu.csupomona.cs480.model.Login;
import edu.csupomona.cs480.model.User;
public interface UserDao {
  void register(User user);
  User validateUser(Login login);
}