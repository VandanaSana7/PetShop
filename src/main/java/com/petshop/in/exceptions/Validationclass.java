package com.petshop.in.exceptions;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.petshop.in.enums.transaction_status;


public class Validationclass {
	public static boolean ValidationInt(String str) {
		try {
			Integer.parseInt(str);
			return false;
		}
		catch(NumberFormatException e) {
			return true;
		}
		
	}
	

	
	public static boolean ValidationDouble(String str) {
		try {
		Double.parseDouble(str);
		return false;
		}
		catch(NumberFormatException e) {
			return true;
		}
		
		
	}
	
	

	
	 public static boolean isValidDateFormat(Date date) {
	        // Convert Date object to String
//	        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
//	        // Check if the date string matches the format "yyyy-MM-dd"
//	        try {
//	            LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
//	           
//	            return true;
//	        } catch (Exception e) {
//	            return false;
//	        }
		  if (date == null) {
	            return false; // Null date object
	        }

	        try {
	            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	            String dateString = dateFormatter.format(date); // Convert Date object to string
	            dateFormatter.parse(dateString); // Try parsing the string back to a Date object
	            return true; // Valid date format
	        } catch (Exception e) {
	            return false; // Invalid date format or parsing error
	        }
	    }

	public static boolean validateTransactionStatus(transaction_status status) {
        // Check if the status is either "Success" or "Failed"
        if (status == null || (status != transaction_status.Success) ||( status != transaction_status.Failed)) {
            return false;
        }
		return true;
		
	}

	
	public static boolean ValidationInt1(int id) {
		try {
		Integer.parseInt(String.valueOf(id));
		return false;
		}
		catch(NumberFormatException e) {
			return true;
		}
	}
	
	public static boolean ValidationString(int id) {
		try {
			Integer.parseInt(String.valueOf(id));
			return false;
		}
		catch(NumberFormatException e){
			return true;
		}
	}
	
	public static boolean ValidationDate(String str) {
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        try {
            // Parse the string to Date using the formatter
            Date date = formatter.parse(str);
            return false;
        } catch (ParseException e) {
            return true;
        }
	}

}

    


	
	





