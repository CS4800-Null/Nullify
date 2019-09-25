package edu.csupomona.cs480.data;

public class Website
{
	public String websitename;
	public String domain;
	public String settings;
	public String changepass;
	public String deleteacct;
	public String notes;
	public boolean candelete;

	public Website(String wn, String d, String s, String cp, String da, String n)
	{
		websitename = wn;
		domain = d;
		settings = s;
		changepass = cp;
		deleteacct = da;
		notes = n;
		candelete = (deleteacct == "null");
	}

	public String getWebsite()
	{
		return websitename;
	}

	public String getDomain()
	{
		return domain;
	}

	public String getSettings()
	{
		return settings;
	}

	public String getChangePass()
	{
		return changepass;
	}

	public String getDeleteAcct()
	{
		return deleteacct;
	}

	public String getNotes()
	{
		return notes;
	}

	public boolean canDelete()
	{
		return candelete;
	}
}
