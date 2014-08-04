package com.gpcare.constants;

import android.content.SharedPreferences;

public class Constants {
	
	public static SharedPreferences preference = null;
	public static boolean ISLOGIN = false;
	public static int STATUS_CODE ;
	public static boolean ISUPDATESKILL = false;
	
	public static String setDesignationName = null;
	public static String StateName,StateID,CityName,CityId;
	public static int USER = 1;
	public static int DOCTOR = 2;
	public static int ADMIN  =3;
	
	public static String IMAGEPATH = "http://clickfordevelopers.com/demo/seedstir/upload/professional/";
	public static String ADMIN_LOGIN =   "http://devlpconsole.com/hospital/adminLogin.php";
	public static String USER_LOGIN =   "http://devlpconsole.com/hospital/userLogin.php";
	public static String DOCTOR_LOGIN =   "http://devlpconsole.com/hospital/userLogin.php";
	
	public static String USER_UPDATE_PROFILE =   "http://devlpconsole.com/hospital/userLogin.php";
	public static String DOCTOR_UPDATE_PROFILE =   "http://devlpconsole.com/hospital/userLogin.php";
	
	public static String USER_REGISTER =   "http://devlpconsole.com/hospital/userRegistration.php";
	
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
