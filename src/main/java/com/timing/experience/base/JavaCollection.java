package com.timing.experience.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaCollection {

    private void methodA() {
        List a = Arrays.asList(1, 2, 3);
    }

    public static void main(String[] args) {
        List a = Arrays.asList(1, 2, 3);
        System.out.println(a.size());
        System.out.println(a.getClass());
        int[] aa = {1, 2, 3, 4};
        List bb = Arrays.asList(aa);
        System.out.println(bb.size());
//        a.add(2);

//        ArrayList<Integer> b = Arrays.asList(1, 2, 3);
        Integer[] data = {1, 2, 3};
        List<Integer> lista = Arrays.asList(data);
        ArrayList<Integer> listb = new ArrayList<>(lista);
        listb.add(5);


    }
}
