package com.skx.tomike.tacticallaboratory.activity;

import android.util.Log;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tacticallaboratory.R;

import java.util.Arrays;

/**
 * 描述 : 数据结构 - 数组 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
public class ArrayDemoActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 数组").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_structure_array;
    }

    @Override
    protected void initView() {

        int[] source = {9, 8, 7, 6, 5, 40, 30, 20, 10, 0};

        StringBuilder stringBuilder = new StringBuilder();
        for (int value : source) {
            stringBuilder.append(value).append(",");
        }
        Log.e(TAG, "source:" + stringBuilder.toString());

        System.arraycopy(source, 5, source, 0, 5);

        StringBuilder stringBuilder2 = new StringBuilder();
        for (int value : source) {
            stringBuilder2.append(value).append(",");
        }
        Log.e(TAG, "arraycopy:" + stringBuilder2.toString());


        int[] copyOf = Arrays.copyOf(source, 5);
        StringBuilder stringBuilder3 = new StringBuilder();
        for (int value : copyOf) {
            stringBuilder3.append(value).append(",");
        }
        Log.e(TAG, "copyOf:" + stringBuilder3.toString());

        int[] copyOfRange = Arrays.copyOfRange(source, 3, 6);
        StringBuilder stringBuilder4 = new StringBuilder();
        for (int value : copyOfRange) {
            stringBuilder4.append(value).append(",");
        }
        Log.e(TAG, "copyOfRange:" + stringBuilder4.toString());

    }


    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 条件：
     * 1.不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 2.元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * <p>
     * 示例->
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,3,0,4]
     *
     * @param nums 数组
     * @param val  需要删除的值
     * @return 移除后数组的新长度
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;
        int size = nums.length;

        while (fast < size) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }


    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                result++;
            } else {
                count = Math.max(count, result);
                result = 0;
            }
        }

        return Math.max(result, count);
    }

}
