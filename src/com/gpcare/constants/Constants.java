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
	
	public static String USER_UPDATE_PROFILE =   "http://devlpconsole.com/hospital/userProfileUpdate.php";
	public static String DOCTOR_UPDATE_PROFILE =   "http://devlpconsole.com/hospital/userLogin.php";
	
	public static String USER_REGISTER =   "http://devlpconsole.com/hospital/userRegistration.php";
	public static String STUFF_REGISTER =   "http://devlpconsole.com/hospital/stuffRegistration.php";
	public static String USER_LIST =   "http://devlpconsole.com/hospital/userList.php";
	
	public static String USER_PRESCRIPTION = "http://devlpconsole.com/hospital/addPrescription.php";
	public static String FETCH_PRESCRIPTION = "http://devlpconsole.com/hospital/getUserPrescription.php";
	
	public static String ADD_MESSAGE = "http://devlpconsole.com/hospital/addMessage.php";
	
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
