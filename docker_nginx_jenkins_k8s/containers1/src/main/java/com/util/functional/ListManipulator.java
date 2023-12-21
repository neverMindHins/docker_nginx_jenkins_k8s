package com.util.functional;

import java.util.ArrayList;

@FunctionalInterface
public interface ListManipulator<T> {

    T manList(ArrayList<String> list);
}