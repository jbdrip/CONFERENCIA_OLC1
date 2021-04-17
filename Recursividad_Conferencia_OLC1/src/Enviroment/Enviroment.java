/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enviroment;

import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Javier Bran
 */
public class Enviroment {
    public HashMap<String, Sym> table;
    public ArrayList<String> printList;
    private Enviroment previous;
    
    public Enviroment(Enviroment previous) {
        this.previous = previous;
        this.table = new HashMap<>();
        this.printList = new ArrayList<>();
    }
    
    public Enviroment getGlobal() {
        for(Enviroment env = this; env != null; env = env.previous) {
            if (env.previous == null) return env;
        }
        return null;
    }
    
    public boolean insert(String name, Sym sym, int line, int column) {
        name = name.toLowerCase();
        if(table.containsKey(name)) return false;
        table.put(name, sym);
        return true;
    }
    
    public Sym search(String name, int line, int column) {
        name = name.toLowerCase();
        for(Enviroment e = this; e != null; e = e.previous) {
            if(e.table.containsKey(name)) {
                Sym sym = e.table.get(name);
                return sym;
            }
        }
        return null;
    }
    
    public void updateValue(String name, Sym sym, int line , int column) {
        name = name.toLowerCase();
        for(Enviroment e = this; e != null; e = e.previous) {
            if(e.table.containsKey(name)) {
                e.table.replace(name, sym);
                return;
            }
        }
    }
    
    public void setPrevious(Enviroment previous) {
        this.previous = previous;
    }
    
    public Enviroment getPrevious() {
        return this.previous;
    }
}
