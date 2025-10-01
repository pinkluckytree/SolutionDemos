package com.example.SolutionDemos.Solutions;

import java.util.*;

public class Solution {
    /**
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
     *
     * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
     *
     * 0 <= j <= nums[i] 且 i + j < n 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
     * 
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n-1];
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMaxHeight = new int[n];
        leftMaxHeight[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMaxHeight[i] = Math.max(leftMaxHeight[i - 1], height[i]);
        }
        int[] rightMaxHeight = new int[n];
        rightMaxHeight[n - 1] = height[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            rightMaxHeight[j] = Math.max(rightMaxHeight[j + 1], height[j]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i];
        }
        return ans;
    }

    /**
     * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     *
     * 注意：解集不能包含重复的组合。
     * 
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        recurCombinationSum2(candidates, 0, 0, target, new ArrayList<>());
        return combinationSum2Ans;
    }

    public List<List<Integer>> combinationSum2Ans = new ArrayList<>();

    public void recurCombinationSum2(int[] nums, int start, int currentSum, int target, List<Integer> list) {
        if (currentSum == target) {
            combinationSum2Ans.add(new ArrayList<>(list));
            return;
        } else if (currentSum > target) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 同一层的选择中还不允许选择相同元素
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            recurCombinationSum2(nums, i + 1, currentSum + nums[i], target, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 35. 搜索插入位置
     * 
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * 
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length >= 1 && nums[nums.length - 1] >= target) {
            int left = binarySearch(nums, target);
            int right = binarySearch(nums, target + 1);
            if (nums[left] == target) {
                return new int[] {left, right - 1};
            }
        }
        return new int[] {-1, -1};
    }

    /**
     * 找到非递减数组中第一个大于等于target的下标
     * 
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] >= target) {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 下一个排列
     */
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int p = nums.length - 2;
        while (p >= 0 && nums[p] >= nums[p + 1]) {
            p--;
        }
        int q = nums.length - 1;
        if (p >= 0) {
            while (nums[q] <= nums[p]) {
                q--;
            }
            swapNumberInNums(nums, p, q);
        }
        p++;
        q = nums.length - 1;
        while (p < q) {
            swapNumberInNums(nums, p, q);
            p++;
            q--;
        }
    }

    public void swapNumberInNums(int[] nums, int p, int q) {
        int temp = nums[q];
        nums[q] = nums[p];
        nums[p] = temp;
    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.equals("")) {
            return new ArrayList<>();
        }
        char[] numberChar = digits.toCharArray();
        Map<Character, String[]> map = new HashMap<>();
        map.put('2', new String[] {"a", "b", "c"});
        map.put('3', new String[] {"d", "e", "f"});
        map.put('4', new String[] {"g", "h", "i"});
        map.put('5', new String[] {"j", "k", "l"});
        map.put('6', new String[] {"m", "n", "o"});
        map.put('7', new String[] {"p", "q", "r", "s"});
        map.put('8', new String[] {"t", "u", "v"});
        map.put('9', new String[] {"w", "x", "y", "z"});
        recurCom(numberChar, map, 0, new StringBuilder());
        return new ArrayList<>(letterCombinationsAns);
    }

    public Set<String> letterCombinationsAns = new HashSet<>();

    public void recurCom(char[] number, Map<Character, String[]> map, int index, StringBuilder str) {
        if (index >= number.length) {
            letterCombinationsAns.add(str.toString());
            return;
        }
        for (String s : map.get(number[index])) {
            str.append(s);
            recurCom(number, map, index + 1, str);
            str.deleteCharAt(str.length() - 1);
        }
    }

    /**
     * 三数之和
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                return list;
            }
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == -nums[i]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    list.add(temp);
                    while (right > left && nums[right] == nums[right - 1])
                        right--;
                    while (right > left && nums[left] == nums[left + 1])
                        left++;
                    right--;
                    left++;
                } else if (nums[left] + nums[right] < -nums[i]) {
                    // 说明两者之和需要大一些，左指针右移动
                    left++;
                } else {
                    right--;
                }
            }
        }
        return list;
    }

    /**
     * 给你一个正整数 n 。
     *
     * 请你将 n 的值替换为 n 的 质因数 之和，重复这一过程。
     *
     * 注意，如果 n 能够被某个质因数多次整除，则在求和时，应当包含这个质因数同样次数。 返回 n 可以取到的最小值。
     */
    public int smallestValue(int n) {
        int temp = getSumOfZhiYin(n);
        while (n > temp) {
            n = temp;
            temp = getSumOfZhiYin(n);
        }
        return n;
    }

    public int getSumOfZhiYin(int n) {
        if (n <= 2) {
            return n;
        }
        int sum = 0;
        while (n > 1) {
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    n = n / i;
                    sum += i;
                    break;
                }

            }
        }
        return sum;
    }

    /**
     * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
     */
    public boolean repeatedSubstringPattern(String s) {
        int i = 1;
        int len = s.length();
        while (i < len) {
            if (len % i != 0) {
                i++;
                continue;
            }
            if (s.substring(0, i).equals(s.substring(len - i, len))
                && s.substring(i, len).equals(s.substring(0, len - i))) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 每个右括号都有一个对应的相同类型的左括号。
     * 
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (c == '}' && pop != '{') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
                if (c == ')' && pop != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     *
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层（从第 0 层开始），则该层包含 1~ 2h 个节点。
     * 
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        return recurCountNodes(root);
    }

    public int recurCountNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + recurCountNodes(node.right) + recurCountNodes(node.left);
    }

    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * 
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        invertTreeRecur(root);
        return root;
    }

    public void invertTreeRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        invertTreeRecur(node.left);
        invertTreeRecur(node.right);
    }

    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回 滑动窗口中的最大值 。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int right;
        int left = 0;
        int[] result = new int[n - k + 1];
        TreeMap<Integer, Integer> occurTimes = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
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
            occurTimes.put(nums[right - 1], occurTimes.getOrDefault(nums[right - 1], 0) - 1);
            occurTimes.put(nums[left], occurTimes.getOrDefault(nums[left], 0) + 1);
            left++;
            while (occurTimes.get(occurTimes.firstKey()) <= 0) {
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
    // public List<List<Integer>> fourSum(int[] nums, int target) {
    // Arrays.sort(nums);
    // int n = nums.length;
    // List<Integer> list = new ArrayList<>();
    // Map<String, List<Integer>> map = new HashMap<>();
    // long sum = 0;
    // for (int i = 0; i < n - 3; i++) {
    // list.add(nums[i]);
    // sum += nums[i];
    // for (int j = i + 1; j < n - 2; j++) {
    // if (nums[j] > 0 && sum + nums[j] > target) {
    // continue;
    // }
    // sum += nums[j];
    // list.add(nums[j]);
    // int r = j + 1;
    // int l = n - 1;
    // while (r < l) {
    // long tempSum = nums[r] + nums[l];
    // if (tempSum == target - sum) {
    // list.add(nums[r]);
    // list.add(nums[l]);
    // map.put(list.toString(), new ArrayList<>(list));
    // list.removeLast();
    // list.removeLast();
    // r++;
    // }
    // if (tempSum < target - sum) {
    // r++;
    // }
    // if (tempSum > target - sum) {
    // l--;
    // }
    // }
    // sum -= nums[j];
    // list.removeLast();
    // }
    // sum -= nums[i];
    // list.removeLast();
    // }
    // List<List<Integer>> result = new ArrayList<>();
    // map.forEach((k, v) -> {
    // result.add(v);
    // });
    // return result;
    // }

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
