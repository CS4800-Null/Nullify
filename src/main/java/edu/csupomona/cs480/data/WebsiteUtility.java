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
			System.out.println("file issue");
		}
	}
	
	public ArrayList<Website> allWebsites()
	{
		return new ArrayList<Website>(Arrays.asList(sitelist));
	}

	//sorting method

	//filtering method (can delete or not)

	//search method
		public Website search(String webName)
		{
			int idx = Arrays.binarySearch(sitelist, webName);
			if(idx == -1)
				return null;
			return sitelist[idx];
		}

	//image file retrieval method
}
