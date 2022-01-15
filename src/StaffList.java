/* Version 2.2
 * @author: Sanskar
 * Last Edit at: 29 October 2021 at 4:00 PM SGT
 * Last Edit by: Harsh*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class StaffList 
{
	public static ArrayList<Staff> stafflist = new ArrayList<Staff>();
	public static void saveStaffList()
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream("StaffList.txt");
			PrintWriter pw = new PrintWriter(fos);
			/* Convention for Staff
			 * Name
			 * Gender
			 * EmpId
			 * job_title*/
			for(Staff s : stafflist)
			{
				pw.println(s.getname());
				pw.println(s.getgender());
				pw.println(s.getID());
				pw.println(s.gettitle());
			}
			pw.println("-1");
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public static void loadStaffList()
	{
		 String name;
		 String gender;
		 int empId;
		 String job_title;
		try 
		{
			FileInputStream fis = new FileInputStream("StaffList.txt");
			Scanner sc2 = new Scanner(fis);
			while(sc2.hasNext())
			{
				name = sc2.nextLine();
				//System.out.println("Name: "+ name);
				if(name.equals("-1"))
					break;
				gender = sc2.next();
				//System.out.println("Gender: "+ gender);
				empId = sc2.nextInt();
				//System.out.println("empId" + empId);
				sc2.nextLine();
				job_title = sc2.nextLine();
				//System.out.println("Job Title: " + job_title);
				Addstaff(name, gender, empId, job_title);
			}
			sc2.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error - Staff File Not Found");
		}
		catch (Exception e)
		{
			System.out.println("Error - Staff File Corrupted");
		}
	}
	public static void Addstaff(String name,String gender,int EmpID,String job_title)
	{
		Staff s = new Staff(name,gender,EmpID,job_title);
		stafflist.add(s);
	}
	public static int Removestaff(int EmpID)
	{
		if(stafflist.size()==0)
		{
			System.out.println("There are no staff members!");
			return -1;
		}
		for(Staff s:stafflist)
		{
			if(s.getID()==EmpID)
			{
				stafflist.remove(s);
				return 0;
			}
		}
		return 1;
	}
	public static void getDetails(int EmpID)
	{
		for(Staff s:stafflist)
		{
			if(s.getID()==EmpID)
			{
				System.out.println("Name: "+s.getname()+"\nGender: "+s.getgender()+
						"\nEmployee ID: "+s.getID()+"\nJob title: "+s.gettitle());
				return;
			}
		}
		System.out.println("No such employee id!");
	}
	public static void dispStaff()
	{
		if(stafflist.size()==0)
		{
			System.out.println("There are no staff members!");
			return;
		}
		System.out.println("The list of all employees:\n"+"EmpID\t\t\t\t\tName");
		for(Staff s:stafflist)
		{
			System.out.println(s.getID()+"\t\t\t\t\t"+s.getname());
		}
	}
	public static String getName(int empId)
	{
		for(Staff s: stafflist)
		{
			if(s.getID() == empId)
				return s.getname();
		}
		return "(No Staff)";
	}

}
