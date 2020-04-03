package dev.jamesPratt.tankGame.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utilities {

    // This will read our .txt files, parse it and create a world from it.
    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            // Read through each line of the .txt file and add to StringBuilder.
            while((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }
            br.close();

        }
        catch(IOException e) {
            e.printStackTrace();
        }

        // Return the parsed string.
        return builder.toString();
    }

    // Parses each int. If not an integer, catch NumberFormatException.
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        }
        catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
