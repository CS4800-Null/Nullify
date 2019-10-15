package edu.csupomona.cs480.data;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.util.ResourceResolver;

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

	//sorting method

	//filtering method (can delete or not)

	//search method
		public Website search(String webName)
		{
			int i = 0;
			while (sitelist[i].getWebsite() != webName)
			{
				i++;
			}
			return sitelist[i];
		}

	//image file retrieval method
}
