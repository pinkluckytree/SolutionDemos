package com.example.SolutionDemos.Test;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] split = s.split(" ");
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            int x;
            if (split[i].equals("J")) {
                x = 11;
            } else if (split[i].equals("Q")) {
                x = 12;
            } else if (split[i].equals("K")) {
                x = 13;
            } else if (split[i].equals("A")) {
                x = 14;
            } else {
                x = Integer.parseInt(split[i]);
            }
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        boolean flag = false;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 3; i <= 10; i++) {
            int j = i;
            while (map.getOrDefault(j, 0) > 0) {
                j++;
            }
            j = j - 1;
            if (j - i >= 4) {
                flag = true;
                List<Integer> list = new ArrayList<>();
                for (int index = i; index <= j; index++) {
                    list.add(index);
                    map.put(index, map.get(index) - 1);
                }
                ans.add(list);
                if (map.get(i) > 0) {
                    i--;
                }
            }
        }
        if (!flag) {
            System.out.println("No");
        } else {
            ans.sort(new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    if (o1.get(0) - o2.get((0)) == 0) {
                        return o1.size() - o2.size();
                    }
                    return o1.get(0) - o2.get((0));
                }
            });
            for (List<Integer>temp:ans){
                StringBuilder stringBuilder = new StringBuilder();
                for (int x : temp) {
                    if (x <= 10) {
                        stringBuilder.append(x).append(" ");
                    } else if (x == 11) {
                        stringBuilder.append("J").append(" ");
                    } else if (x == 12) {
                        stringBuilder.append("Q").append(" ");
                    } else if (x == 13) {
                        stringBuilder.append("K").append(" ");
                    } else if (x == 14) {
                        stringBuilder.append("A").append(" ");
                    }
                }
                System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
            }
        }

    }

//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String s = in.nextLine();
//        String[] split = s.split(" ");
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < split.length; i++) {
//            int x;
//            if (split[i].equals("J")) {
//                x = 11;
//            } else if (split[i].equals("Q")) {
//                x = 12;
//            } else if (split[i].equals("K")) {
//                x = 13;
//            } else if (split[i].equals("A")) {
//                x = 14;
//            } else {
//                x = Integer.parseInt(split[i]);
//            }
//            map.put(x, map.getOrDefault(x, 0) + 1);
//        }
//        boolean flag= false;
//        for (int i = 3; i <= 10; i++) {
//            int j = i;
//            while (map.getOrDefault(j,0) > 0) {
//                j++;
//            }
//            j = j - 1;
//            if (j - i >= 4) {
//                flag =true;
//                List<Integer> list = new ArrayList<>();
//                for (int index = i; index <= j; index++) {
//                    list.add(index);
//                    map.put(index, map.get(index) - 1);
//                }
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int x : list) {
//                    if (x <= 10) {
//                        stringBuilder.append(x).append(" ");
//                    } else if (x == 11) {
//                        stringBuilder.append("J").append(" ");
//                    } else if (x == 12) {
//                        stringBuilder.append("Q").append(" ");
//                    } else if (x == 13) {
//                        stringBuilder.append("K").append(" ");
//                    } else if (x == 14) {
//                        stringBuilder.append("A").append(" ");
//                    }
//                }
//                System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
//                if(map.get(i)>0){
//                    i--;
//                }
//            }
//        }
//        if(!flag){
//            System.out.println("No");
//        }
//    }
}
