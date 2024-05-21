package org.harryng.demo.asset.service;

import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.util.TextUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

//@SpringBootTest(classes = Application.class)
//@Import(Application.class)
@Slf4j
public class TestNumber {
    @Test
    public void test() throws IOException {
        final int size = 1200; // 738 - 740 is threshold of rounding
        final int size2= 600;
        final Random random = new Random();
        final double[] array1 = random.doubles(size, 0.00001, 1.0).toArray();
        final double[] array2 = random.doubles(size2, 1.0, 5.0).toArray();
        final double[] array3 = new double[size + size2];
        System.arraycopy(array2, 0, array3, 0, size2/2);
        System.arraycopy(array1, 0, array3, size2/2, size);
        System.arraycopy(array2, size2/2, array3, size+size2/2, size2/2);
//        final double[] array = random.doubles(size, 0.0, 1.87).toArray(); //Arrays.stream(array3)..toArray();
//        final double[] array = array3;
        final String decimalStr = Files.readString(Paths.get("../decimal_1800.json").toAbsolutePath(), StandardCharsets.UTF_8);
        final Double[] arrayD = TextUtil.jsonToObj(Double[].class, decimalStr);
        final double[] array = Arrays.stream(arrayD).mapToDouble(Double::doubleValue).toArray();

//        log.info("array[{}]:{}", array.length, array);
        final long countLessThan1 = Arrays.stream(array).filter(value -> value < 1.0).count();
        log.info("countLessThan1: {}", countLessThan1);
        final double productO = Arrays.stream(array)
//                .boxed()
//                .sorted((o1, o2) -> Double.compare(o2, o1))
//                .mapToDouble(Double::doubleValue)
                .reduce(1.0, (left, right) -> left * right);
        final double productS = Arrays.stream(array)
                .boxed()
                .sorted((o1, o2) -> Double.compare(o2, o1))
                .mapToDouble(Double::doubleValue)
                .reduce(1.0, (left, right) -> left * right);
        final BigDecimal productBD = Arrays.stream(array)
                .mapToObj(BigDecimal::valueOf)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
        final double expProductE = Arrays.stream(array)
                .map(Math::log)
                .reduce(0.0, Double::sum);
        final double expProduct10 = Arrays.stream(array)
                .map(Math::log10)
                .reduce(0.0, Double::sum);
        final double product2 = Math.pow(Math.E, expProductE);
        final double product3 = Math.pow(10.0, expProduct10);
        log.info("--- productO : {}", productO);
        log.info("--- productS : {}", productS);
        log.info("--- productBD: {}", productBD.doubleValue());
        log.info("--- productE : {} exp:{}", product2, expProductE);
        log.info("--- product10: {} exp:{}", product3, expProduct10);
    }
}
