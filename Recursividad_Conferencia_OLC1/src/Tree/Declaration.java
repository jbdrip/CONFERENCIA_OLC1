
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Enviroment.Enviroment;
import Enviroment.Sym;
import Enviroment.Sym.EnumType;
import java.util.LinkedList;

/**
 *
 * @author Javier Bran
 */
public class Declaration implements Instruction {
    public String id;
    private Expression value;
    LinkedList<Sym> symResult;
    EnumType type;
    public int line, column;
    
    public Declaration(String id, EnumType type, Expression value, int line, int column) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
    }

    @Override
    public Object execute(Enviroment env) {
        try {
            if(value != null) {
                value.paramsResult = executeParams(value, env);
                Sym valueResult = (Sym)value.execute(env);
                if(valueResult.type == type) {
                    Sym sym = new Sym(valueResult.type, valueResult.value);
                    boolean insert = env.insert(id, sym, line, column);
                }
            } else {
                Sym sym;
                switch(type) {
                    case entero:
                        long l = 0;
                        sym = new Sym(type, l);
                        break;
                    default:
                        sym = new Sym(type, "@null");
                        break;
                }
                boolean insert = env.insert(id, sym, line, column);
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
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
    
    public void setExpression(Expression e) {
        this.value = e;
    }
}
