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

	public WebsiteUtility()
	{
		getData();
	}

	public void getData()
	{
		try
		{
			JSONReader js = new JSONReader();
			String file = "src/main/resources/static/sitelist.json";
			sitelist = js.readWebsiteJSON(file);
		}
		catch(Exception e)
		{
			System.out.println("file issue in sitelist.json");
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
			int idx = Arrays.binarySearch(sort(), webName);
			if(idx == -1)
				return null;
			return sitelist[idx];
		}
}
