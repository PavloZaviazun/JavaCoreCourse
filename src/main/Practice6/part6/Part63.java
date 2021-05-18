package main.Practice6.part6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part63 {
    private Part63() {

    }
    public static void duplicates(String input) {
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(input);
        List <String> list = new ArrayList <>();
        while(matcher.find()) {
            String group = matcher.group();
            Pattern pattern1 = Pattern.compile("\\b" + group + "\\b");
            Matcher matcher1 = pattern1.matcher(input);
            int count = 0;
            while(matcher1.find()) {
                count++;
            }
            if(count > 1) list.add(group);
        }
        list.stream().limit(3)
                .forEach(a -> System.out.println(new StringBuilder(a.toUpperCase()).reverse()));
    }
}
