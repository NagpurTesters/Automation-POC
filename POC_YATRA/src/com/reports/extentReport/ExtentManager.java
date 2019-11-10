/**
 * 
 */
package extentReport;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;


/**
 * @author Satish
 *
 */
public class ExtentManager {

	 
	private static ExtentReports extent;
	static String reportPath="D://report//SkillsLiving//reports.html";
	
	public static ExtentReports getInstance(){
		if(extent == null){
		
			extent=new ExtentReports(reportPath,true,DisplayOrder.NEWEST_FIRST);
			
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			extent.addSystemInfo("Selenium Version","3.5.3").addSystemInfo("Environment","QA");
			extent.addSystemInfo("Host Name", "HumaneBITS");
			extent.addSystemInfo("OS", "Windows 10");
			extent.addSystemInfo("User Name", "Satish Kamble");
		
			
		}
		return extent;
	}

}
