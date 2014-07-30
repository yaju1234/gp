package com.gpcare.constants;

import android.content.SharedPreferences;

public class Constants {
	
	public static SharedPreferences preference = null;
	public static boolean ISLOGIN = false;
	public static int STATUS_CODE ;
	public static boolean ISUPDATESKILL = false;
	
	public static String setDesignationName = null;
	public static String StateName,StateID,CityName,CityId;
	
	public static String image_path = "http://clickfordevelopers.com/demo/seedstir/upload/professional/";
	public static String company_image_path = "http://clickfordevelopers.com/demo/seedstir/upload/company/";
	public static String company_list_image_path = "http://clickfordevelopers.com/demo/seedstir/upload/company_photos/";

	public enum values{
		USRINFO,
		USERNAME,
		PASSWORD,
		IS_SIGNIN,
		
		
		COMPANY_INFO,
		COMPANY_SIGNIN_STATUS,
		
		/**For company*/
		COMPANY_EMAIL,
		COM_USERID,
		COM_PROFILEID,
		COMPANY_NAME,
		COM_LOGO,
		COMPANY_DESCRIPTION,
		COMPANY_LOCATION,
		COMPANY_TYPE,
		
		/**For Individual**/
		USER_SIGNIN_STATUS,
		
		GENDER,
		INDIVIDUAL_EMAIL,
		INDI_USERID,
		INDI_PROFILEID,
		INDI_FIRSTNAME,
		INDI_LASTNAME,
		INDI_ADDRESS,
		INDI_PROFILEPIC,
		INDI_PROFILEDESC,
		INDI_UNIVERSITY
		
		}
}
