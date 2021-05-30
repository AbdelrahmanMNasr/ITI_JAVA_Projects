/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdelrahman Mostafa
 */
public class CountryDAO {
    
        public List<Country> readAllCountries(String fileName) {
        File f = new File(fileName);
        List<Country> countries = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(f.toPath());
            for (int i = 1; i < lines.size(); i++) {
                countries.add(createCountry(lines.get(i)));
            }
            return countries;
        } catch (IOException e) {
            return null;
        }
        
    }

    private Country createCountry(String line) {
        String[] fields = line.split(",");
        return new Country(Integer.parseInt(fields[0]), fields[1]);
    }
    
}
