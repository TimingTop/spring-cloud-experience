package com.timing.compiler.day53;

import java.util.ArrayList;

public class Symbols {
    public  int value;
    public ArrayList<int[]> productions;
    public  ArrayList<Integer> firstSet = new ArrayList<Integer>();
    public  ArrayList<Integer> followSet = new ArrayList<Integer>();
    public  ArrayList<ArrayList<Integer>> selectionSet = new ArrayList<ArrayList<Integer>>();

    public  boolean isNullable; // 是否可推出 null

    public Symbols(int symVal, boolean nullable, ArrayList productions) {
        value = symVal;
        this.productions =  productions;
        isNullable = nullable;

        if (symVal < 256) {   // 《 256 就是终结符，方便计算而已
            //terminal's first set is itself
            firstSet.add(symVal);
        }
    }
}
