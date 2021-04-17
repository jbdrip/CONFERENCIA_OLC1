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
public class Expression implements Instruction {
    public static enum Expression_type {
        SUMA,
        RESTA,
        MULTIPLICACION,
        MAYOR_QUE,
        MENOR_QUE,
        MAYOR_IGUAL,
        MENOR_IGUAL,
        IGUAL_IGUAL,
        AND,
        ENTERO,
        IDENTIFICADOR,
        FUNCION,
        NULO
    }
    
    public LinkedList<Expression> parameters, arraySizes;
    public LinkedList<Sym> paramsResult, symResult;
    public Expression_type type;
    public Expression leftExp;
    public Expression rightExp;
    public Object value;
    public Sym val;
    public int line, column;
    
    public Expression(Expression leftExp, Expression rightExp, Expression_type tipo, int line, int column) {
        this.type = tipo;
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        val = new Sym();
        this.line = line;
        this.column = column;
    }
    
    public Expression(long entero, int line, int column) {
        this.value = entero;
        this.type = Expression_type.ENTERO;
        val = new Sym(EnumType.entero, entero);
        this.line = line;
        this.column = column;
    }
    
    public Expression(String id, Expression_type type, int line, int column) {
        this.value = id;
        this.type = type;
        val = new Sym();
        this.line = line;
        this.column = column;
    }
    
    public Expression(String id, LinkedList<Expression> parameters, Expression_type type, int line, int column) {
        this.value = id;
        this.type = type;
        val = new Sym();
        this.line = line;
        this.column = column;
        this.parameters = parameters;
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
    public Object execute(Enviroment env) {
        Sym leftResult = new Sym(EnumType.nulo, "@null");
        Sym rightResult = new Sym(EnumType.nulo, "@null");
        if(leftExp != null) {
            leftExp.paramsResult = executeParams(leftExp, env);
            leftResult = (Sym)leftExp.execute(env);
        }
        if(rightExp != null) {
            rightExp.paramsResult = executeParams(rightExp, env);
            rightResult = (Sym)rightExp.execute(env);
        }
        if(null == type) return null;
        else switch (type) {
            case MULTIPLICACION: return Multiplicacion(env, leftResult, rightResult);
            case RESTA: return Resta(env, leftResult, rightResult);
            case SUMA: return Suma(env, leftResult, rightResult);
            case MAYOR_QUE: return MayorQue(env, leftResult, rightResult);
            case MENOR_QUE: return MenorQue(env, leftResult, rightResult);
            case MAYOR_IGUAL: return MayorIgual(env, leftResult, rightResult);
            case MENOR_IGUAL: return MenorIgual(env, leftResult, rightResult);
            case IGUAL_IGUAL: return IgualIgual(env, leftResult, rightResult);
            case AND: return And(env, leftResult, rightResult);
            case ENTERO: return val;
            case IDENTIFICADOR: return Identificador(env);
            case FUNCION: return functionVoid_Call(env);
            default: return null;
        }
    }
    
    public Object functionVoid_Call(Enviroment env) {
        FunctionCall call = new FunctionCall((String)value, parameters, line, column);
        call.paramsResult = paramsResult;
        return call.execute(env);
    }
    
    public Object Identificador(Enviroment env) {
        Sym sym = env.search(value.toString(), 0, 0);
        if(sym != null) {
            val = new Sym(sym.type, sym.value);
            return new Sym(sym.type, sym.value);
        }
        return new Sym(EnumType.error, "@error");
    }
    
    public Object Multiplicacion(Enviroment env, Sym leftResult, Sym rightResult) {
        long result = (long)leftResult.value * (long)rightResult.value;
        val = new Sym(EnumType.entero, result);
        return new Sym(EnumType.entero, result);
    }
    
    public Object Resta(Enviroment env, Sym leftResult, Sym rightResult) {
        long result = (long)leftResult.value - (long)rightResult.value;
        val = new Sym(EnumType.entero, result);
        return new Sym(EnumType.entero, result);
    }
    
    public Object Suma(Enviroment env, Sym leftResult, Sym rightResult) {
        long result1 = (long)leftResult.value + (long)rightResult.value;
        val = new Sym(EnumType.entero, result1);
        return new Sym(EnumType.entero, result1);
    }
     
    public Object MayorQue(Enviroment env, Sym leftResult, Sym rightResult) {
        boolean result = (long)leftResult.value > (long)rightResult.value;
        val = new Sym(EnumType.booleano, result);
        return new Sym(EnumType.booleano, result);
    }
    
    public Object MenorQue(Enviroment env, Sym leftResult, Sym rightResult) {
        boolean result = (long)leftResult.value < (long)rightResult.value;
        val = new Sym(EnumType.booleano, result);
        return new Sym(EnumType.booleano, result);
    }
    
    public Object MayorIgual(Enviroment env, Sym leftResult, Sym rightResult) {
        boolean result = (long)leftResult.value >= (long)rightResult.value;
        val = new Sym(EnumType.booleano, result);
        return new Sym(EnumType.booleano, result);
    }
    
    public Object MenorIgual(Enviroment env, Sym leftResult, Sym rightResult) {
        boolean result = (long)leftResult.value <= (long)rightResult.value;
        val = new Sym(EnumType.booleano, result);
        return new Sym(EnumType.booleano, result);
    }
    
    public Object IgualIgual(Enviroment env, Sym leftResult, Sym rightResult) {
        boolean result = (long)leftResult.value == (long)rightResult.value;
        val = new Sym(EnumType.booleano, result);
        return new Sym(EnumType.booleano, result);
    }
      
    public Object And(Enviroment env, Sym leftResult, Sym rightResult) {
        boolean result;
        if(leftResult.type == EnumType.booleano && leftResult.type == EnumType.booleano) {
            result = (boolean)leftResult.value && (boolean)rightResult.value;
            val = new Sym(EnumType.booleano, result);
            return new Sym(EnumType.booleano, result);
        }
        return new Sym(EnumType.error, "@error");
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
