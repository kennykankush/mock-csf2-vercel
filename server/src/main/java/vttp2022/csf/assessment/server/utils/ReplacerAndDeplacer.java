package vttp2022.csf.assessment.server.utils;

import java.util.ArrayList;
import java.util.List;

public class ReplacerAndDeplacer {

    public static List<String> replace(List<String> strings){
        List<String> newStrings = new ArrayList<>();
        for (String string: strings){
            String newString = string.replace("/", "_");
            newStrings.add(newString);
        }

        return newStrings;
    }

    public static List<String> deplace(List<String> strings){
        List<String> newStrings = new ArrayList<>();
        for (String string: strings){
            String newString = string.replace("_", "/");
            newStrings.add(newString);
        }

        return newStrings;
    }

    public static String deplace(String string){
        String newString = string.replace("_","/");
        return newString;
    }
    
    
}
