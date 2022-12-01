package utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static final Pattern digitPattern = Pattern.compile("\\d+");
    public ArrayList<Integer> getDigitsFromString(String s){
        ArrayList<Integer> digits = new ArrayList<>();
        Matcher m = digitPattern.matcher(s);
        while (m.find()){
            digits.add(Integer.parseInt(m.group()));
        }
        return digits;
    }
}
