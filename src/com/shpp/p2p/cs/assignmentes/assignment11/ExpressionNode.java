package com.shpp.p2p.cs.bvorobev.assignment11;

import java.util.ArrayList;

/**
 * The method describe nodes:
 * <p>
 * 1. The value that is final and input in the node.
 * <p>
 * 2. ExpressionNode left and right — it's a left and right child of node.
 * <p>
 * 3. Nesting lvl it is a nesting value in brackets.
 * <p>
 * 4. Priority — priority of operators and operands. Operands always has 0 priority.*/
public class ExpressionNode {
    private String token;
    private int nestingLevel;
    private int priority;
    private ExpressionNode parent;
    private ExpressionNode left;
    private ExpressionNode right;

    public ExpressionNode() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ExpressionNode getParent() {
        return parent;
    }

    public void setParent(ExpressionNode parent) {
        this.parent = parent;
    }

    public ExpressionNode getLeft(){
        return this.left;
    }

    public void setLeft(final ExpressionNode left){
        this.left = left;
    }

    public ExpressionNode getRight() {
        return this.right;
    }

    public void setRight(final ExpressionNode right){
        this.right = right;
    }
}
