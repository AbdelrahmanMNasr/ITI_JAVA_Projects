/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdelrahman Mostafa
 */
public class Main {
        /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("output");
        PyramidDAO pyramidDOAObj = new PyramidDAO();
        List<Pyramid> pyramids = pyramidDOAObj.readPyramidsFromCSV();
        
        //print all pyramids in the csv
        int i = 0;
        for(Pyramid p:pyramids){
            System.out.println("#"+(i++)+" "+p);
        }
        
    }
    
}
