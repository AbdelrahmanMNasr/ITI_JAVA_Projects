/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day3;

import java.io.IOException;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Table;

/**
 *
 * @author Abdelrahman Mostafa
 */
public class TableSawTitanicData {
    
    public static void main(String[] args) throws IOException {
        
        Table df = Table.read().csv("titanic.csv");
        System.out.println(df.structure());
        System.out.println(df.summary());
        //add new column
        IntColumn column = IntColumn.create("New Column");
        for (int i = 0; i < df.rowCount(); i++) {
            column.append(i);
        }
        df.addColumns(column);
        System.out.println(df.structure());
        System.out.println(df);
        
        //join
        Table newDataFrame1 = df.select("Pclass", "Survived", "Name");
        Table newDataFrame2 = df.select("Sex", "Age", "Name");
        Table joinedTable = newDataFrame1.joinOn("name").inner(newDataFrame2);
        System.out.println(joinedTable.structure());
        
        //map sex male & female to 0 & 1 then add this column
        int[] genders =  df.stream()
                    .mapToInt((row) -> {
                        String gender = row.getString("sex");
                        if (gender.equals("male"))
                            return 1;
                        return 0;
                    }).toArray();
        System.out.println(genders.length);
        //add this column
        IntColumn genderColumn=IntColumn.create("New Gender",genders);
        df.addColumns(genderColumn);
        System.out.println(df.structure());
        System.out.println(df);
    }
    
}
