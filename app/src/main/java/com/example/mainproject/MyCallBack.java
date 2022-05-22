package com.example.mainproject;

import java.util.ArrayList;

public interface MyCallBack {
    void print_elem(boolean x);
    void leave();
    void clear();
    void theme();
    void add_create_fragment();
    void create_substance(String name, String formula);
    void leave_create_fragment();
    void add_reaction(ArrayList<String> arrayList, String name);
    void leave_settings_fragment();
}
