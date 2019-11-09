package edu.csupomona.cs480.controller;

import java.util.ArrayList;

import edu.csupomona.cs480.data.WebsiteUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.*;

import edu.csupomona.cs480.data.JSONReader;
import edu.csupomona.cs480.data.Website;


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
		return websiteUtility.sortAZ();
	}

	@RequestMapping(value = "/sortZA", method = RequestMethod.GET)
	ArrayList<Website> listWebsitesZA() {
		System.out.println("website lister ZA reached webcontroller");
		return websiteUtility.sortZA();
	}
}
	
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