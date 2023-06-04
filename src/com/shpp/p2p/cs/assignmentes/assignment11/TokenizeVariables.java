package com.shpp.p2p.cs.bvorobev.assignment11;

import java.util.HashMap;

/**
 * Processing the variables.
 * <p>
 * 1. Replace commas to dots for double values.
 * <p>
 * 2. Replaces all spaces by empty.
 * <p>
 * 3. Add correct values to HashMap as {a=2, b=3} etc.*/
public class TokenizeVariables {
    private final HashMap<String, String> variables = new HashMap<>();

    public TokenizeVariables() {
    }

    /**
     * Replace incorrect symbols in variables and separate it by "=" in hashMap, where kay = variable name,
     * and value = variable value.*/
    public void tokenize(String[] args) {
        for (int i = 1; i < args.length; i++) {
            String[] splittedVariable = args[i].
                    replaceAll(",+", ".").
                    replaceAll(" ", "").
                    split("=");
            String key = "";
            String value = "";
            if (splittedVariable.length > 1) {
                key = splittedVariable[0];
                value = splittedVariable[1].replaceAll(",", ".");
                variables.put(key, value);
            } else {
                System.out.println("The one variable is incorrect.");
            }
            Tokenizer.updateVariables(variables);
        }
    }
}
