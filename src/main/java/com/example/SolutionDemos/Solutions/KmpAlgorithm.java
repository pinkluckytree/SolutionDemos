package com.example.SolutionDemos.Solutions;

public class KmpAlgorithm {
    /**
     * 在haystack找到needle第一次出现时的下标
     * 
     * @param haystack
     * @param needle
     * @return
     */
    public int kmp(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int[] pwt = calculatePWT(needle);
        for (int i = 0, j = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pwt[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if(j==needle.length()) {
                return i-j+1;
            }
        }
        return -1;
    }

    public int[] calculatePWT(String needle) {
        int length = needle.length();
        int[] pwt = new int[length];
        for (int i = 1, j = 0; i < length; i++) {
            while (j > 0 && needle.charAt(j) != needle.charAt(i)) {
                j = pwt[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pwt[i] = j;
        }
        return pwt;
    }

}
