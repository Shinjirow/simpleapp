package jp.ac.kyoto_su.cse.simpleapp;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import jp.cafebabe.pochi.BirthmarkSystemHelper;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.birthmarks.entities.Birthmarks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception {
        // write your code here

        SparkSession spark = SparkSession
                .builder()
                .appName("PochiSpark")
                .getOrCreate();

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());


        List<File> list = new ArrayList<>();
        File directory = new File("/path/to/jars");
        list = Arrays.asList(Objects.requireNonNull(directory.listFiles()));

        JavaRDD<File> dataSet = jsc.parallelize(list);

        // BirthmarkSystemHelper bsh = new BirthmarkSystemHelper();


        Birthmarks ret = dataSet.map(file -> {
            BirthmarkSystemHelper bsh = new BirthmarkSystemHelper();
            DataSource source = bsh.source(file.getAbsolutePath());
            Extractor extractor = bsh.extractor("uc");
            return extractor.extract(source);
        }).reduce(Birthmarks::merge);

        System.out.println(ret.toString());

        spark.stop();

    }
}

