package day08;

public class ArithmaticOperation {

    // using ArithmaticInterface to define three lamda operations (addOps, multiplyOps, subtractOps) that implement the process method for each operation.
    // but nothing is being executed or called in this code yet.
    ArithmaticInterface<Integer> addOps = (a, b) -> {
        return a + b;
    };

    ArithmaticInterface<Integer> multiplyOps = (a, b) -> {
        return a * b;
    };

    ArithmaticInterface<Integer> subtractOps = (a, b) -> {
        return a - b;
    };

    // defining methods that use the lambda expressions above to perform actual arithmetic operations.
    public Integer AddOperation(Integer ta, Integer tb) {
        return this.addOps.process(ta, tb);
    }

    public Integer MultiplyOperation(Integer ta, Integer tb) {
        return this.multiplyOps.process(ta, tb);
    }

    public Integer SubtractOperation(Integer ta, Integer tb) {
        return this.subtractOps.process(ta, tb);
    }

}
