package com.skx.tomike.tacticallaboratory.activity;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.imageloader.ImageLoader;

import java.util.Arrays;
import java.util.HashMap;

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

    private TextView tv_sort_theory;
    private TextView tv_sort_process;
    private ImageView iv_sort_exampleImage;

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

        RadioGroup radioGroup = findViewById(R.id.rb_sort_sortGroup);
        radioGroup.setOnCheckedChangeListener(this);

        TextView tv_sort_source = findViewById(R.id.tv_sort_source);
        StringBuilder sb = new StringBuilder();
        for (int t : SOURCE) {
            sb.append("  ").append(t).append("  ");
        }
        tv_sort_source.setText(sb);
        tv_sort_theory = findViewById(R.id.tv_sort_theory);
        tv_sort_process = findViewById(R.id.tv_sort_process);
        iv_sort_exampleImage = findViewById(R.id.iv_sort_exampleImage);

        radioGroup.check(R.id.rbtn_sort_bubbleSort);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbtn_sort_bubbleSort) {
            bubbleSort(SOURCE);

        } else if (checkedId == R.id.rbtn_sort_selectionSort) {
            selectionSort(SOURCE);

        } else if (checkedId == R.id.rbtn_sort_insertSort) {
            insertSort(SOURCE);

        } else if (checkedId == R.id.rbtn_sort_quickSort) {
            quickSort(SOURCE);
        }
    }


    /**
     * 冒泡排序
     * N个数字冒泡排序，总共要进行N-1趟比较，每趟的排序次数为(N-i)次比较
     * <p>
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     *
     * @param source 待排序的数组
     * @return 排序后的数组
     */
    @SuppressLint("SetTextI18n")
    private int[] bubbleSort(int[] source) {
        tv_sort_theory.setText(SORT_THEORY.get("bubbleSort"));
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

            // 渲染排序步骤view
            renderSortProcessView(i, intArray);
        }
        ImageLoader.with(this).asGif().load(R.drawable.sort_bubble).into(iv_sort_exampleImage);

        // 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
        return intArray;
    }

    /**
     * 选择排序.
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 重复第二步，直到所有元素均排序完毕。
     *
     * @param array 待排序数组
     * @return 排序后的数组
     */
    @SuppressLint("SetTextI18n")
    private int[] selectionSort(int[] array) {
        tv_sort_theory.setText(SORT_THEORY.get("selectionSort"));
        tv_sort_process.setText("");

        if (array == null || array.length == 0) {
            return array;
        }

        int[] intArray = Arrays.copyOf(array, array.length);
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

            // 渲染排序步骤view
            renderSortProcessView(i, intArray);
        }
        ImageLoader.with(this).asGif().load(R.drawable.sort_selection).into(iv_sort_exampleImage);

        return array;
    }

    /**
     * 插入排序
     * 插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     *
     * @param source 待排序的数组
     * @return 排序后的数组
     */
    @SuppressLint("SetTextI18n")
    private int[] insertSort(int[] source) {
        tv_sort_theory.setText(SORT_THEORY.get("insertSort"));
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

            // 渲染排序步骤view
            renderSortProcessView(i, intArray);
        }

        ImageLoader.with(this).asGif().load(R.drawable.sort_insertion).into(iv_sort_exampleImage);
        return source;
    }

    /**
     * 快速排序
     *
     * @param source 待排序的数组
     * @return 排序后的数组quickSort.gif
     */
    private int[] quickSort(int[] source) {
        tv_sort_theory.setText(SORT_THEORY.get("quickSort"));
        tv_sort_process.setText("");

        if (source == null || source.length == 0) {
            return source;
        }

        int[] intArray = Arrays.copyOf(source, source.length);
        quickSort(intArray, 0, intArray.length - 1);

        return intArray;
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);

            renderSortProcessView(0, arr);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int next = pivot + 1;
        for (int i = next; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, next);
                next++;
            }
        }
        swap(arr, pivot, next - 1);
        return next - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void renderSortProcessView(int i, int[] intArray) {
        StringBuilder sb = new StringBuilder("第").append(i + 1).append("排序后：");
        for (int t : intArray) {
            sb.append("  ").append(t).append("  ");
        }
        sb.append("\n");
        tv_sort_process.append(sb);
    }

    private final HashMap<String, String> SORT_THEORY = new HashMap<>();

    {
        SORT_THEORY.put("bubbleSort", "冒泡排序思路：\n比较相邻的元素。如果第一个比第二个大，就交换他们两个。" +
                "对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。" +
                "针对所有的元素重复以上的步骤，除了最后一个。" +
                "持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。");
        SORT_THEORY.put("selectionSort", "选择排序思路：\n首先在未排序序列中找到最小（大）元素，" +
                "存放到排序序列的起始位置。再从剩余未排序元素中继续寻找最小（大）元素，" +
                "然后放到已排序序列的末尾。重复第二步，直到所有元素均排序完毕。");
        SORT_THEORY.put("insertSort", "插入排序原理：\n通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入" +
                "\n\n步骤：\n将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。" +
                "从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）");
        SORT_THEORY.put("quickSort", "插入排序原理：\n使用分治法（Divide and conquer）策略来把一个串行（list）分为两个子串行（sub-lists）。" +
                "\n\n步骤：\n将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。" +
                "从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）");
    }

}
