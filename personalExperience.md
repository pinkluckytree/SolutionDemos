记录刷题中的一些经验之谈

回溯
回溯与递归不可分离，回溯一般伴随着剪枝

贪心算法
贪心的本质是选择每一阶段的局部最优，从而达到全局最优。

动态规划

二分查找  
范围查询规律
* 初始化:
```
int left = 0;
int right = nums.length - 1;
```
* 循环条件
```
left <= right
```
* 右边取值
```
right = mid - 1
``` 
* 左边取值
```
left = mid + 1
```   
* 查询条件
```
>= target值, 则 nums[mid] >= target时, 都减right = mid - 1
>  target值, 则 nums[mid] >  target时, 都减right = mid - 1
<= target值, 则 nums[mid] <= target时, 都加left = mid + 1
<  target值, 则 nums[mid] <  target时, 都加left = mid + 1
```
* 结果
```
求大于(含等于), 返回left
求小于(含等于), 返回right
```
* 核心思想: 要找某个值, 则查找时遇到该值时, 当前指针(例如right指针)要错过它, 让另外一个指针(left指针)跨过他(体现在left <= right中的=号), 则找到了