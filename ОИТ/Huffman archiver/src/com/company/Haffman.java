package com.company;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;


public class Haffman {

    private char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private ArrayList<Priority> symbolPriorityList = new ArrayList<Priority>();
    private Queue<Priority> symbolPriorityQueue = new PriorityQueue<>(26, frequencyComparator);

    public Haffman(String fileName){
        CalculationSymbolsList(fileName);
        AddSymbolsToQueue();
    }

    private void CalculationSymbolsList(String fileName) {

        int frequency = 0;

        for (int i = 0; i < chars.length; i++) {
            Priority temp = new Priority();

            try (FileReader file = new FileReader(fileName)) {
                int c;
                while ((c = file.read()) != -1) {
                    if (chars[i] == (char) c) frequency++;
                }

                temp.setFrequency(frequency);
                temp.setSymbol(chars[i]);
                frequency = 0;

                symbolPriorityList.add(temp);
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }
    private void AddSymbolsToQueue(){

        //add data to Queue
        for(int i=0; i<symbolPriorityList.size(); i++){
            symbolPriorityQueue.add(symbolPriorityList.get(i));
        }
    }

    //Анонимный класс компаратора
    private static Comparator<Priority> frequencyComparator = new Comparator<Priority>(){

            @Override
            public int compare(Priority c1, Priority c2) {
                return (int) (c1.getFrequency() - c2.getFrequency());
            }
        };

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

    public void getDataFromList() {
        for (int i = 0; i < symbolPriorityList.size(); i++) {
            Priority symbol = symbolPriorityList.get(i);
            System.out.println(symbol.getSymbol() + ": " + symbol.getFrequency());
        }
    }

    public void getDataFromQueue() {
        while (true) {
            Priority symbol = symbolPriorityQueue.poll();
            if (symbol == null) break;
            System.out.println(symbol.getSymbol() + ": " + symbol.getFrequency());
        }
    }
}
