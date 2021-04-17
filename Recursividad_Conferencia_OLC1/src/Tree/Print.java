/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Enviroment.Enviroment;
import Enviroment.Sym;
import java.util.LinkedList;

/**
 *
 * @author Javier Bran
 */
public class Print implements Instruction {
    private final Expression content;
    public int line, column;
    
    public Print(Expression content, int line, int column) {
        this.content = content;
        this.line = line;
        this.column = column; 
    }
    
    @Override
    public Object execute(Enviroment env) {
        if(content != null) {
            content.paramsResult = executeParams(content, env);
            String print = content.execute(env).toString();
            env.getGlobal().printList.add(print + "\n");
        }
        return null;
    }
    
    public LinkedList<Sym> executeParams(Expression e, Enviroment env) {
        LinkedList<Sym> paramsResult = null;
        if(e != null && e.parameters != null) {
            paramsResult = new LinkedList<>();
            for(int i = 0; i < e.parameters.size(); i++) {
                Expression exp = e.parameters.get(i);
                exp.paramsResult = executeParams(exp, env);
                Object r = exp.execute(env);
                if(r instanceof Sym) {
                    final Sym s = (Sym) r;
                    if(paramsResult.size() < e.parameters.size()) paramsResult.add(i, s);
                    else paramsResult.set(i, s);
                }
            }
        }
        return paramsResult;
    }
    
    @Override
    public int getLine() {
        return line;
    }
    
    @Override
    public int getColumn() {
        return column;
    }
}
