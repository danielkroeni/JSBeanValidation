package ch.fhnw.imvs.jsbeanvalidation;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class JSValidationTest {

    @Test
    public void testBeanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        TestBean t = new TestBean();
        
        Set<ConstraintViolation<TestBean>> violations = validator.validate(t);
        assertEquals(2, violations.size());
        
        t.i = 15;
        t.s = "bli0";
        violations = validator.validate(t);
        assertEquals(1, violations.size());
        
        t.s = "bla0";
        violations = validator.validate(t);
        assertEquals(0, violations.size());    
    }
}

class TestBean {   
    @JSConstraint("i > 12")
    public int i;
    
    @JSConstraint(value = "s != null && s.length > 3 && s.indexOf('bla') == 0", var = "s")
    public String s;  
}
