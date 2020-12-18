package com.company;
import java.io.*;
import java.util.*;


public class Haffman {

    private ArrayList<Character> symbols = new ArrayList<Character>();
    private ArrayList<Node> NodeList = new ArrayList<Node>();
    private Queue<Node> NodeQueue = new PriorityQueue<Node>(frequencyComparator);
    private Node haffmanRoot;

    public Haffman(String fileName){
        readFile(fileName);
        findFrequency();
        addNodesToQueue();
        haffmanTree();
        symbolCodes();
    }


    private  void readFile(String fileName){
        try (FileReader file = new FileReader(fileName)) {
            int c;
            while ((c = file.read()) != -1) {
                Character temp;
                temp = (char) c;
                symbols.add(temp);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void findFrequency() {
        int frequency = 0;

        Collections.sort(symbols,characterComparator);
        int listSize = symbols.size();

        for (int i = 0; i < listSize; i++) {
            frequency++;
            Node temp = new Node();

            if (i==listSize-1){

                temp.setSymbol(symbols.get(i));
                temp.setFrequency(frequency);
                NodeList.add(temp);
                break;
            }

            if (symbols.get(i) != symbols.get(i+1)){

                temp.setSymbol(symbols.get(i));
                temp.setFrequency(frequency);
                NodeList.add(temp);
                frequency = 0;
            }
        }
    }
    private void addNodesToQueue(){
        NodeQueue.addAll(NodeList);
    }
    private void haffmanTree(){
        Node root = new Node();

        root.addChild(NodeQueue.poll());
        root.addChild(NodeQueue.poll());
        NodeQueue.add(root);

        if(NodeQueue.size() > 1)
            haffmanTree();
        else {
            haffmanRoot = NodeQueue.poll();
            addNodesToQueue();
        }
    }
    private void symbolCodes(){
        NodeList.clear();
        Queue<Node> notSymbols = new PriorityQueue<>(frequencyComparator);
        notSymbols.add(haffmanRoot);
        Node temp;

        while (!notSymbols.isEmpty()) {

            temp = notSymbols.poll();
            temp.getLeftChild().addToCode(temp.getCode() + "0");
            temp.getRightChild().addToCode(temp.getCode() + "1");

            if (!temp.getLeftChild().isLeaf()){
                notSymbols.add(temp.getLeftChild());
            }
            else{
                NodeList.add(temp.getLeftChild());
            }

            if (!temp.getRightChild().isLeaf()){
                notSymbols.add(temp.getRightChild());
            }
            else{
                NodeList.add(temp.getRightChild());
            }
        }
    }


    //Анонимный класс компаратора
    private static Comparator<Node> frequencyComparator = new Comparator<Node>(){

            @Override
            public int compare(Node c1, Node c2) {
                return (int) (c1.getFrequency() - c2.getFrequency());
            }
        };
    private static Comparator<Character> characterComparator = new Comparator<Character>(){

        @Override
        public int compare(Character c1, Character c2) {
            return (int) (c1 - c2);
        }
    };


    public void getDataFromList() {
        for (int i = 0; i < NodeList.size(); i++) {
            Node symbol = NodeList.get(i);
            System.out.println(symbol.getSymbol() + ": " + symbol.getFrequency());
        }
    }
    public void getDataFromQueue() {
        while (true) {
            Node symbol = NodeQueue.poll();
            if (symbol == null) break;
            System.out.println(symbol.getSymbol() + ": " + symbol.getFrequency());
        }
        addNodesToQueue();
    }


}
