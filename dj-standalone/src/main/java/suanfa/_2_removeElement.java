package suanfa;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author jiandong 2024-01-07 create
 * 教训：边界条件，数组越界
 * 知识点：单数组内的双指针
 *
 */
public class _2_removeElement {

    @Test
    public void removeElementTest(){
        int[] nums = {3,2,2,3};
        int val = 3;
        int result = removeElement(nums, val);
        System.out.println(JSONObject.toJSONString(nums));
        System.out.println(result);
    }

    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length -1;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

}