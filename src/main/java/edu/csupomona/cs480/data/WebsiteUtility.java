package edu.csupomona.cs480.data;

import java.io.IOException;
import java.io.File;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.util.ResourceResolver;
import javassist.NotFoundException;

public class WebsiteUtility
{
	private Website[] sitelist; //this stores the data from our JSON file
	private static final ObjectMapper JSON = new ObjectMapper();
	private final String sitelistPath = "src/main/resources/static/sitelist.json";

	public WebsiteUtility()
	{
		getData(sitelistPath);
	}

	public void getData(String file)
	{
		try
		{
			JSONReader js = new JSONReader();
			sitelist = js.readWebsiteJSON(file);
		}
		catch(Exception e)
		{
			System.out.println("file issue in " + file);
		}
	}
	
	private WebsiteMap getWebsiteJSON()
	{
		WebsiteMap web = null;
		File siteFile = ResourceResolver.getSiteFile();
		if (siteFile.exists()) {
			// read the file and convert the JSON content
			// to the Website object
			try {
				web = JSON.readValue(siteFile, WebsiteMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			web = new WebsiteMap();
		}
		return web;
	}
	
	private void persistWebsiteMap(WebsiteMap webMap) {
		try {
			// convert the Website object to JSON format
			JSON.writeValue(ResourceResolver.getSiteFile(), webMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Website> allWebsites()
	{
		return new ArrayList<Website>(Arrays.asList(sitelist));
	}

	//sorting method
	public Website[] sort()
	{
		Arrays.sort(sitelist);
		return sitelist;
	}
	
	//filtering method (can delete or not)
	public Website[] filter(Website website)
	{
		Website[] filtered = new Website[sitelist.length];
		
		for (int i = 0; i < sitelist.length; i++) {
			if (website.canDelete()) {
				filtered[i] = sitelist[i];
			}
		}
		return filtered;
	}
	

	//search method
		public Website search(String webName)
		{
			webName = webName.toLowerCase();
			System.out.println("search() reached websiteutility for search query: " + webName);
			for(Website w:sitelist)
			{
				if(w.getWebsite().equalsIgnoreCase(webName))
					return w;
			}
			return null;
		}
		
		private WebsiteMap siteMap()
		{
			getData(sitelistPath);
			WebsiteMap web = new WebsiteMap();
			System.out.println("search() reached siteMap()");
			for(Website w:sitelist)
			{
				web.put(w.getWebsite(),w);
			}
			return web;
		}
}
