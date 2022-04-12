package com.zeroheat.codingprogrammers.summer

//주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다. 숫자들이 들어있는 배열 nums가 매개변수로 주어질 때, nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수를 return 하도록 solution 함수를 완성해주세요.
//
//제한사항
//nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
//nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.

class sosu {
    fun solution(nums: IntArray):Int{
        var answer = 0
        var check = false

        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                for (k in j + 1 until nums.size) {
                    val num = nums[i] + nums[j] + nums[k] //값을 담아
                    if (num >= 2) //경우의 수를 찾아서
                        check = sosu(num)
                    if (check == true) //소수가 맞을 경우
                        answer++
                }
            }
        }
        return answer
    }

    fun sosu(num: Int): Boolean{
        var check = true
        if (num==2){
            return check
        }
        for (i in 2 until num){
            if (num%i ==0){
                check = false
                break
            }
        }
        return check
    }
}


//자바코드
//class Solution {
//
//    public int solution(int[] nums) {
//        int ans = 0;
//
//        for(int i = 0; i < nums.length - 2; i ++){
//            for(int j = i + 1; j < nums.length - 1; j ++){
//                for(int k = j + 1; k < nums.length; k ++ ){
//                    if(isPrime(nums[i] + nums[j] + nums[k])){
//                        ans += 1;
//                    }
//                }
//            }
//        }
//        return ans;
//    }
//    public Boolean isPrime(int num){
//        int cnt = 0;
//        for(int i = 1; i <= (int)Math.sqrt(num); i ++){
//            if(num % i == 0) cnt += 1;
//        }
//        return cnt == 1;
//    }
//}