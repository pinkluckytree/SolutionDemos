package com.example.SolutionDemos.Solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
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
