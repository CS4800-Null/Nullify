package edu.csupomona.cs480.data;

import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;

import java.util.Date;


/**
 * The basic user object.
 */

public class User
{
    /**
     * Websites the user have accounts for
     */
    private String UserName;
    /**
     * Email of the user
     */
    private String email;
    /**
     * Password of the user
     */
    private String password;
    /**
     * List of Websites the user have accounts for
     **/
    private String UserSites[];
    
    public String getUserName()
    {
        return UserName;
    }
    
    public void setUserName(String UserName)
    {
        this.UserName = UserName;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;//DigestUtils.sha256(password);
    }
    
    public String[] getUserSites()
    {
        return UserSites;
    }
    
    public void setUserSites(String[] UserSites)
    {
        this.UserSites = UserSites;
    }
    
}