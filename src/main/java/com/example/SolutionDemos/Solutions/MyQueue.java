package com.example.SolutionDemos.Solutions;

import java.util.Stack;

class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        if (!stackOut.isEmpty()) {
            return stackOut.pop();
        }
        if (!stackIn.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
            return stackOut.pop();
        }
        return -1;
    }

    public int peek() {
        if (!stackOut.isEmpty()) {
            return stackOut.peek();
        }
        if (!stackIn.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
            return stackOut.peek();
        }
        return -1;
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such: MyQueue obj = new MyQueue(); obj.push(x); int param_2 =
 * obj.pop(); int param_3 = obj.peek(); boolean param_4 = obj.empty();
 */