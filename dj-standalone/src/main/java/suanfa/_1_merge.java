package suanfa;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author jiandong 2024-01-06 create
 * 教训：数组是引用传递 nums1=nums2 不生效，要for循环 nums1[i]=nums2[i]，仅在leetcode上是这样？ 因为本地打印是可以的
 * 知识点：双指针、逆向 变为，谁大取谁，可不覆盖元素
 *
 *
 */
public class _1_merge {

    @Test
    public void mergeTest3(){
        int[] nums1 = {0};
        int[] nums2 = {1};
        // 打印
        System.out.println(JSON.toJSONString(nums1));
        System.out.println(JSON.toJSONString(nums2));
        merge(nums1, 0, nums2, 1);
    }
    @Test
    public void mergeTest2(){
        int[] nums1 = {1};
        int[] nums2 = {};
        // 打印
        System.out.println(JSON.toJSONString(nums1));
        System.out.println(JSON.toJSONString(nums2));
        merge(nums1, 1, nums2, 0);
    }
    @Test
    public void mergeTest1(){
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        // 打印
        System.out.println(JSON.toJSONString(nums1));
        System.out.println(JSON.toJSONString(nums2));
        merge(nums1, 3, nums2, 3);
    }




    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(0==m){
//            nums1 = nums2;
            for(int i=0; i<n; i++){
                nums1[i] = nums2[i];
            }
        } else if(0==n){
        } else {
            int [] result = new int[m+n];
            int nums1Index = 0;
            int nums2Index = 0;
            for(int i=0; i<m+n; i++){
                if(nums1Index==m){
                    // 1 取完；取 2
                    result[i] = nums2[nums2Index];
                    nums2Index ++;
                    continue;
                }
                if(nums2Index==n){
                    // 2 取完；取 1
                    result[i] = nums1[nums1Index];
                    nums1Index ++;
                    continue;
                }
                if(nums1[nums1Index] <= nums2[nums2Index]){
                    // 哪个小，取哪个
                    result[i] = nums1[nums1Index];
                    nums1Index ++;
                } else {
                    result[i] = nums2[nums2Index];
                    nums2Index ++;
                }
            }
//            nums1 = result;
            for(int i=0; i<m+n; i++){
                nums1[i] = result[i];
            }
        }
        System.out.println(JSON.toJSONString(nums1));
    }

}