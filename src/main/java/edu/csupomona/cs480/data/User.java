package edu.csupomona.cs480.data;

import org.hibernate.mapping.Map;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;



/**
 * The basic user object.
 */
public class User
{
	
	HashMap<String, ArrayList<Website>> folder = new HashMap<String, ArrayList<Website>>();
	
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
    /**
     * List of tags the user made
     **/
    private String TaggedSites[];
    
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

    public void addFolder(String name) 
    {
    	if (folder.containsKey(name)) 
    	{
    		System.out.println("Error, " + name + " already exists");
    	} 
    	else
    	{
    		folder.put(name, new ArrayList<Website>());
    	}
    }
    
    public void addItem(String name, Website item) 
    {
    	if (folder.get(name) == null)
    	{
    		folder.put(name, new ArrayList<Website>());
    	}
    	
    	folder.get(name).add(item);
    }
    
}
