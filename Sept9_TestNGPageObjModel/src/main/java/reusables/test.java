package reusables;

import java.util.Hashtable;



import xls.ShineXlsReader;

public class test {
	
public static 	ShineXlsReader xls;
public static Hashtable<String,String> ht;
	
	
	public static void main(String args[])
{
		
		ht = new Hashtable<String,String>();
		xls = new ShineXlsReader(System.getProperty("user.dir")+"\\Test_IP_OP\\ModuleDriver.xlsx");
		int Module_count = xls.getRowCount("Module");
		for (int i =2;i<=Module_count;i++) {
			String Module_name = xls.getCellData("Module", 0, i);
			String Module_Exests = xls.getCellData("Module", 1, i);
			
			if(Module_Exests.equalsIgnoreCase("Yes"))
			{
				int Testcases = xls.getRowCount(Module_name);
				for(int j=2;j<=Testcases;j++)
				{ 	 	 	 	
					String Testcase_name = xls.getCellData(Module_name, 0, j);
					String Testcase_Exests = xls.getCellData(Module_name, 1, j);
					
					ht.put(Testcase_name, Testcase_Exests);
					
				}
			}
			
		}
		System.out.println("values in ht are :"+ht);
	}
}
