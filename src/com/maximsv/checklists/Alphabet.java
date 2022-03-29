package com.maximsv.checklists;

import java.util.ArrayList;

public class Alphabet {
    private final ArrayList<Character> symbol = new ArrayList<>();

    public Alphabet(){
        for (char c = 'а'; c <= 'я'; c++) symbol.add(c);
        for (char c = 'А'; c <= 'Я'; c++) symbol.add(c);
        symbol.add('.');
        symbol.add(',');
        symbol.add('”');
        symbol.add(':');
        symbol.add('-');
        symbol.add('!');
        symbol.add('?');
        symbol.add(' ');
        symbol.add('"');
    }



    public ArrayList<Character> getSymbol() {
        return symbol;
    }

    public int getSize() {
        return symbol.size();
    }


}
