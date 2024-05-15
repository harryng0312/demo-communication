package org.harryng.demo.asset.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

//@SpringBootTest(classes = Application.class)
//@Import(Application.class)
@Slf4j
public class TestNumber {
    @Test
    public void test() {
        final int size = 200; // 738 - 740 is threshold of rounding
        final Random random = new Random();
        final double[] array1 = random.doubles(size, 0.0, 1.0).toArray();
        final double[] array2 = random.doubles(size, 1.0, 5.0).toArray();
        final double[] array3 = new double[size + size];
        System.arraycopy(array1, 0, array3, 0, size);
        System.arraycopy(array2, 0, array3, size, size);
//        final double[] array = random.doubles(size, 0.0, 1.7).toArray(); //Arrays.stream(array3)..toArray();
//        log.info("array[{}]:{}", array.length, array);
        final double[] array = array3;
        final long countLessThan1 = Arrays.stream(array).filter(value -> value < 1.0).count();
        log.info("countLessThan1: {}", countLessThan1);
        final double product1 = Arrays.stream(array)
//                .boxed()
//                .sorted((o1, o2) -> Double.compare(o2, o1))
//                .mapToDouble(Double::doubleValue)
                .reduce(1.0, (left, right) -> left * right);
        final double expProduct2 = Arrays.stream(array)
                .map(Math::log)
                .reduce(0.0, Double::sum);
        final double expProduct3 = Arrays.stream(array)
                .map(Math::log10)
                .reduce(0.0, Double::sum);
        final double product2 = Math.pow(Math.E, expProduct2);
        final double product3 = Math.pow(10.0, expProduct3);
        log.info("----product1:{}", product1);
        log.info("E - product2:{} exp:{}", product2, expProduct2);
        log.info("10- product3:{} exp:{}", product3, expProduct3);
    }
}
