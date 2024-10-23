package day08;

@FunctionalInterface  // an interface that has exactly one abstract method.
public interface ArithmaticInterface<T> {
    
    T process(T a, T b);
}
