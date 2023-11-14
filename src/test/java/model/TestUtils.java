package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils {

    WebDriver driver;
    private static String generatedString;

    public static String getGeneratedString() {
        return generatedString;
    }


    public static String getRandomString() {

        Random random = new Random();
        StringBuffer str = new StringBuffer();
        int randomStringLenght = 10;
        for (int i = 0; i < randomStringLenght; i++) {

            str = str.append((char) random.nextInt(26) + 'a');
        }
        generatedString = str.toString();

        return generatedString;

    }

    public static String convertRGBToHex(String rgb) {
        String hex = Color.fromString(rgb).asHex();


        return hex;

    }

    public static String findSomeSequenceInString(String totalString, String regex){
        Pattern regexp = Pattern.compile(regex);
        Matcher match = regexp.matcher(totalString);
        if (match.find()) {
            return match.group(1);
        }
        throw new RuntimeException("not found");
    }

}


