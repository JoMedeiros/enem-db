package br.ufrn;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private List<Option> options = new ArrayList<Option>();
    public void setOptions(String opts){
        String[] args = opts.split(" ");
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-'){
                Option op = new Option(0,args[i]);//TODO change params to something that makes sense
                String temp = args[i];
                while (temp.charAt(0) != '-'){

                }
            }
        }
    }
}
