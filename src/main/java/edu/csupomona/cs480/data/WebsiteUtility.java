package edu.csupomona.cs480.data;

import java.io.*;
import java.util.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.util.ResourceResolver;
import javassist.NotFoundException;

public class WebsiteUtility
{
	private ArrayList<Website> sitelist; //this stores the data from our JSON file
	private static final ObjectMapper JSON = new ObjectMapper();
	private final String sitelistString = "static/sitelist.json";
	private File sitelistFile;
	
	public WebsiteUtility()
	{
		Resource resource = new ClassPathResource(sitelistString);
		//sitelistFile = new File("");
		InputStream input = null;
		try
		{
			System.out.println("HELP");
			input = resource.getInputStream();
		//	sitelistFile = resource.getFile();
		//	input = this.getClass().getResourceAsStream(sitelistString);
		}
		catch(Exception io)   {   System.out.println("failure at sitelist.json rsrc access");    }
		getData(input);
	}

	public void getData(InputStream file)
	{
		try
		{
			System.out.println("reached getData()");
			JSONReader js = new JSONReader();
			sitelist = new ArrayList<Website>(Arrays.asList(js.readWebsiteJSON(file)));
		}
		catch(Exception e)
		{
			System.out.println("getData file issue in " + file.toString());
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
		return sitelist;
	}
	

	//sorting method
	public ArrayList<Website> sortAZ()
	{
		int n = sitelist.size();
		ArrayList<Website> az = sitelist;
		for (int j = 1; j < n; j++) {
			Website key = az.get(j);
			int i = j-1;
			while ( (i > -1) && ( az.get(i).compareTo(key) > 0 ) )
			{
				az.set(i+1, az.get(i));
				i--;
			}
			az.set(i+1, key);
		}
		return az;
		//Arrays.sort(sitelist.toArray());
		//return sitelist;
	}
	
	public ArrayList<Website> sortZA()
	{
		ArrayList<Website> sorted = sortAZ();
		for(int i = 0; i < sorted.size()/2; i++){
			
			Website w = sorted.get(i);
			sorted.set(i, sorted.get(sorted.size() - 1 - i));
			sorted.set(sorted.size() - 1 - i, w);
		}
		return sorted;
	}
	
	//filtering method (can delete or not)
	public Website[] filter()
	{
		Website[] filtered = new Website[sitelist.size()];
		int idx = 0;
		for (int i = 0; i < sitelist.size(); i++) {
			if (sitelist.get(i).canDelete())
			{
				filtered[idx] = sitelist.get(i);
				idx++;
			}
		}
		return filtered;
	}
	

	//search method
		public Website search(String webName)
		{
			System.out.println("search() reached websiteutility for search query: " + webName);
			for(Website w:sitelist)
			{
				if(w.getWebsite().equalsIgnoreCase(webName))
					return w;
			}
			return null;
		}
		
		public WebsiteMap siteMap()
		{
			//getData(sitelistFile);
			WebsiteMap web = new WebsiteMap();
			System.out.println("search() reached siteMap()");
			for(Website w:sitelist)
			{
				web.put(w.getWebsite(),w);
			}
			return web;
		}
		
		// add website
		public void addWebsite(Website website)
		{
			System.out.println("reached add website in web controller");
		}

}
