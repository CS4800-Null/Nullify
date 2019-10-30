package edu.csupomona.cs480.data;

import java.io.IOException;
import java.io.File;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.util.ResourceResolver;
import javassist.NotFoundException;

public class WebsiteUtility
{
	private ArrayList<Website> sitelist; //this stores the data from our JSON file
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
			sitelist = new ArrayList<Website>(Arrays.asList(js.readWebsiteJSON(file)));
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
		return sitelist;
	}
	

	//sorting method
	public ArrayList<Website> sortAZ()
	{
		Arrays.sort(sitelist.toArray());
		return sitelist;
	}
	
	public Website[] sortZA()
	{
		Website[] sorted = (Website[])sortAZ().toArray();
		for(int i = 0; i < sorted.length/2; i++){
			
			Website w = sorted[i];
			sorted[i] = sorted[sorted.length - 1 - i];
			sorted[sorted.length - 1 - i] = w;
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
		// add website
	public void addWebsite(Website website) {
		File jsonFile = new File(sitelistPath);
		sitelist.add(website);
		// this will append information to our json file
		try {
			JSONReader js = new JSONReader();
			js.writeWebsiteJSON(sitelistPath, sitelist);
			// inserted Json Writer above
		} catch (Exception e) {
			System.out.println("Something went wrong with the addWebsite");
		}
	}
}
