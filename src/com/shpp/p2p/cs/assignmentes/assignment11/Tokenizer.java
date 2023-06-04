package com.shpp.p2p.cs.stud.assignment11;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class can do:
 * <p>
 * 1. Reading the sting-expression and split it by operator and operands in array list as separate token.
 * <p>
 * 2. Reading the variables and split it to HashMap as key and value by "=" sign.
 * <p>
 * 3. Return prepared data for calculation.
 */
public class Tokenizer {

    // All data: expression and variables.
    protected String[] allData;
    private String expression;
    // Keeps each value from expression as separate token in ArrayList.
    private ArrayList<String> tokens = new ArrayList<>();
    // Keeps each variable in HashMap, where key - variable name, and value - variable value.
    private static final HashMap<String, String> variables = new HashMap<>();
    // An object for processing expression.
    TokenizeExpression exp = new TokenizeExpression();
    // An object for processing variables.
    TokenizeVariables var = new TokenizeVariables();

    public Tokenizer(String[] args) {
        this.expression = args[0];
        this.allData = args;
    }

    /**
     * How it works read in TokenizeExpression.*/
    public void tokenizeExpression() {
        replaceIncorrectValue();
        tokens = exp.tokenize(expression);
    }

    /**
     * How it works read in the TokenizeExpression class.*/
    private void replaceIncorrectValue() {
        expression = exp.replace(expression.toLowerCase());
    }

    /**
     * How it works read in the TokenizeVariables class.*/
    public void tokenizeVariables() {
        var.tokenize(allData);
    }

    protected static void updateVariables(HashMap<String, String> var) {
        variables.putAll(var);
    }

    public HashMap<String, String> getVariables() {
        return variables;
    }

    /**
     * Read the arrayList of tokens, and add it to tree.
     * Set priority for each value, and if open bracket increase nesting level, if close bracket decrease nesting lvl.*/
    public ExpressionNode saveInTree() {
        ExpressionTree tree = new ExpressionTree();
        int openBrackets = 0;
        for (String token : tokens) {
            if (token.equals("(")) {
                openBrackets++;
            }
            if (token.equals(")")) {
                openBrackets--;
            }
            switch (token) {
                case "+", "-" -> tree.add(token, 1, openBrackets);
                case "*", "/" -> tree.add(token, 2, openBrackets);
                case "^" -> tree.add(token, 3, openBrackets);
                case "sin", "cos", "tan", "atan", "log10", "log2", "sqrt" -> tree.add(token, 4, openBrackets);
                case "(", ")" -> {
                }
                default -> tree.add(token, 0, openBrackets);
            }
        }
        return tree.getRoot();
    }
}
