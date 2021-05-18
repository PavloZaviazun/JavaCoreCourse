package main.Practice6.part6;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part61 {
    private Part61() {

    }
    public static void frequencyCount(String input) {
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(input);
        Map <String, Integer> map = new LinkedHashMap <>();
        while(matcher.find()) {
            String group = matcher.group();
            int count = 0;
            for(Map.Entry<String, Integer> pair : map.entrySet()) {
                if(pair.getKey().equals(group)) {
                    pair.setValue(pair.getValue() + 1);
                } else {
                    count++;
                }
            }
            if(count == map.size()) map.put(group, 1);
        }
        map.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(3)
                .sorted((a, b) -> b.getKey().compareTo(a.getKey()))
                .forEach(a -> System.out.println(a.getKey() + " ==> " + a.getValue()));
    }
}
