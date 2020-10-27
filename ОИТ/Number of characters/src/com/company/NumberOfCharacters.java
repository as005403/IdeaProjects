package com.company;
import java.io.*;
import java.security.SecureRandom;


public class NumberOfCharacters {

    public static void generation(){

        try(FileWriter file = new FileWriter("D://My projects//IdeaProjects//ОИТ//Number of characters//file.txt"))
        {
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            SecureRandom rd = new SecureRandom();

            for (int i = 0; i < 50; i++) {
                file.append(chars[rd.nextInt(chars.length)]);
            }
            file.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


    }

    public static int number(char character) {

        int number = 0;

        try (FileReader file = new FileReader("D://My projects//IdeaProjects//ОИТ//Number of characters//file.txt")) {
            int c;
            while ((c = file.read()) != -1) {
                if (character == (char)c) number++;
                System.out.print((char) c);
            }
            System.out.println();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        return number;
    }
}