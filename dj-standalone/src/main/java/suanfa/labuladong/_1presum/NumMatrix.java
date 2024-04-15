package suanfa.labuladong._1presum;

/**
 * @author jiandong 2024-04-15 create
 *
 * 二维矩阵中的前缀和
 *
 * 304. 二维区域和检索 - 矩阵不可变
 *
 * Key points：构造方法注入 前缀和数组，使方法sumRegion的复杂度由O(n^2)降为O(1)
 */
public class NumMatrix {
    // preSum[i][j] 记录矩阵 [0, 0, i, j] 的元素和
    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) {
            return;
        }
        // 构造前缀和矩阵
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 计算每个矩阵 [0, 0, i, j] 的元素和
                // 看图形就知道
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    // 计算子矩阵 [x1, y1, x2, y2] 的元素和
    public int sumRegion(int x1, int y1, int x2, int y2) {
        // 目标矩阵之和由四个相邻矩阵运算获得
        return preSum[x2+1][y2+1] - preSum[x1][y2+1] - preSum[x2+1][y1] + preSum[x1][y1];
    }

}
