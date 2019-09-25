package edu.csupomona.cs480.data;

public class Website
{
	public String website;
	public String domain;
	public String settings;
	public String changepassword;
	public String deleteaccount;
	public String notes;

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
