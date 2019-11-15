package edu.csupomona.cs480.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csupomona.cs480.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.provider.*;
import edu.csupomona.cs480.model.Login;
import edu.csupomona.cs480.model.User;
import edu.csupomona.cs480.service.UserService;


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
	public UserService userService;
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
		return site;
	}
	
	@RequestMapping(value = "/websites", method = RequestMethod.GET)
	ArrayList<Website> listWebsites() {
		System.out.println("website lister reached webcontroller");
		//websiteUtility = new WebsiteUtility();
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

	/**
	 * This method returns the contents of our sitelist.JSON file
	 * after parsing the file to an array of Website objects
	 * Uses GSON external library for parsing JSON data to custom Website Object
	 * used to check sitelist.json output
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
	Website addWebsiteAlt(
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

	/**
	 * This method will update our sitelist with newly added websites
	 * This will be hardcoded in to add a single website as proof that the java works
	 */
	@RequestMapping(value = "/hardAdd}", method = RequestMethod.POST)
	Website addWebsite() {
		System.out.println("inside addwebsite webcontroller");
		String image, wesite, domain,settings,changepassword,deleteaccount,notes;
		wesite = "Google";
		domain = "www.google.com";
		settings = "settings text";
		changepassword = "password text";
		deleteaccount = "delete account text";
		notes = "notes text";
		image = "static/logos/resized/zzimg.jpg";
		Website website = new Website(wesite, domain, settings, changepassword, deleteaccount, notes, image);
		websiteUtility.addWebsite(website);
		return website;
	}
	
	
	@RequestMapping(value = "/candelete", method = RequestMethod.GET)
	ArrayList<Website> listWebsitesCD() {
		System.out.println("website lister candelete reached webcontroller");
		return websiteUtility.canDelete();
	}
	
	@PostMapping(value = "/registerProcess")
	  public String addUser(@ModelAttribute("user") User user, ModelMap model) {
	    userService.register(user);
	    model.addAttribute("username", user.getUsername());

	    return "welcome " + user.getUsername() + "!" ;
	  }
    @PostMapping(value = "/loginProcess")
	  public String login(@ModelAttribute("login") Login login, BindingResult bindingResult, ModelMap model) {

	    User user = userService.validateUser(login);

	    boolean isValidUser = false;

	    if (null != user && user.getUsername().equals(login.getUsername())
	        && user.getPassword().equals(login.getPassword())) {
	      isValidUser = true;
	      model.addAttribute("username", user.getUsername());
	    }

	    return isValidUser ? "welcome " + user.getUsername() + "!" : "Login Failed.";
	  }
}