/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import joinery.DataFrame;

/**
 *
 * @author Abdelrahman Mostafa
 */
public class JoineryTitanicData {
    
    public static void main(String[] args) throws IOException {
            //lab with joinery
            DataFrame<Object> df = DataFrame.readCsv("titanic.csv");
            //describe
            System.out.println(df.describe());
            //print first 10 records
            System.out.println(df.head(10));
            //print first column Name
            System.out.println(df.col("Name"));
            //print first value in Name
            System.out.println(df.col("Name").get(0));
            //print max age
            //df.retain("Age").max().iterrows().forEachRemaining (System.out::println);
            df.retain("Age").max().iterator().forEachRemaining(System.out::print);
            df.retain("Age").mean().iterator().forEachRemaining(System.out::print);
            
            //select from dataframe
            DataFrame<Object> selectedData= df.select(rows->{
                return (rows.get(0).equals(Long.parseLong("1")));//select * where survived = 1
            });
            System.out.println("======== All survived =============");
            System.out.println(selectedData.describe());
            System.out.println(selectedData.head(10));
            System.out.println("========");
            
            DataFrame<Object> df1 = df.retain("Name", "Age" , "Survived");
            DataFrame<Object> df2 = df.retain("Name", "Sex", "Pclass");
            //join
            DataFrame<Object> joinedDf = df1.joinOn(df2, DataFrame.JoinType.INNER, "Name");
            System.out.println(joinedDf.describe());
            System.out.println(joinedDf.head(10));
            
            //Add column of zeros
            List<Integer> listOfValues = new ArrayList<>();
            for (int i = 0; i < df.length(); i++){
                listOfValues.add(0);
            }
            df.add("newcolumn", listOfValues);
            System.out.println(df.describe());
            System.out.println(df.head(10));
  
    }
    
    
}
