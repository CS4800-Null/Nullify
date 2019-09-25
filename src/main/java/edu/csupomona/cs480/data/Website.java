package edu.csupomona.cs480.data;

public class Website
{
	private String website;
	private String domain;
	private String settings;
	private String changepassword;
	private String deleteaccount;
	private String notes;

	public Website(String wn, String d, String s, String cp, String da, String n)
	{
		website = wn;
		domain = d;
		settings = s;
		changepassword = cp;
		deleteaccount = da;
		notes = n;
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

	public boolean canDelete()
	{
		return (deleteaccount == "null");
	}
}
