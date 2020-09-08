package com.skx.tomike.tacticallaboratory.activity;

import android.widget.RadioGroup;
import android.widget.TextView;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.util.Arrays;

/**
 * 描述 : 排序算法
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/7 2:25 PM
 */
public class SortDemoActivity extends SkxBaseActivity<BaseViewModel> implements RadioGroup.OnCheckedChangeListener {

    //    private static final int[] SOURCE = {9, 7, 2, 1, 3, 5, 8, 6, 4};
    private final int[] SOURCE = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//    private final int[] SOURCE = {1, 3, 2, 5, 4, 7, 9, 6, 8};

    private TextView tv_sort_process;

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("排序算法").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sort_algorithm;
    }

    @Override
    protected void initView() {
        tv_sort_process = findViewById(R.id.tv_sort_process);
        TextView tv_sort_source = findViewById(R.id.tv_sort_source);
        StringBuilder sb = new StringBuilder();
        for (int t : SOURCE) {
            sb.append("  ").append(t).append("  ");
        }
        tv_sort_source.setText(sb);

        RadioGroup radioGroup = findViewById(R.id.rb_sort_sortGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbtn_sort_bubbleSort) {
            bubbleSort(SOURCE);

        } else if (checkedId == R.id.rbtn_sort_selectionSort) {
            selectionSort(SOURCE);

        } else if (checkedId == R.id.rbtn_sort_insertSort) {
            insertSort(SOURCE);
        }
    }

    /**
     * 插入排序
     * 插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     *
     * @param source 排序的数组
     * @return 排序后的数组
     */
    private int[] insertSort(int[] source) {
        tv_sort_process.setText("");
        if (source == null || source.length == 0) {
            return source;
        }

        int[] intArray = Arrays.copyOf(source, source.length);

        int next;
        for (int i = 0; i < intArray.length - 1; i++) {
            next = intArray[i + 1];// 下一个要比较的数
            int current = i;// 当前索引

            // 取下一个值，不断的向前比较，遇到比next 大的，就把当前索引的值复制到下一个索引上，以此来模拟插入。
            while (current >= 0 && next < intArray[current]) {
                intArray[current + 1] = intArray[current];
                // 索引减一，同前一个数据再对比。
                current--;
            }
            intArray[++current] = next;// 这里++ 是因为上一步满足条件后，先 "--" 同前一个数据比较，但是条件不符合，所以再其后的一个位置插入

            StringBuilder sb = new StringBuilder("第").append(i + 1).append("排序后：");
            for (int t : intArray) {
                sb.append("  ").append(t).append("  ");
            }
            sb.append("\n");
            tv_sort_process.append(sb);
        }

        return source;
    }

    /**
     * 冒泡排序
     * N个数字冒泡排序，总共要进行N-1趟比较，每趟的排序次数为(N-i)次比较
     *
     * @param source
     * @return
     */
    private int[] bubbleSort(int[] source) {
        tv_sort_process.setText("");
        if (source == null || source.length == 0) {
            return source;
        }

        int[] intArray = Arrays.copyOf(source, source.length);

        for (int i = 0; i < intArray.length - 1; i++) {
            for (int j = 0; j < intArray.length - 1 - i; j++) {
                if (intArray[j + 1] < intArray[j]) {
                    int temp = intArray[j + 1];
                    intArray[j + 1] = intArray[j];
                    intArray[j] = temp;
                }
            }

            StringBuilder sb = new StringBuilder("第").append(i + 1).append("排序后：");
            for (int t : intArray) {
                sb.append("  ").append(t).append("  ");
            }
            sb.append("\n");
            tv_sort_process.append(sb);
        }
        // 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
        return intArray;
    }

    /**
     * 选择排序.
     * <p>
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 重复第二步，直到所有元素均排序完毕。
     */
    private int[] selectionSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int[] intArray = Arrays.copyOf(array, array.length);
        tv_sort_process.setText("");
        for (int i = 0; i < intArray.length - 1; i++) {// 总共需要经过 N-1 轮比较
            int minIndex = i;// 假设最小数的索引为 i

            // 每一个元素都和剩下的未排序的元素比较
            for (int j = i + 1; j < intArray.length; j++) {
                if (intArray[j] < intArray[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = intArray[minIndex];
            intArray[minIndex] = intArray[i];
            intArray[i] = temp;

            StringBuilder sb = new StringBuilder("第").append(i + 1).append("排序后：");
            for (int t : intArray) {
                sb.append("  ").append(t).append("  ");
            }
            sb.append("\n");
            tv_sort_process.append(sb);
        }
        return array;
    }

}
