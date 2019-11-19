package edu.csupomona.cs480.model;


import edu.csupomona.cs480.data.Website;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The basic user object.
 */
public class User
{
	
	HashMap<String, ArrayList<Website>> folders = new HashMap<String, ArrayList<Website>>();
	
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
	
	public String getUsername()
	{
		return UserName;
	}
	
	public void setUsername(String UserName)
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
	
	public boolean addFolder(String name)
	{
		if (folders.containsKey(name))
		{
			System.out.println("Error, " + name + " already exists");
			return false;
		}
		folders.put(name, new ArrayList<Website>());
		return true;
	}
	
	public void addItem(String name, Website item)
	{
		if (folders.get(name) == null)
		{
			folders.put(name, new ArrayList<Website>());
		}
		folders.get(name).add(item);
	}
	
	public ArrayList<String> getFolderNames()
	{
		ArrayList<String> names = new ArrayList<>(folders.keySet());
		return names;
	}
	
	public ArrayList<Website> getFolder(String name)
	{
		return folders.get(name);
	}
	
}
