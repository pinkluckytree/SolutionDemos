package com.example.SolutionDemos.Solutions;

import java.util.*;

public class Solution {
    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回 滑动窗口中的最大值 。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int right;
        int left = 0;
        int []result = new int[n-k+1];
        TreeMap<Integer, Integer> occurTimes = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (right = 0; right < n; right++) {
            if (right == 0) {
                while (left < k) {
                    occurTimes.put(nums[left], occurTimes.getOrDefault(nums[left], 0) + 1);
                    left++;
                }
                result[0] = occurTimes.firstKey();
                continue;
            }
            occurTimes.put(nums[right-1], occurTimes.getOrDefault(nums[right-1], 0) - 1);
            occurTimes.put(nums[left], occurTimes.getOrDefault(nums[left], 0) + 1);
            left++;
            while (occurTimes.get(occurTimes.firstKey())<=0){
                occurTimes.pollFirstEntry();
            }
            result[right] = occurTimes.firstKey();
        }
        return result;
    }

    /**
     * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurTimes = new HashMap<>();
        for (int x : nums) {
            occurTimes.put(x, occurTimes.getOrDefault(x, 0) + 1);
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurTimes.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (minHeap.size() < k) {
                minHeap.offer(new int[] {key, value});
                continue;
            }
            int[] peek = minHeap.peek();
            assert peek != null;
            if (peek[1] < value) {
                minHeap.poll();
                minHeap.offer(new int[] {key, value});
            }
        }
        int[] result = new int[k];
        int i = 0;
        while (minHeap.size() > 0) {
            result[i] = minHeap.poll()[0];
            i++;
        }
        return result;
    }

    /**
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     */
    public String reverseStr(String s, int k) {
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i = i + 2 * k) {
            if (i + 2 * k > array.length) {
                reverseStrHelper(array, i, Math.min(i + k - 1, array.length - 1));
            } else {
                reverseStrHelper(array, i, i + k - 1);
            }
        }
        return String.valueOf(array);
    }

    public void reverseStrHelper(char[] s, int r, int l) {
        while (r < l) {
            char tempC = s[r];
            s[r] = s[l];
            s[l] = tempC;
            r++;
            l--;
        }
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题
     */
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char tempC = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = tempC;
        }
    }

    /**
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]
     * （若两个四元组元素一一对应，则认为两个四元组重复）：
     *
     * 0 <= a, b, c, d < n a、b、c 和 d 互不相同 nums[a] + nums[b] + nums[c] + nums[d] == target 你可以按 任意顺序 返回答案 。
     */
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        Arrays.sort(nums);
//        int n = nums.length;
//        List<Integer> list = new ArrayList<>();
//        Map<String, List<Integer>> map = new HashMap<>();
//        long sum = 0;
//        for (int i = 0; i < n - 3; i++) {
//            list.add(nums[i]);
//            sum += nums[i];
//            for (int j = i + 1; j < n - 2; j++) {
//                if (nums[j] > 0 && sum + nums[j] > target) {
//                    continue;
//                }
//                sum += nums[j];
//                list.add(nums[j]);
//                int r = j + 1;
//                int l = n - 1;
//                while (r < l) {
//                    long tempSum = nums[r] + nums[l];
//                    if (tempSum == target - sum) {
//                        list.add(nums[r]);
//                        list.add(nums[l]);
//                        map.put(list.toString(), new ArrayList<>(list));
//                        list.removeLast();
//                        list.removeLast();
//                        r++;
//                    }
//                    if (tempSum < target - sum) {
//                        r++;
//                    }
//                    if (tempSum > target - sum) {
//                        l--;
//                    }
//                }
//                sum -= nums[j];
//                list.removeLast();
//            }
//            sum -= nums[i];
//            list.removeLast();
//        }
//        List<List<Integer>> result = new ArrayList<>();
//        map.forEach((k, v) -> {
//            result.add(v);
//        });
//        return result;
//    }

    /**
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     *
     * 如果可以，返回 true ；否则返回 false 。
     *
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] chars1 = ransomNote.toCharArray();
        char[] chars2 = magazine.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char x : chars2) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (char y : chars1) {
            map.put(y, map.getOrDefault(y, 0) - 1);
            if (map.get(y) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     *
     * 0 <= i, j, k, l < n nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.put((long)(nums1[i] + nums2[j]), map.getOrDefault((long)(nums1[i] + nums2[j]), 0) + 1);
            }
        }
        int times = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long sum = nums3[i] + nums4[j];
                times += map.getOrDefault((long)(-sum), 0);
            }
        }
        return times;
    }

    /**
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     */
    public ListNode swapPairs(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        ListNode fakeHead = new ListNode();
        ListNode wCur = fakeHead;
        while (cur != null) {
            if (stack.size() == 2) {
                while (!stack.isEmpty()) {
                    ListNode pop = stack.pop();
                    pop.next = null;
                    wCur.next = pop;
                    wCur = wCur.next;
                }
            }
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            pop.next = null;
            wCur.next = pop;
            wCur = wCur.next;
        }
        return fakeHead.next;
    }

    /**
     * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     *
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎； 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量
     * 。如果没有石头剩下，就返回 0。
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int t = sum / 2;
        // dp[i][j]表示在前i个元素中拿到的不大于j的x个石头的和的最大值
        int[][] dp = new int[stones.length + 1][t + 1];
        for (int i = 1; i <= stones.length; i++) {
            for (int j = 0; j <= t; j++) {
                // 每一次都会面临选与不选第i个石头
                if (j < stones[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }
        return sum - 2 * dp[stones.length][t];
    }

    /**
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     *
     * 你需要按照以下要求，给这些孩子分发糖果：
     *
     * 每个孩子至少分配到 1 个糖果。 相邻两个孩子中，评分更高的那个会获得更多的糖果。 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */
    public int candy(int[] ratings) {
        int[] dp = new int[ratings.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int sum = 0;
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                dp[i] = Math.max(dp[i + 1] + 1, dp[i]);
            }
            sum += dp[i];
        }
        return sum + dp[ratings.length - 1];
    }

    /**
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     *
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * 返回 你能获得的 最大 利润
     * 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int allProfit = 0;
        int latestProfit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minPrice && prices[i] > prices[i - 1]) {
                latestProfit = prices[i] - minPrice;
                continue;
            }
            if (prices[i] < minPrice || prices[i] < prices[i - 1]) {
                minPrice = prices[i];
                allProfit += latestProfit;
                latestProfit = 0;
            }
        }
        return allProfit + latestProfit;
    }
    /*-----------------------------------------------------------------------------------*/

    /**
     * 全排列：给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        recurPermute(nums, new int[nums.length], new ArrayList<>());
        return ansList;
    }

    public List<List<Integer>> ansList = new ArrayList<>();

    public void recurPermute(int[] nums, int[] flags, List<Integer> list) {
        if (list.size() == nums.length) {
            ansList.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flags[i] != 1) {
                Integer integer = nums[i];
                list.add(nums[i]);
                flags[i] = 1;
                recurPermute(nums, flags, list);
                list.remove(integer);
                flags[i] = 0;
            }
        }
    }

    /*-----------------------------------------------------------------------------------*/

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
            if (currentGas < 0 || i - j >= n) {
                // 无法继续行驶
                if (i - j >= n) {
                    return j;
                }
                j = i + 1;
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
