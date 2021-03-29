////////////////////////////////////////////
//// LINEAR REGRESSION EXERCISE ///////////
/// Complete the commented tasks below ///
/////////////////////////////////////////

// Import LinearRegression

// Optional: Use the following code below to set the Error reporting
import org.apache.log4j._
import org.apache.spark.sql.SparkSession

Logger.getLogger("org").setLevel(Level.ERROR)


// Start a simple Spark Session
val spark = SparkSession.builder().getOrCreate()
// Use Spark to read in the Ecommerce_Customers csv file.
val data = spark.read.option("inferSchema", true).option("header", true).csv("C:\\Users\\LuisGustavodeSouza\\Documents\\CURSOS\\SCALA_SPARK\\Machine_Learning_Sections\\Regression\\Clean_Ecommerce.csv")

// Print the Schema of the DataFrame
data.printSchema()

// Print out an example Row
// Various ways to do this, just
// choose whichever way you prefer
data.show(10)

////////////////////////////////////////////////////
//// Setting Up DataFrame for Machine Learning ////
//////////////////////////////////////////////////

// A few things we need to do before Spark can accept the data!
// It needs to be in the form of two columns
// ("label","features")

// Import VectorAssembler and Vectors
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.regression.LinearRegression


// Rename the Yearly Amount Spent Column as "label"
// Also grab only the numerical columns from the data
// Set all of this as a new dataframe called df
val df = data.drop("Email").drop("Avatar").withColumnRenamed("Yearly Amount Spent", "label")

// An assembler converts the input values to a vector
// A vector is what the ML algorithm reads to train a model

// Use VectorAssembler to convert the input columns of df
// to a single output column of an array called "features"
// Set the input columns from which we are supposed to read the values.
// Call this new object assembler
val assembler = new VectorAssembler().setInputCols(df.drop("label").columns).setOutputCol("features")

// Use the assembler to transform our DataFrame to the two columns: label and features
val transformed_df = assembler.transform(df).select($"label", $"features")

// Create a Linear Regression Model object
val lr = new LinearRegression()

// Fit the model to the data and call this model lrModel
val lrModel = lr.fit(transformed_df)

// Print the coefficients and intercept for linear regression
println(s"coefficients: ${lrModel.coefficients}")
println(s"intercept: ${lrModel.intercept}")


// Summarize the model over the training set and print out some metrics!
// Use the .summary method off your model to create an object
// called trainingSummary
val trainingSummary = lrModel.summary

// Show the residuals, the RMSE, the MSE, and the R^2 Values.
trainingSummary.residuals.show()
println(s"rmse: ${trainingSummary.rootMeanSquaredError}")
println(s"mse: ${trainingSummary.meanSquaredError}")
println(s"r2: ${trainingSummary.r2}")
// Great Job!
