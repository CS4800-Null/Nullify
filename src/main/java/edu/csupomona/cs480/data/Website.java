package edu.csupomona.cs480.data;

import javax.persistence.*;

@Entity
public class Website implements Comparable<Website>
{
	@Id
	private String website;
	private String domain;
	private String settings;
	private String changepassword;
	private String deleteaccount;
	private String notes;
	private String image;

	public Website(String wn, String d, String s, String cp, String da, String n, String i)
	{
		website = wn;
		domain = d;
		settings = s;
		changepassword = cp;
		deleteaccount = da;
		notes = n;
		image = i;
	}

	public String getWebsite()
	{
		return website;
	}

	public String getDomain()
	{
		return domain;
	}

	public String getSettings()
	{
		return settings;
	}

	public String getChangepassword()
	{
		return changepassword;
	}

	public String getDeleteaccount()
	{
		return deleteaccount;
	}

	public String getNotes()
	{
		return notes;
	}
	
	public String getImage()
	{
		return image;
	}

	public boolean canDelete()
	{
		return (!deleteaccount.equals("null"));
	}
	
	@Override
	public int compareTo(Website other)
	{
		return this.website.compareTo(other.getWebsite());
	}
}
