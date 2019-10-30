package edu.csupomona.cs480.data;

import java.io.*;

import com.google.gson.*;

public class JSONReader
{
	Gson gson = new Gson();

	public JSONReader() {	}

	public Website[] readWebsiteJSON(String filepath)
	{
		Website[] web = null;
		try
		{
			InputStream is = this.getClass().getResourceAsStream(filepath);
			web = gson.fromJson(new FileReader(new File(filepath)), Website[].class);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return web;
	}
	
	public void writeWebsiteJSON(String filepath, Website web)
	{
		try
		{
			String jsonString = gson.toJson(web);
			FileWriter fileWriter = new FileWriter(filepath);
			fileWriter.write(jsonString);
			fileWriter.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

