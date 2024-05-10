package org.kishore.java8.functionalinterface;

@FunctionalInterface
public interface CalculatorFunctionalInterface {
    int calculate(int a, int b);

    default int defaultSum(int a, int b){
        return a + b;
    }
}
