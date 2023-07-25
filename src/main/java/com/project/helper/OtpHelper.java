package com.project.helper;

import java.util.Random;

public class OtpHelper {

    public static String createRandomOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            otp.append(randomNumber);
        }
        return otp.toString();
    }

    public static String createRefcode(String chars) {
        Random random = new Random();
        StringBuilder ref = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            ref.append(chars.charAt(random.nextInt(chars.length())));
        }

        return ref.toString();
    }
}
