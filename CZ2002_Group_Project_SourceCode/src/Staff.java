/* Version 1.0
 * @author: Sanskar
 * Last Edit at: 12 October 2021 at 16.40 PM SGT
 * Last Edit by: Sanskar*/

public class Staff 
{
	private String name;
	private String gender;
	private int EmpID;
	private String job_title;
	public Staff()
	{
		name = "";
		gender = "";
		EmpID = 0;
		job_title = "";
	}
	public Staff(String name,String gender,int EmpID,String job_title)
	{
		this.name = name;
		this.gender = gender;
		this.EmpID = EmpID;
		this.job_title = job_title;
	}
	public String getname()
	{
		return name;
	}
	public String getgender()
	{
		return gender;
	}
	public String gettitle()
	{
		return job_title;
	}
	public int getID()
	{
		return EmpID;
	}
	public void setname(String name)
	{
		this.name=name;
	}
	public void setgender(String gender)
	{
		this.gender=gender;
	}
	public void setEmpID(int EmpID)
	{
		this.EmpID=EmpID;
	}
	public void setjob(String job_title)
	{
		this.job_title=job_title;
	}

}
