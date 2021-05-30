/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day5;

import static day5.SmileExample.encodeColumn;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
import smile.io.Read;
import smile.plot.swing.BarPlot;
import smile.plot.swing.Histogram;

/**
 *
 * @author Abdelrahman Mostafa
 */
public class smileLibTest {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, InvocationTargetException {
        //read train data
        DataFrame titanic = Read.csv("titanic_train.csv", CSVFormat.DEFAULT.withFirstRecordAsHeader());
        System.out.println(titanic.schema());
        System.out.println(titanic.summary());
        titanic = titanic.select("PassengerId", "Pclass", "Age", "SibSp", "Name", "Parch", "Sex", "Survived");
        //encode columns
        titanic = titanic.merge(IntVector.of("Gender", encodeColumn(titanic, "Sex")));
        titanic = titanic.merge(IntVector.of("PclassValues", encodeColumn(titanic, "Pclass")));
        System.out.println("===After Encoding===");
        System.out.println(titanic.schema());
        System.out.println(titanic.summary());
         
        titanic = titanic.drop("Name");
        titanic = titanic.drop("Sex");
        titanic = titanic.drop("Pclass");
        titanic=titanic.omitNullRows();
        
        //EDA
        eda(titanic);
        System.out.println(titanic.schema());
        System.out.println(titanic.summary());
    
        //random forest model
        RandomForest randomForestModel = RandomForest.fit(Formula.lhs("Survived"),titanic);
        System.out.println("feature importance:");
        System.out.println(Arrays.toString(randomForestModel.importance()));
        System.out.println(randomForestModel.metrics ());
        
        //Test Data
        //read train data
        DataFrame titanic_test=Read.csv("titanic_test.csv",CSVFormat.DEFAULT.withFirstRecordAsHeader());
        System.out.println(titanic_test.summary());
        titanic_test=titanic_test.select("PassengerId", "Pclass", "Age", "SibSp", "Name", "Parch", "Sex");
        titanic_test = titanic_test.merge(IntVector.of("Gender", encodeColumn(titanic_test, "Sex")));
        
        titanic_test = titanic_test.drop("Sex");
        titanic_test = titanic_test.drop("Name");
        titanic_test=titanic_test.omitNullRows();
        System.out.println(titanic_test.summary());
        //predict
        System.out.println(Arrays.toString(randomForestModel.predict(titanic_test)));
          
    }
    
    private static int[] encodeColumn(DataFrame df, String columnName) {
        String[] values = df.stringVector(columnName).distinct().toArray(new String[] {});
        int[] pclassValues = df.stringVector(columnName).factorize(new NominalScale(values)).toIntArray();
        return pclassValues;
    }

    private static void eda(DataFrame titanic) throws InterruptedException, InvocationTargetException {
        DataFrame titanicSurvived = DataFrame.of(titanic.stream().filter(t -> t.get("Survived").equals(1)));
        DataFrame titanicNotSurvived = DataFrame.of(titanic.stream().filter(t -> t.get("Survived").equals(0)));
        titanicNotSurvived.summary();
        titanicSurvived.summary();
        int size = titanicSurvived.size();
        System.out.println(size);
        Double averageAge = titanicSurvived.stream()
                .mapToDouble(t -> t.isNullAt("Age" ) ? 0.0 : t.getDouble("Age"))
                .average()
                .orElse(0);
        System.out.println("Average age of survived: " + averageAge.intValue());
        
        //Histogram for Pclass values
        Histogram.of(titanicSurvived.intVector("PclassValues").toIntArray(),4, true)
                .canvas().setAxisLabels("Classes","Count")
                .setTitle("Pclass values frequencies among surviving passengers" )
                .window();
        //Histogram for Age values
        Histogram.of(titanicSurvived.doubleVector("Age").toDoubleArray())
                    .canvas().setAxisLabel(0,"Age").setAxisLabel(1,"Count")
                    .setTitle("Age values frequencies among surviving passengers")
                    .window();


    }
}
