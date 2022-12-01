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
        model.setStoredVal(9.5);
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
    @Test
    public void squareTest(){
        model.setValue(5);
        model.square();
        assertEquals(25,model.getValue(),.01);
    }

    @Test
    public void rootTest(){
        model.setValue(25);
        model.root();
        assertEquals(5,model.getValue(),.01);
    }
    @Test
    public void memRecallTest(){
        model.memoryRecall();
        assertEquals(74.3,model.getValue(),.01);
    }

    @Test
    public void memSubtractTest(){
        model.memorySub();
        assertEquals(74.3-5.5,model.getMemory(),.01);
    }

    @Test
    public void memAddTest(){
        model.memoryAdd();
        assertEquals(74.3+5.5,model.getMemory(),.01);
    }
    @Test
    public void memClearTest(){
        model.memoryClear();
        assertEquals(0,model.getMemory(),.01);
    }
}