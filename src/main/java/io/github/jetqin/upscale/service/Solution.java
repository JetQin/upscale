package io.github.jetqin.upscale.service;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Stream;

public class Solution {

    /*
     * Complete the simpleArraySum function below.
     */
    static int simpleArraySum(int[] ar) {
        /*
         * Write your code here.
         */
        int sum = 0;
        if(null != ar && ar.length > 0){
            sum = Arrays.stream(ar).map(item -> Integer.valueOf(item)).sum();
        }
        String s = "sfs";
        return sum;

    }

    static String sumTwovalue(long a, long b){
//        Stack<Integer> results = new Stack<>();
//        StringBuilder abuilder = new StringBuilder(String.valueOf(a));
//        StringBuilder bbuilder = new StringBuilder(String.valueOf(b));
//        String avalue = abuilder.reverse().toString();
//        String bvalue = bbuilder.reverse().toString();
//        for(int index = 0; index < avalue.length(); index++){
//            int av = Integer.parseInt(avalue.charAt(index));
//            int bv = Integer.parseInt(bvalue.charAt(index));
//            int result = av + bv;
//            if(result >= 10){
//                if(results.size() >= index){
//                    results.add( result - 10 + results.get(index));
//                }else{
//                    results.add( result - 10);
//                }
//                results.add(1);
//            }else{
//                results.add(result);
//            }
//        }
        return null;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arCount = Integer.parseInt(scanner.nextLine().trim());

        int[] ar = new int[arCount];

        String[] arItems = scanner.nextLine().split(" ");

        for (int arItr = 0; arItr < arCount; arItr++) {
            int arItem = Integer.parseInt(arItems[arItr].trim());
            ar[arItr] = arItem;
        }

        int result = simpleArraySum(ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

