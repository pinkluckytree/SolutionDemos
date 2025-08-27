package com.example.SolutionDemos.Solutions;

import java.util.Arrays;
import java.util.List;

public class SolutionMain {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<List<Integer>> combine = solution.combine(10, 9);
        int []nums = new int[]{1,3,-1,-3,5,3,6,7};
        int[] ints = solution.maxSlidingWindow(nums, 3);
        System.out.println("ints = " + Arrays.toString(ints));
    }
}
