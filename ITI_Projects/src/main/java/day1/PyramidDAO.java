/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdelrahman Mostafa
 */
public class PyramidDAO {
    
    //read csv method
    public List<Pyramid> readPyramidsFromCSV() throws FileNotFoundException, IOException{
        
        File pyramidInfo = new File("pyramids.csv");
        FileReader fr = new FileReader(pyramidInfo);
        BufferedReader br = new BufferedReader(fr);
        
        String strCurrentLine;
        br.readLine();//pass first row
        
        //array of pyramids => arraylist
        List<Pyramid> pyramidList = new ArrayList<>();
        
        while ((strCurrentLine = br.readLine()) != null){

            String[] pyramidData = strCurrentLine.split(",");
            
            pyramidList.add(createPyramid(pyramidData)); 
        }
        
        return pyramidList;
        
    }
    
    //create pyramid
    public Pyramid createPyramid(String[] pyramidData){
        
        double pyramidHeight = -1; //put -1 to height if empty cell
        
        try {
            //parsedouble height
            pyramidHeight = Double.parseDouble(pyramidData[7]);
        } catch (NumberFormatException e) {}
            
        //create person object
        Pyramid pObj = new Pyramid(pyramidData[0],pyramidData[2],pyramidData[4],pyramidHeight);
        return pObj;
    }
    
}
