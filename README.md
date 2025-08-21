# Detecting Missing Video Frames 🎥

This repository contains a Java solution for the **Hackathon Candidate Problem-Solving Quiz**:  
**Detecting Missing Video Frames**.

The task is to analyze a list of unordered frame numbers (positive integers starting from 1) and detect which frames are missing due to potential network or hardware issues.

---

## 🚀 Problem Statement [Original PDF](files/Quiz.pdf)

Given an unordered list of frame numbers received by a surveillance platform:

1. Detect the **missing frame ranges (gaps)**.  
2. Identify the **longest missing range**.  
3. Count the **total number of missing frames**.  

### Example

**Input**:
```java
[1, 2, 3, 5, 6, 10, 11, 16]
```
**Expected Output**:
```
{
  "gaps": [[4, 4], [7, 9], [12, 15]],
  "longest_gap": [12, 15],
  "missingCount": 8
}
```

## 🛠️ Solution Overview

The solution is implemented in Java without using built-in sorting functions (.sort()) or third-party libraries like NumPy/Pandas.
Instead, it uses a bucket sort approach to efficiently identify missing ranges.

**Key features**:
* Detects all missing ranges.
* Tracks the longest missing range.
* Counts total missing frames.
* Returns results in JSON format using org.json.

## ⏱️ Time Complexity

The solution uses a **bucket sort–based approach** instead of built-in sorting.

1. **Finding min & max** → `O(n)`  
2. **Distributing numbers into buckets** → `O(n)`  
3. **Scanning buckets for gaps** → `O(k)`, where `k` is the number of buckets (≤ `n`)  

Overall:
- **Time Complexity**: `O(n)`  
- **Space Complexity**: `O(n)` (for storing bucket min/max values)
