package com.util.functional;

import java.util.ArrayList;

public class CallFunctional {

    public void call(ListManipulator<?> list) {

        ArrayList<String> arrayList = new ArrayList<>();

        Object o = list.manList(arrayList);
    }

}
