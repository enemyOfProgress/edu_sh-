package com.shpp.p2p.cs.bvorobev.assignment11;

import java.util.HashMap;

/**
 * The class get is calculated expression.
 * Get the expression as a tree.*/
public class Calculator {

    HashMap<String, String> var;

    public Calculator(HashMap<String, String> variables) {
        this.var = variables;
    }

    /**
     * Check the node, if it is operator - just calculate the left and the right child.
     * Then result of calculation set on the node-operator place. And call method with parent node.
     * @param node in first case (out call) it only root.
     * Then method working with recursion call.*/
    public void calculate(ExpressionNode node) {
        String result;
        if (node == null) {
            return;
        }
        if (isFunction(node.getToken())) {
            if (node.getRight() != null && node.getRight().getPriority() != 0) { // If function in function.
                calculate(node.getRight());
            }
            if (node.getRight() == null) { // It checks needed for some value, when calculate been in brackets.
                return;
            }
            result = calculateValues(node.getToken(), "0", node.getRight().getToken());
            node.setToken(result);
            node.getRight().setParent(null);
            node.setRight(null);
            node.setPriority(0);
            calculate(node.getParent());
        } else if (node.getLeft().getPriority() == 0 && node.getRight().getPriority() == 0) {
            // If the child is numbers - just calculate it according to operator.
            // And set up new node, old node will be deleted.
            result = calculateValues(node.getToken(), node.getLeft().getToken(), node.getRight().getToken());
            node.setToken(result);
            node.getLeft().setParent(null);
            node.setLeft(null);
            node.getRight().setParent(null);
            node.setRight(null);
            node.setPriority(0);
            calculate(node.getParent());
        } else if (node.getLeft() != null) {
            if (node.getLeft().getPriority() > 0) {
                calculate(node.getLeft());
            } else if (node.getRight().getPriority() > 0) {
                calculate(node.getRight());
            }
        }
    }

    /**
     * @param token it can be operator, operand or function. If it is a function - return true.*/
    private boolean isFunction(String token) {
        return token.equals("sin") || token.equals("cos") || token.equals("tan") ||
                token.equals("atan") || token.equals("log10") || token.equals("log2") ||
                token.equals("sqrt");
    }

    /**
     * Just do the operation according to operator between two values.*/
    private String calculateValues(String operator, String operandLeft, String operandRight) {
        String result = "";
        try{
            double left;
            double right;
            if (var.get(operandLeft) == null){
                left = Double.parseDouble(operandLeft);
            } else {
                left = Double.parseDouble(var.get(operandLeft));
            }

            if (var.get(operandRight) == null){
                right = Double.parseDouble(operandRight);
            } else {
                right = Double.parseDouble(var.get(operandRight));
            }
            switch (operator) {
                case "+" -> result = String.valueOf(left + right);
                case "-" -> result = String.valueOf(left - right);
                case "*" -> result = String.valueOf(left * right);
                case "/" -> result = String.valueOf(left / right);
                case "^" -> result = String.valueOf(Math.pow(left, right));
                case "sin" -> result = String.valueOf(Math.sin(right));
                case "cos" -> result = String.valueOf(Math.cos(right));
                case "tan" -> result = String.valueOf(Math.tan(right));
                case "atan" -> result = String.valueOf(Math.atan(right));
                case "log10" -> result = String.valueOf(Math.log10(right));
                case "log2" -> result = String.valueOf(Math.log(right) / Math.log(2));
                case "sqrt" -> result = String.valueOf(Math.sqrt(right));
            }
            return result;
        } catch (Exception e){
            System.out.println("There is difference between variables in expression and variables array" +
                    "There aren't any values " + e.getMessage());
        }
        return result;
    }
}
