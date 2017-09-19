package com.aric.queue;

import com.aric.common.utils.PrinterUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 数据结构之队列
 * Created by tom.lee
 * <p/>
 * 队列常用方法：
 * void offer ：将元素添加到队尾
 * E peek ：查看队头元素
 * E poll : 移除队头元素，并返回
 * boolean isEmpty ： 判断队列是否为空
 * int size : 查看队列元素个数
 */
public class JDKQueue {


    public void jdkQueue() {
        Queue<Integer> queue = new LinkedList<Integer>();
        PrinterUtils.printELog("队列大小：" + queue.size());

        Integer arr[] = {1, 2, 3, 4, 5};
        //队列里放元素
        for (int i : arr) {
            queue.offer(i);
        }
        PrinterUtils.printELog("队列大小：" + queue.size());

        //移除
        while (!queue.isEmpty()) {
            PrinterUtils.printELog(
                    "队列头部元素：" + queue.peek() +
                            ",出队：" + queue.poll());
        }


    }


}
