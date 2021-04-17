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
public class If implements Instruction
{
    private final Expression condition;
    private final LinkedList<Instruction> instructionsList;
    public int line, column;

    public If(Expression condition, LinkedList<Instruction> instructionsList, int line, int column) {
        this.condition = condition;
        this.instructionsList = instructionsList;
        this.line = line;
        this.column = column;
    }

    
    @Override
    public Object execute(Enviroment env) {
        condition.paramsResult = executeParams(condition, env);
        Sym conditionValue = (Sym) condition.execute(env);
        if (conditionValue.type == EnumType.booleano && (boolean) conditionValue.value) {
            Enviroment localEnv = new Enviroment(env);
            if(instructionsList != null) {
                for(Instruction ins : instructionsList) {
                    if(ins instanceof FunctionCall) {
                        FunctionCall call = (FunctionCall) ins;
                        call.paramsResult = executeParams(call.parametersExpression, localEnv);
                        ins = call;
                    } else if(ins instanceof Expression) {
                        Expression expression = (Expression) ins;
                        expression.paramsResult = executeParams(expression, localEnv);
                        ins = expression;
                    }
                    Object instruction = ins.execute(localEnv);
                    if(instruction != null && (instruction instanceof Sym)) {
                        Sym sym = (Sym) instruction;
                        if(sym.breturn) return instruction;
                    }   
                }
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
        return params;
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