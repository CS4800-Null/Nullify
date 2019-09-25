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

	public File generateWebsiteJSON(Website[] websites) throws IOException
	{
		for(Website web:websites)
		{
			String str = gson.toJson(web);
			try
			{
				JsonWriter writer = new JsonWriter(new FileWriter("temp.json"));
				writer.setIndent("    ");
                buildWebsiteArray(writer, websites);
                writer.close();

			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return new File("temp.json");
	}

	private void buildWebsiteArray(JsonWriter writer, Website[] websites)
	{
		try
		{
			writer.beginArray();
			for(Website web:websites)
			{
				encapsulateWebsite(writer, web);
			}
			writer.endArray();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private void encapsulateWebsite(JsonWriter writer, Website web)
	{
		try
		{
			writer.beginObject();
			writer.name("websitename").value(web.getWebsite());
			writer.name("domain").value(web.getDomain());
			writer.name("settings").value(web.getSettings());
			writer.name("changepass").value(web.getChangePass());
			writer.name("deleteaccount").value(web.getDeleteAcct());
			writer.name("notes").value(web.getNotes());
			writer.name("candelete").value(web.canDelete());
			writer.endObject();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

