package ch.fhnw.imvs.jsbeanvalidation;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JSConstraintValidator implements ConstraintValidator<JSConstraint, Object> {

    private String jsConstraint;
    private String variableName;
    
    @Override
    public void initialize(JSConstraint constraint) {
        jsConstraint = constraint.value();
        variableName = constraint.var();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        ScriptEngineManager mgr = new ScriptEngineManager(null);
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        
        try {
            engine.put(variableName, value);
            Object result = engine.eval(jsConstraint);
            if(result instanceof Boolean) return (Boolean) result;
            else return false;
        } catch (ScriptException ex) {
            ex.printStackTrace();
            return false;
        }    
    }
}