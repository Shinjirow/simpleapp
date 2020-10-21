package jp.ac.kyoto_su.cse.simpleapp;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import jp.cafebabe.pochi.BirthmarkSystemHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // write your code here

        SparkSession spark = SparkSession
                .builder()
                .appName("PochiSpark")
                .getOrCreate();

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        BirthmarkSystemHelper bsh = new BirthmarkSystemHelper();

        // int slices = (args.length == 1) ? Integer.parseInt(args[0]) : 2;
        int n = 1;
        List<Integer> l = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            l.add(i);
        }

        JavaRDD<Integer> dataSet = jsc.parallelize(l);

        List<String> ret = dataSet.map(integer -> {
            return Arrays.asList(bsh.extractorNames());
        }).reduce((str1, str2) -> {
            str1.addAll(str2);
            return str1;
        });

        ret.forEach(System.out::println);
        // System.out.println("Pi is roughly " + 4.0 * count / n);

        spark.stop();

    }
}

