package com.theshoqanebi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] list = {1, 2, 3, 5, 6, 10, 11, 16};
        System.out.println(sol.maximumGap(list).toJson());

        int[] reversed_list = {16, 11, 10, 6, 5, 3, 2, 1};
        System.out.println(sol.maximumGap(reversed_list).toJson());
    }

    public Result maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new Result(new int[0][0], new int[0], 0);
        }

        if (nums.length == 2) {
            return new Result(
                    new int[][]{{nums[0], nums[1]}},
                    Math.abs(nums[1] - nums[0]) > 1 ? new int[]{Math.min(nums[0], nums[1]), Math.max(nums[0], nums[1])} : new int[]{},
                    Math.max(nums[0], nums[1]) - Math.min(nums[0], nums[1]) + 1 - 2
            );
        }

        return bucketSort(nums);
    }

    public Result bucketSort(int[] nums) {
        int n = nums.length;
        int max = 0, min = Integer.MAX_VALUE;

        for (int i : nums) {
            if (i > max) max = i;
            if (i < min) min = i;
        }

        if (min == max) return new Result(new int[0][0], new int[0], 0);

        int bucketSize = (max - min) / n + 1;
        int bucketCount = (max - min) / bucketSize + 1;

        int[] minBucket = new int[bucketCount];
        int[] maxBucket = new int[bucketCount];
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        Arrays.fill(maxBucket, Integer.MIN_VALUE);

        for (int x : nums) {
            int index = (x - min) / bucketSize;
            minBucket[index] = Math.min(minBucket[index], x);
            maxBucket[index] = Math.max(maxBucket[index], x);
        }

        ArrayList<int[]> gapsList = new ArrayList<>();
        int[] longestGap = {-1, -1};
        int maxGapLength = 0;

        int prevMax = maxBucket[0];
        for (int i = 1; i < bucketCount; i++) {
            if (minBucket[i] == Integer.MAX_VALUE) continue;

            if (minBucket[i] > prevMax + 1) {
                int gapStart = prevMax + 1;
                int gapEnd = minBucket[i] - 1;
                int gapLen = gapEnd - gapStart + 1;

                gapsList.add(new int[]{gapStart, gapEnd});

                if (gapLen > maxGapLength) {
                    maxGapLength = gapLen;
                    longestGap = new int[]{gapStart, gapEnd};
                }
            }
            prevMax = maxBucket[i];
        }

        int[][] gaps = gapsList.toArray(new int[0][0]);
        return new Result(gaps, longestGap, max - min + 1 - nums.length);
    }

    static class Result {
        int[][] gaps;
        int[] longestGap;
        int missingCount;

        public Result(int[][] gaps, int[] longestGap, int missingCount) {
            this.gaps = gaps;
            this.longestGap = longestGap;
            this.missingCount = missingCount;
        }

        public String toJson() {
            JSONObject resultJson = new JSONObject();
            resultJson.put("missingCount", missingCount);

            JSONArray gapsArray = new JSONArray();
            for (int[] gap : gaps) {
                JSONArray arr = new JSONArray();
                arr.put(gap[0]);
                arr.put(gap[1]);
                gapsArray.put(arr);
            }
            resultJson.put("gaps", gapsArray);

            JSONArray longestGapArray = new JSONArray();
            longestGapArray.put(longestGap[0]);
            longestGapArray.put(longestGap[1]);
            resultJson.put("longest_gap", longestGapArray);

            return resultJson.toString();
        }
    }
}