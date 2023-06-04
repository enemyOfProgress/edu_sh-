package com.shpp.p2p.cs.stud.assignment11;

import java.util.ArrayList;

/**
 * The class is tokenize expression to arrayList.
 * So, for example, 2+2*(5-3) >> [2, +, 2, *, (, 5, -, 3, )] where each value in arrayList as separate token.
 */
public class TokenizeExpression {
    public TokenizeExpression() {
    }

    /**
     * Replace all incorrect value, e.g. ++++++ >> + or replace all spaces by nothing.
     */
    protected String replace(String exp) {
        exp = exp.replaceAll(",+", ".").
                replaceAll("\\.+", ".").
                replaceAll("\\++", "+").
                replaceAll("\\*+", "*").
                replaceAll("/+", "/").
                replaceAll("\\^+", "^").
                replaceAll(" ", "");
        return exp;
    }

    /**
     * The method read the string and add each value to array list, as tokens: operator or operands.
     * <p>
     * If it is operator just add it to arrayList.
     * <p>
     * If it isn't operator, collect chars to string and add string to arrayList.
     * <p>
     * E.g.: 2+2,34342 >> [2, +, 2.34342]
     */
    protected ArrayList<String> tokenize(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        String buffer = "";
        for (int i = 0; i < expression.length(); i++) {
            String currentValue = String.valueOf(expression.charAt(i));
            if (!isOperator(currentValue)) {
                buffer += currentValue;
            } else {
                if (i == 0 && expression.charAt(i) == '-') {
                    buffer += currentValue;
                } else if (isUnaryMinus(expression, i)) {
                    buffer += currentValue;
                } else {
                    if (!buffer.isEmpty()) {
                        buffer = removeExtraMinuses(buffer);
                        tokens.add(buffer);
                        buffer = "";
                    }
                    tokens.add(currentValue);
                }
            }
        }
        if (!buffer.isEmpty()) {
            buffer = removeExtraMinuses(buffer);
            tokens.add(buffer);
        }
        return tokens;
    }

    /**
     * The method calculate extra minuses and remove it.
     * <p>
     * E.g. if --5 >> 5
     * <p>
     * If ---5 >> -5 etc.
     */
    private String removeExtraMinuses(String value) {
        int i = 0;
        int counter = 0;
        while (value.charAt(i) == '-') {
            counter++;
            i++;
        }
        if (counter % 2 == 0) {
            value = value.replaceAll("-+", "");
        } else {
            value = value.replaceAll("-+", "-");
        }
        return value;
    }

    /**
     * Check if the unary minus before value.
     */
    private boolean isUnaryMinus(String expression, int i) {
        String operators = "+-*/^(";
        return expression.charAt(i) == '-' && operators.contains(String.valueOf(expression.charAt(i - 1)));
    }

    private boolean isOperator(String currentValue) {
        String operators = "+-*/^()";
        return operators.contains(currentValue);
    }
}
