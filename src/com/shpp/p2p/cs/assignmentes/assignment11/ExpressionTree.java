package com.shpp.p2p.cs.stud.assignment11;

/**
 * The class describes how exactly the tree will be built.*/
public class ExpressionTree {
    private ExpressionNode root;

    public ExpressionTree() {
        root = null;
    }

    /**
     * Add new node, and set it to root, or if root != null, find place as left child or right child.
     * @param token it is always operator or operand.
     * @param priority it is operator priority. The operand always hase 0 priority.
     * @param nestingLevel it is a nesting in the brackets.*/
    public void add(String token, int priority, int nestingLevel) {
        ExpressionNode inputNode = new ExpressionNode();
        inputNode.setToken(token);
        inputNode.setPriority(priority);
        inputNode.setNestingLevel(nestingLevel);
        if (root == null) {
            root = inputNode;
        } else if (isOperand(root)) { // It should be only once, because first input symbol it is operand.
            ExpressionNode prev = root;
            root = inputNode;
            inputNode.setLeft(prev);
            prev.setParent(inputNode);
        } else if (priority != 0 && root.getNestingLevel() >= nestingLevel &&
                (isCurrentPriorityEqual(root, priority) || isCurrentPriorityHigher(root, priority))) {
            // It needed for set new root, if root is has higher or equal nesting lvl and if root has higher priority
            // Than input node.
            ExpressionNode prev = root;
            root = inputNode;
            inputNode.setLeft(prev);
            prev.setParent(inputNode);
        } else { // If root isn't empty
            ExpressionNode currNode = root; // Always start from root.
            while (true) {
                if (currNode.getRight() == null) { // If right child is empty, set up new right child.
                    currNode.setRight(inputNode);
                    inputNode.setParent(currNode);
                    return;
                } else { // If right child is node.
                    if (isNestingLevelEqual(currNode.getRight(), nestingLevel)) { // If nesting lvl is equal right child
                        if (isOperand(currNode.getRight())) {
                            // If new node is operand, just set it as right child
                            ExpressionNode prev = currNode.getRight();
                            currNode.setRight(inputNode);
                            inputNode.setParent(currNode);
                            inputNode.setLeft(prev);
                            prev.setParent(inputNode);
                            return;
                        } else {
                            // If new node is operator
                            if (priority != 0 && (isCurrentPriorityEqual(currNode.getRight(), priority)
                                    || isCurrentPriorityHigher(currNode.getRight(), priority))) {
                                // Check priority and set up new node.
                                ExpressionNode prev = currNode.getRight();
                                currNode.setRight(inputNode);
                                inputNode.setParent(currNode);
                                inputNode.setLeft(prev);
                                prev.setParent(inputNode);
                                return;
                            } else { // Else go to the next right node.
                                currNode = currNode.getRight();
                            }
                        }
                    } else if (currNode.getRight().getNestingLevel() > nestingLevel) {
                        // If right child nesting lvl is higher, just set up new node on the right child place.
                        // And right child set up as left child.
                        ExpressionNode prev = currNode.getRight();
                        currNode.setRight(inputNode);
                        inputNode.setParent(currNode);
                        inputNode.setLeft(prev);
                        prev.setParent(inputNode);
                        return;
                    } else {
                        currNode = currNode.getRight();
                    }
                }
            }
        }
    }

    /**
     * Compare priority of current node and input node.
     * <p>
     * It needed for check if input node may be new root.
     * <p>
     * E.g.: if current node "+" and has priority 1, and new node "*" and has priority 2, the input node should be added
     * to the right child of current node.*/
    private boolean isCurrentPriorityHigher(ExpressionNode currentNode, int priority) {
        return currentNode.getPriority() > priority;
    }

    private boolean isCurrentPriorityEqual(ExpressionNode root, int priority) {
        return root.getPriority() == priority;
    }

    /**
     * Check nesting lvl of current node and input node.
     * <p>
     * Nesting lvl it is a value lvl in brackets.
     * <p>
     * E.g.: 2+(3-4) >> 2 and "+" has 0 nesting lvl, and 3 "-" and 4 have 1 nesting lvl.*/
    private boolean isNestingLevelEqual(ExpressionNode root, int nestingLevel) {
        return root.getNestingLevel() == nestingLevel;
    }

    /**
     * Because the operand has always priority 0, need to check if input value is operand or not.*/
    private boolean isOperand(ExpressionNode node) {
        return node.getPriority() == 0;
    }

    public ExpressionNode getRoot() {
        return root;
    }
}
