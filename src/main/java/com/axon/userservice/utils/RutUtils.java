package com.axon.userservice.utils;

public class RutUtils {

    public static boolean validateRut(Long rut, String dv) {
        if (rut == null || dv == null || dv.isEmpty()) {
            return false;
        }

        String rutStr = rut.toString();
        if (rutStr.length() < 7 || rutStr.length() > 8) {
            return false;
        }

        int sum = 0;
        int multiplier = 2;

        for (int i = rutStr.length() - 1; i >= 0; i--) {
            sum += Character.getNumericValue(rutStr.charAt(i)) * multiplier;
            multiplier = (multiplier == 7) ? 2 : multiplier + 1;
        }

        int remainder = 11 - (sum % 11);
        String expectedDv = (remainder == 11) ? "0" : (remainder == 10) ? "K" : String.valueOf(remainder);

        return expectedDv.equalsIgnoreCase(dv);
    }
}