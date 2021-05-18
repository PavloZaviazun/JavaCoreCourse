package main.Practice4;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Part5 {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                String line = reader.readLine();
                if(line.equals("stop")) break;
                String[] array = line.split(" ");
                String word = array[0];
                String language = array[1];
                Locale locale = new Locale(language);
                ResourceBundle rb = ResourceBundle.getBundle("resources" + language, locale);
                String tr = rb.getString(word);
                System.out.println(tr);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

