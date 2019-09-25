package edu.csupomona.cs480.data;

import java.io.*;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

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
}

