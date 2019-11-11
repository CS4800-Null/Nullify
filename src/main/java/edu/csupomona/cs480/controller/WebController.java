package edu.csupomona.cs480.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import edu.csupomona.cs480.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.provider.*;


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;
	@Autowired
	private WebsiteManager webManager;
	private WebsiteUtility websiteUtility = new WebsiteUtility();
	
	
	@RequestMapping(value = "/search/{websitename}", method = RequestMethod.GET)
	Website search(@PathVariable("websitename") String websitename)
	{
		System.out.println("search() reached webcontroller");
		Website site = websiteUtility.search(websitename);
		if(site == null)
			System.out.println("website not found");
		return site;
	}
	
	@RequestMapping(value = "/websites", method = RequestMethod.GET)
	ArrayList<Website> listWebsites() {
		System.out.println("website lister reached webcontroller");
		websiteUtility = new WebsiteUtility();
		return websiteUtility.sortAZ();
	}
	
	@RequestMapping(value = "/sortAZ", method = RequestMethod.GET)
	ArrayList<Website> listWebsitesAZ() {
		System.out.println("website lister reached webcontroller");
		return websiteUtility.sortAZ();
	}

	@RequestMapping(value = "/sortZA", method = RequestMethod.GET)
	ArrayList<Website> listWebsitesZA() {
		System.out.println("website lister ZA reached webcontroller");
		return websiteUtility.sortZA();
	}
//
	/**
	 * This method returns the contents of our sitelist.JSON file
	 * after parsing the file to an array of Website objects
	 * Uses GSON external library for parsing JSON data to custom Website Object
	 * Author - Jay Chen
	 * @returns Website[] read from sitelist.json (for use in handling website data)
	 */
	@RequestMapping(value = "/sitelist", method = RequestMethod.GET)
	Website[] sitelist() throws java.io.IOException
	{
		JSONReader js = new JSONReader();
		String file = "src/main/resources/static/sitelist.json";
		InputStream inputStream;
		inputStream = new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)); // had to convert string to input stream to work
		Website[] web = js.readWebsiteJSON(inputStream);
		return web;
	}

	/**
	 * This method will update our sitelist with newly added websites
	 */
	@RequestMapping(value = "/cs480/newwebsite/{websitenameinput}", method = RequestMethod.POST)
	Website addWebsite(
			@PathVariable("website") String wesite,
			@RequestParam("domain") String domain,
			@RequestParam(value = "settings", required = false) String settings,
			@RequestParam(value = "changepassword", required = false) String changepassword,
			@RequestParam(value = "deleteaccount", required = false) String deleteaccount,
			@RequestParam(value = "notes", required = false) String notes,
			@RequestParam(value = "image", required = false) String image) {
		System.out.println("inside addwebsite webcontroller");
		image = "static/logos/resized/zzimg.jpg";
		Website website = new Website(wesite, domain, settings, changepassword, deleteaccount, notes, image);
		websiteUtility.addWebsite(website);
		return website;
	}
}

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
/**	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}

	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
/**	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
/**	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
/**	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
/**	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
/**	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

	/**
	 * Webpage for user's personal website list
	 *
	 * Author - Jeane Taruc
	 * //@param ??
	 */
/**	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public String mylist()
	{
        return "My website list";
	}
}
//
	/**
	 * This method returns the contents of our sitelist.JSON file
	 * after parsing the file to an array of Website objects
	 * Uses GSON external library for parsing JSON data to custom Website Object
	 * used for testing contents of sitelist
	 * @returns Website[] read from sitelist.json (for use in handling website data)
	 *
	@RequestMapping(value = "/sitelist", method = RequestMethod.GET)
	Website[] sitelist() throws java.io.IOException
	{
		JSONReader js = new JSONReader();
		String file = "src/main/resources/static/sitelist.json";
		Website[] web = js.readWebsiteJSON(file);
		return web;
	}*/