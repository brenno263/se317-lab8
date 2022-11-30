package se317.lab8.model;

import org.junit.Before;
import org.junit.Test;
import se317.lab8.Operation;

import static org.junit.Assert.*;

public class ModelTest {
    Model model = new Model();
    @Before
    public void before(){
        model.setValue(5.5);
        model.setPrevVal(9.5);
        model.setMemory(74.3);
    }

    @Test
    public void addTest(){
        model.setOperation(Operation.add);
        model.completeOp();
        assertEquals(15,model.getValue(), .01);
    }

    @Test
    public void subtractTest(){
        model.setOperation(Operation.subtract);
        model.completeOp();
        assertEquals(4,model.getValue(), .01);
    }

    @Test
    public void multiplyTest(){
        model.setOperation(Operation.mutiply);
        model.completeOp();
        assertEquals(52.25,model.getValue(), .01);
    }

    @Test
    public void divideTest(){
        model.setOperation(Operation.divide);
        model.completeOp();
        assertEquals(1.72,model.getValue(), .01);
    }
}