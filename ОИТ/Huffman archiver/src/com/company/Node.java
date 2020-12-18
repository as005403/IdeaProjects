package com.company;

public class Node {

    private char symbol;
    private int frequency;
    private Node leftChild;//левый потомок
    private Node rightChild;//правый потомок
    private String code="";



    public void setSymbol(char symbol) {
        this.symbol=symbol;
    }
    public void setFrequency(int frequency) {
        this.frequency=frequency;
    }
    public void addChild(Node newNode) {//добавить потомка
        if (leftChild == null)//если левый пустой=> правый тоже=> добавляем в левый
            leftChild = newNode;
        else {
            if (leftChild.getFrequency() <= newNode.getFrequency()) //в общем, левым потомком
                rightChild = newNode;//станет тот, у кого меньше частота
            else {
                rightChild = leftChild;
                leftChild = newNode;
            }
        }

        frequency += newNode.getFrequency();//итоговая частота
    }
    public void addToCode(String i){
        code += i;
    }

    public String getCode() {
        return code;
    }
    public char getSymbol() {
        return symbol;
    }
    public int getFrequency() {
        return frequency;
    }
    public Node getLeftChild() {
        return leftChild;
    }
    public Node getRightChild() {
        return rightChild;
    }

    public boolean isLeaf() {//проверка на лист
        return leftChild == null && rightChild == null;
    }
}