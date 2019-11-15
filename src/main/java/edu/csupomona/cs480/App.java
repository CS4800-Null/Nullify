//ya boi has it working in intellij now
package edu.csupomona.cs480;

import edu.csupomona.cs480.data.provider.WebsiteManager;
import edu.csupomona.cs480.service.UserService;
import edu.csupomona.cs480.service.UserServiceImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.csupomona.cs480.dao.UserDao;
import edu.csupomona.cs480.dao.UserDaoImpl;
import edu.csupomona.cs480.data.provider.FSUserManager;
  import edu.csupomona.cs480.data.provider.UserManager;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {

    /**
     * This is a good example of how Spring instantiates
     * objects. The instances generated from this method
     * will be used in this project, where the Autowired
     * annotation is applied.
     */
    @Bean
    public UserManager userManager() {
        UserManager userManager = new FSUserManager();
        return userManager;
    }
    
    @Bean
    public WebsiteManager webManager() {
        WebsiteManager webManager = new WebsiteManager();
        return webManager;
    }


    @Bean
    public UserService userService()
    {
    	UserService userService = new UserServiceImpl();
    	return userService;
    }
    @Bean
    public UserDao userDao()
    {
    	UserDao userDao = new UserDaoImpl();
    	return userDao;
    }
    /**
     * This is the running main method for the web application.
     * Please note that Spring requires that there is one and
     * ONLY one main method in your whole program. You can create
     * other main methods for testing or debugging purposes, but
     * you cannot put extra main method when building your project.
     */
    public static void main(String[] args) throws Exception {
        // Run Spring Boot
        SpringApplication.run(App.class, args);
    }
}
