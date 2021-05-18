package main.Practice6.part6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part6 {

    public static void main(String[] args) {
        String fileName = "";
        String command = "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-i") || args[i].equals("--input")) fileName = args[i + 1];
            if(args[i].equals("-t") || args[i].equals("--task")) command = args[i + 1];
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while(reader.ready()) {
                sb.append(reader.readLine()).append(" ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        switch(command) {
            case "frequency" : Part61.frequencyCount(sb.toString());
            break;
            case "length" : Part62.lengthCount(sb.toString());
            break;
            case "duplicates" : Part63.duplicates(sb.toString());
            break;
            default : System.out.println("no matches");
        }
    }

}

