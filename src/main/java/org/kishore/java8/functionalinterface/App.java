package org.kishore.java8.functionalinterface;

public class App {
    public static void main(String[] args) {
        CalculatorFunctionalInterface calc = (a,b) -> a + b;
        // Functional interface's only abstract method
        System.out.println("Functional Interface: " + calc.calculate(2,7));
        // default method
        System.out.println("Functional Interface: Default sum > " + calc.defaultSum(5,9));
    }
}
