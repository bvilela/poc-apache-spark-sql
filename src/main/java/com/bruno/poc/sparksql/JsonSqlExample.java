package com.bruno.poc.sparksql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JsonSqlExample {

	public void runSQLQueryInJsonFile() {
		
		SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data source JSON example")
                .master("local[2]")
                .config("spark.app.name", "POC SparkSQLApplicationName")
                .getOrCreate();
  
        // A JSON dataset is pointed to by path.
        // The path can be either a single text file or a directory storing text files
        Dataset<Row> people = spark.read().json("data/people.json");
  
        // The inferred schema can be visualized using the printSchema() method
        System.out.println("Schema\n=======================");
        people.printSchema();
  
        // Creates a temporary view using the DataFrame
        people.createOrReplaceTempView("people");
  
        // SQL statements can be run by using the sql methods provided by spark
        Dataset<Row> names1 = spark.sql("SELECT name FROM people WHERE salary > 3500 ");
        System.out.println("\n\nSQL Result 1 \n=======================");
        names1.show();
        
        Dataset<Row> names2 = spark.sql("SELECT name FROM people WHERE salary > 3500 and salary < 4500 ");
        System.out.println("\n\nSQL Result 2 \n=======================");
        names2.show();
        
        Dataset<Row> names3 = spark.sql("SELECT name FROM people WHERE salary = 4500");
        System.out.println("\n\nSQL Result 3 \n=======================");
        names3.show();
  
        // stop spark session
        spark.stop();
	}
	
}
