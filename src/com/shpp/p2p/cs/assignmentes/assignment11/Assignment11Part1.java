package com.shpp.p2p.cs.stud.assignment11;

import java.util.HashMap;

/**
 * The class get an expression and a variables and calculate the expression.
 * <p>
 * !!!!!!!!!!! WARNING !!!!!!!!!!!
 * <p>
 * The method use simple method to power numbers, as it uses in Excel.
 * <p>
 * Also, the method use standard library for function cos, tan, atan, sin, sqrt.
 * <p>
 * So some results can be different between wolfram and excel of standard calculator.
 * <p>
 * The unary minus isn't processing if the unary minus before brackets,
 * because the processing of unary minus is before calculating. So the expression like -(2)^2 isn't processing.
 */
public class Assignment11Part1 {
    static ExpressionNode root;
    static HashMap<String, String> variables;

    public static void main(String[] args) {
        try {
            System.out.println("The expression is: " + args[0]);
            prepareData(args);
            calculate();
            System.out.println("The result of expression is: " + root.getToken());
        } catch (Exception e){
            System.out.println("Something went wrong, please, recheck the expression. ");
        }
    }

    private static void prepareData(String[] args) {
        Tokenizer data = new Tokenizer(args);
        data.tokenizeExpression();
        data.tokenizeVariables();
        variables = data.getVariables();
        root = data.saveInTree();
    }

    private static void calculate() {
        Calculator calc = new Calculator(variables);
        calc.calculate(root);
    }

}
