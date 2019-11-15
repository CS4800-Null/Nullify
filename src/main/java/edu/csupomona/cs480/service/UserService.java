package edu.csupomona.cs480.service;

import edu.csupomona.cs480.model.Login;
import edu.csupomona.cs480.model.User;

public interface UserService {

  void register(User user);

  User validateUser(Login login);
}