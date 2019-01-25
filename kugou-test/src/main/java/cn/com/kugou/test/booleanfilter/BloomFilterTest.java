package cn.com.kugou.test.booleanfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {

    public static void main(String[] args) {

        int expectedInsertions = 10000000;
        double fpp = 0.00001;

        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),  expectedInsertions, fpp);
        for (int i = 0; i <10000000 ; i++) {
            bloomFilter.put(i);
        }
        long star = System.currentTimeMillis();
        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));
        System.out.println(bloomFilter.mightContain(3));
        System.out.println(bloomFilter.mightContain(10000000));
        System.out.println(bloomFilter.mightContain(1));
        System.out.println("执行时间：" + (System.currentTimeMillis() - star));

    }
}
