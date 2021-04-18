package com.xj.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author xj
 * @date 2021年04月18日 10:45
 * @description 返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
 * 字典序 a~z 序列
 */
public class 不同字符的最小子序列_1081 {

    /**
     * @param s
     * @return java.lang.String
     * @description 单调栈
     * @author xj
     * @date 2021/4/18
     * <p>
     * 解题思路 ： 先判断 元素 是否需要舍弃 判断 是否有元素需要出栈 最后元素入栈
     * 舍弃元素： 当 栈已经包含该元素 则舍弃 元素           入栈的元素一定是 阶段性有序的
     * （当前元素 不必再移除栈顶元素 即使移除了也一样 只不过在最后还是需要 判断是否在栈中）
     * 出栈： 该元素 字典序 小于栈顶元素 && 栈顶元素 在以后还会再出现
     * 入栈： 该元素 没有入栈
     */
    public static String solution(String s) {
        //将 string 转化成 char 数组
        char[] chars = s.toCharArray();
        //标记 char 数组 最后出现的序号
        int[] lastIndex = new int[26];
        //初始化 lastIndex数组
        for (int i = 0; i < chars.length; i++) {
            lastIndex[chars[i] - 'a'] = i;
        }
        //标记 char 数组 是否已存在
        boolean[] isExist = new boolean[26];

        //创建 单调栈
        Deque<Character> stack = new ArrayDeque<>();
        //循环 char数组
        for (int i = 0; i < chars.length; i++) {

            //如果 元素 已经在栈中 则遗弃该元素
            if (isExist[chars[i] - 'a']) {
                continue;
            }

            // 该元素  字典序在 栈顶元素的前面 且 后面还会出现 该元素 -> 栈顶元素出栈 当前元素如栈
            while (!stack.isEmpty() && stack.peekLast() > chars[i] && lastIndex[stack.peekLast() - 'a'] > i) {
                //当前元素 不在栈中
                isExist[stack.peekLast() - 'a'] = false;
                //移除栈顶 元素
                stack.removeLast();
            }

            //入栈
            stack.addLast(chars[i]);
            //当前元素 已在栈中
            isExist[chars[i] - 'a'] = true;
        }
        //输出 最小字典序的最小子序列
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : stack
        ) {
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }


    public static void main(String[] args) {
        String str = solution("cbacdcbc");
        System.out.println(str);
    }

}
