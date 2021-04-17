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
public class FunctionCall implements Instruction {
    public LinkedList<Expression> parametersExpression;
    public LinkedList<Sym> paramsResult;
    public int line, column;
    public String id;
    
    public FunctionCall(String id, LinkedList<Expression> parametersExpression, int line, int column) {
        this.id = id;
        this.line = line;
        this.column = column;
        this.parametersExpression = parametersExpression;
    }
    
    @Override
    public Object execute(Enviroment env) {
        Enviroment local = new Enviroment(env.getGlobal());
        Sym function = local.search(id + "%%", line, column);
        
        if(function != null) { 
            ParametersIns parametersIns = (ParametersIns) function.value;
            LinkedList<Declaration> parameters = parametersIns.parameters;
            LinkedList<Instruction> instructions = parametersIns.instructions;
            
            if(parameters != null) {
                int index = 0;
                for(Declaration declaration : parameters) {
                    Sym sym = (Sym) paramsResult.get(index++);
                    declaration.execute(local);
                    local.updateValue(declaration.id, sym, line, column);
                }
            }
            
            if(instructions != null) {
                for(Instruction ins : instructions) {
                    if(ins instanceof FunctionCall) {
                        FunctionCall call = (FunctionCall) ins;
                        call.paramsResult = executeParams(call.parametersExpression, local);
                        ins = call;
                    } else if(ins instanceof Expression) {
                        Expression expression = (Expression) ins;
                        expression.paramsResult = executeParams(expression, local);
                        ins = expression;
                    }
                    Object result = ins.execute(local);
                    if(result != null) {
                        if((result instanceof Sym) && !(ins instanceof Expression)){
                            Sym sym = (Sym) result;
                            if(sym.breturn) return sym;
                        }
                    }
                }
                return new Sym(Sym.EnumType.error, "@error");
            }
        }
        return null;
    }
    
    public LinkedList<Sym> executeParams(LinkedList<Expression> parameters, Enviroment env) {
        LinkedList<Sym> params = new LinkedList<>();
        if(parameters != null) {
            for(int i = 0; i < parameters.size(); i++) {
                Expression exp = parameters.get(i);
                exp.paramsResult = executeParams(exp, env);
                Object r = exp.execute(env);
                if(r instanceof Sym) {
                    Sym s = (Sym) r;
                    if(params.size() < parameters.size()) params.add(i, s);
                    else params.set(i, s);
                }
            }
        }
        else return null;
        return params;
    }
    
    public LinkedList<Sym> executeParams(Expression e, Enviroment env) {
        LinkedList<Sym> paramsResultt = null;
        if(e != null && e.parameters != null) {
            paramsResultt = new LinkedList<>();
            for(int i = 0; i < e.parameters.size(); i++) {
                Expression exp = e.parameters.get(i);
                exp.paramsResult = executeParams(exp, env);
                Object r = exp.execute(env);
                if(r instanceof Sym) {
                    final Sym s = (Sym) r;
                    if(paramsResultt.size() < e.parameters.size()) paramsResultt.add(i, s);
                    else paramsResultt.set(i, s);
                }
            }
        }
        return paramsResultt;
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
