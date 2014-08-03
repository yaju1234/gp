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
	
	public enum values{
		USRINFO,
		IS_SIGNIN,		
		USER_SIGNIN_STATUS,		
		GENDER,
		EMAIL,		
		USERID,
		PROFILEID,
		FIRSTNAME,
		LASTNAME,
		ADDRESS,
		PROFILEPIC,
		DOB
		
		
		}
}
