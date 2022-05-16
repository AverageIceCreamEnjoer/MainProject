package com.example.mainproject.db;

public class SubstanceItem {
    public final String title;
    public final String formula;
    public final int unicode;
    public Integer base;
    public SubstanceItem(String title, String formula, int unicode, Integer base){
        this.title = title;
        this.formula = formula;
        this.unicode = unicode;
        this.base = base;
    }
}
