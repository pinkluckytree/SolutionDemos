package com.example.SolutionDemos.Solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    /**
     * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     *
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     *
     * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     * 
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] leftGas = new int[n * 2];
        for (int i = 0; i < n; i++) {
            leftGas[i] = gas[i] - cost[i];
            leftGas[i + n] = leftGas[i];
        }
        int j = 0;
        int currentGas = 0;
        for (int i = 0; i < n * 2; i++) {
            currentGas += leftGas[i];
            if (currentGas < 0 || i-j>= n) {
                // 无法继续行驶
                if (i-j>= n) {
                    return j;
                }
                j = i+1;
                currentGas = 0;
            }
        }
        return -1;
    }

    // public int canCompleteCircuit2(int[] gas, int[] cost) {
    // int n = gas.length;
    // int[] leftGas = new int[n * 2];
    // for (int i = 0; i < n; i++) {
    // leftGas[i] = gas[i] - cost[i];
    // leftGas[i + n] = leftGas[i];
    // }
    // for (int i = 0; i < n; i++) {
    // int currentGas = leftGas[i];
    // int j = i;
    // while (currentGas >= 0 && j - i < n) {
    // j++;
    // currentGas = currentGas + leftGas[j];
    // }
    // if(j-i == n){
    // return i;
    // }
    // }
    // return -1;
    // }

    /*-----------------------------------------------------------------------------------*/

    /**
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            Queue<TreeNode> queue2 = new LinkedList<>();
            List<Integer> list = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    list.add(poll.val);
                    queue2.offer(poll.left);
                    queue2.offer(poll.right);
                }
            }
            if (!list.isEmpty()) {
                result.add(list);
            }
            queue = queue2;
        }
        return result;
    }

    /*-----------------------------------------------------------------------------------*/

    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> allList = new LinkedList<>();
        recurCombine(1, n, k, k, new ArrayList<>(), allList);
        for (List<Integer> list : allList) {
            System.out.println("list = " + list);
        }
        return allList;
    }

    public void recurCombine(int start, int n, int allCount, int leftCount, List<Integer> curList,
        List<List<Integer>> allList) {
        if (n - start + 1 < leftCount || leftCount == 0) {
            if (curList.size() == allCount) {
                allList.add(curList);
            }
            return;
        }
        for (int i = start; i <= n - leftCount + 1; i++) {
            List<Integer> newCurList = new ArrayList<>(curList);
            newCurList.add(i);
            recurCombine(i + 1, n, allCount, leftCount - 1, newCurList, allList);
        }
    }

    /*-----------------------------------------------------------------------------------*/

    public List<String> commonChars(String[] words) {
        List<int[]> list = new ArrayList<>();
        for (String s : words) {
            int[] temp = new int[26];
            for (int i = 0; i < s.length(); i++) {
                temp[s.charAt(i) - 'a']++;
            }
            list.add(temp);
        }
        int[] result = new int[26];
        for (int j = 0; j < 26; j++) {
            result[j] = list.get(0)[j];
            for (int i = 0; i < words.length; i++) {
                if (result[j] > list.get(i)[j]) {
                    result[j] = list.get(i)[j];
                }
            }
        }

        List<String> resultCharList = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            if (result[j] > 0) {
                char x = (char)('a' + j);
                for (int i = 0; i < result[j]; i++) {
                    resultCharList.add(String.valueOf(x));
                }

            }
        }
        return resultCharList;
    }

}
