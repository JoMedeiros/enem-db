package br.ufrn;

import java.util.ArrayList;

public class Option {
    private int index;
    private ArrayList<String> value;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value.add( value );
    }

    Option(int i, String value) {
        this.index = i;
        this.value = new ArrayList<String>();
        this.value.add(value);
    }
}
