package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Haffman haffman = new Haffman();
        haffman.generation();
        HashMap<Integer, Character> frequency = haffman.numberOfChars("file.txt");

        for (Integer chars: frequency.keySet()) {

            System.out.print(chars + ": " + frequency.get(chars));
            System.out.println();
        }
    }
}

