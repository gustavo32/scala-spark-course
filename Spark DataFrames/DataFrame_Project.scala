// DATAFRAME PROJECT
// Use the Netflix_2011_2016.csv file to Answer and complete
// the commented tasks below!
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

// Start a simple Spark Session
val spark = SparkSession.builder().appName("SimpleProject").getOrCreate()

// Load the Netflix Stock CSV File, have Spark infer the data types.
val schema = StructType(Array(
  StructField("Date", DateType, true),
  StructField("Open", DoubleType, true),
  StructField("High", DoubleType, true),
  StructField("Low", DoubleType, true),
  StructField("Close", DoubleType, true),
  StructField("Volume", LongType, true)
))

val df = spark.read.option("header", true).schema(schema).csv("CitiGroup2006_2008")

// What are the column names?
df.columns

// What does the Schema look like?
df.printSchema()

// Print out the first 5 rows.
df.head(5)

// Use describe() to learn about the DataFrame.
df.describe()

// Create a new dataframe with a column called HV Ratio that
// is the ratio of the High Price versus volume of stock traded
// for a day.
val transformed_df = df.withColumn("HV Ratio", $"High"/$"Volume")

// What day had the Peak High in Price?
transformed_df.select(max($"Date")).show()

// What is the mean of the Close column?
transformed_df.select(mean("Close")).show()

// What is the max and min of the Volume column?
transformed_df.select(max($"Volume"), min($"Volume")).show()

// For Scala/Spark $ Syntax

// How many days was the Close lower than $ 600?
transformed_df.filter($"Close" < 600).count()

// What percentage of the time was the High greater than $500 ?
(transformed_df.filter($"High" > 500).count().toDouble / transformed_df.count()) * 100

// What is the Pearson correlation between High and Volume?
transformed_df.select(corr("High", "Volume")).show()

// What is the max High per year?
val transformed_df_with_year = transformed_df.withColumn("Year", year($"Date"))
transformed_df_with_year.groupBy("Year").max("High").orderBy("Year").show()

// What is the average Close for each Calender Month?
val transformed_df_with_month = transformed_df.withColumn("Month", month($"Date"))
transformed_df_with_month.groupBy("Month").mean("Close").orderBy("Month").show()