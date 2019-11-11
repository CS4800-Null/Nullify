package edu.csupomona.cs480.data;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.*;

public class JSONReader
{
	Gson gson = new Gson();

	public JSONReader() {	}
	
	public String readTextFile(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		byte buf[] = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
		
		}
		return outputStream.toString();
	}
	
	public Website[] readWebsiteJSON(InputStream filepath)
	{
		String file = readTextFile(filepath);
		Website[] web = null;
		try
		{
			//InputStream is = this.getClass().getResourceAsStream(file);
			web = gson.fromJson(file, Website[].class);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return web;
	}
	
	public void writeWebsiteJSON(String filepath, ArrayList<Website> web)
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

