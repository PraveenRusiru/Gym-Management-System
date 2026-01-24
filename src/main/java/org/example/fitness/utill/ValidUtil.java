package org.example.fitness.utill;

public class ValidUtil {
    public static boolean isValidEmail(String email) {
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
              return email.matches(emailPattern);

    }
    public static boolean isValidName(String name) {
        String namePattern = "^[A-Za-z ]+$";
        return name.matches(namePattern);
    }
    public static boolean isValidPhone(String phone) {
        String phonePattern = "^0\\d{9}$";
        return phone.matches(phonePattern);
    }
    public static boolean isNicValid(String nic) {
        String nicPattern = "^([0-9]{9}[vVxX]|[0-9]{12})$";
        return nic.matches(nicPattern);
    }
    public  static  boolean isHeightValid(String height) {
        String heightPattern = "^(?:[1-2]?\\d{1,2}|300)$";
        return height.matches(heightPattern);
    }
    public  static  boolean isWeightValid(String weight) {
        String weightPattern ="^(?:[1-2]?\\d{1,2}|300)$";
        return weight.matches(weightPattern);
    }
    public static boolean isAgeValid(String age) {
        String agePattern = "^(?:1[0-4][0-9]|[1-9]?[0-9]|150)$";
        return age.matches(agePattern);
    }
    public static boolean isFatPercentageValid(String percentage) {
        String fatPercentagePattern ="^(100(\\.0{1,2})?|[0-9]{1,2}(\\.[0-9]{1,2})?)$";
        return percentage.matches(fatPercentagePattern);
    }
    public static boolean isStringValid(String string) {
        String pattern = "^[a-zA-Z0-9]+$";
        return string.matches(pattern);
    }
    public static boolean isPriceValid(String price) {
        String pricePattern="^\\$?\\d+(\\.\\d{1,2})?$";
        return price.matches(pricePattern);
    }
    public static boolean isIntegerValid(String integer) {
        String integerPattern = "^\\d+$";
        return integer.matches(integerPattern);
    }
}
