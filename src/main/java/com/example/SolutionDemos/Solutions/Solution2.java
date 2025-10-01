package com.example.SolutionDemos.Solutions;

public class Solution2 {

    public int maxLength(int[] nums) {
        // write code here
        int maxLength = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                int right = i - 1;
                int left = i + 1;
                while (right >= 0) {
                    if (nums[right] < nums[right + 1]) {
                        right--;
                    } else {
                        right++;
                        break;
                    }
                }
                if(right<0){
                    right=0;
                }
                while (left < nums.length) {
                    if (nums[left] < nums[left - 1]) {
                        left++;
                    } else {
                        right++;
                        break;
                    }
                }
                if(left>=nums.length){
                    left = nums.length-1;
                }
                maxLength = Math.max(maxLength,left-right+1);

                i=left-1;
            }
        }
        return maxLength;
    }
}
