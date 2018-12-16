/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author dstarcenko
 */
public class UtilRegexHelper {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    //Minimum 4 characters + numbers
    private static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9._-]{4,}$", Pattern.CASE_INSENSITIVE);
    
    //Minimum(8 characters, 1 number, 1 lowercse letter, 1 uppercase letter, 1 special character)
    private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!*@#$%^&+=])(?=\\S+$).{8,}$", Pattern.UNICODE_CASE);

    private UtilRegexHelper() {
    }
    
    public static boolean validateEmail(String email){
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }
    
    public static boolean validUsername(String username){
        return VALID_USERNAME_REGEX.matcher(username).find();
    }
    
    public static boolean validPassword(String password){
        return VALID_PASSWORD_REGEX.matcher(password).find();
    }
}
