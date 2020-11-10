package com.company;
import java.io.*;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.*;


public class Haffman {

    private char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public void generation(){
        try(FileWriter file = new FileWriter("file.txt"))
        {
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

    public HashMap<Integer, Character> numberOfChars(String fileName) {

        HashMap<Integer, Character> frequency = new HashMap<>();
        int numberOfRepeats = 0;

        for (int i = 0; i < chars.length; i++) {
            try (FileReader file = new FileReader(fileName)) {
                int c;
                while ((c = file.read()) != -1) {
                    if (chars[i] == (char) c) numberOfRepeats++;
                }
                
                
                
                frequency.put(numberOfRepeats, chars[i]);
                numberOfRepeats = 0;
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }

//        HashMap<Character, Integer> frequencySortedByValue = new HashMap<>();





        return frequency;
    }
}