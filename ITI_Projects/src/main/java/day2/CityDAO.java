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
public class CityDAO {
    
    public List<City> readAllCites(String fileName) {
        File f = new File(fileName);
        List<City> cities = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(f.toPath());
            for (int i = 1; i < lines.size(); i++) {
                cities.add(createCity(lines.get(i)));
            }
            return cities;
        } catch (IOException e) {
            return null;
        }


    }

    private City createCity(String line) {
        String[] fields = line.split(",");
        return new City(Integer.parseInt(fields[0]), fields[1], fields[2], Double.parseDouble(fields[3]), Double.parseDouble(fields[4]));
    }
    
}
