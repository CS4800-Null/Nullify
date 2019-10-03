package edu.csupomona.cs480.data;

import java.io.IOException;

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

	//image file retrieval method
}
