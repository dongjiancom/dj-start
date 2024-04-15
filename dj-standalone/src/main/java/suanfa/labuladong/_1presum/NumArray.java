package suanfa.labuladong._1presum;

/**
 * @author jiandong 2024-04-15 create
 *
 * 一维数组中的前缀和
 *
 * 303. 区域和检索 - 数组不可变
 *
 * Key points：构造方法注入 前缀和数组，使方法sumRange的复杂度由O(n)降为O(1)
 */
public class NumArray {

    // 前缀和数组
    private int[] preSum;

    /* 输入一个数组，构造前缀和 */
    public NumArray(int[] nums) {
        int n = nums.length;
        preSum = new int[n + 1];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    /* 查询闭区间 [left, right] 的累加和 */
    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }
}
