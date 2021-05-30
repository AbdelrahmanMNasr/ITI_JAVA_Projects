/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 *
 * @author Abdelrahman Mostafa
 */
public class Main {
    
    public static void main(String[] args) {
        
        //list of countries
        CountryDAO countryDAO = new CountryDAO();
        List<Country> countries = countryDAO.readAllCountries("countries.csv");
        System.out.println("=== list of countries ===");
        System.out.println(countries);
        
        //list of cities
        CityDAO cityDAO = new CityDAO();
        List<City> cities = cityDAO.readAllCites("cities.csv");
        System.out.println("=== list of cities ===");
        System.out.println(cities);
        
        //map of country=>cities
        Map<Integer, List<City>> countryCities = new HashMap<>();

        for (City c : cities) {
            if (!countryCities.containsKey(c.code)) {
                List<City> city = new ArrayList<>();
                city.add(c);
                countryCities.put(c.code, city);
            } else {
                countryCities.get(c.code).add(c);
            }
        }
        System.out.println("=== country=>cities Map ===");
        countryCities.forEach((i, c) -> {
            System.out.println("key = " + i + " cities= " + c);
        });
      
        
        //sort cities in each country according to population
        Iterator<Integer> it = countryCities.keySet().iterator();
        it.forEachRemaining(key -> {
            countryCities.get(key).sort((o1, o2) -> {
                if (o1.population < o2.population)
                    return 1;
                else if (o1.population == o2.population)
                    return 0;
                return -1;
            });
        });
        System.out.println("=== sorted Map ===");
        countryCities.forEach((i, c) -> {
            System.out.println("key = " + i + " cities= " + c);
        });
        
        //Better String
        String checkBetter = Main.betterString("longer String", "short Str", (str1,str2)->str1.length()>str2.length());
        System.out.println(checkBetter);
        
        //checkString if conatains only alphapet
        System.out.println(Main.checkAlphapet("A1hmed123"));
        
        //highest population city
        List<Optional<Double>> highestPopulationCity =cities.stream()
                .collect(Collectors.groupingBy(City::getCode))
                .values()
                .stream()
                .map(cityList1 -> cityList1.stream()//city
                        .map(City::getPopulation)
                        .max(Double::compare)
                ).collect(Collectors.toList());
        System.out.println(highestPopulationCity);
        
        //highest population city
        List<Optional<Double>> highestPopulationCityContinent =cities.stream()
                .collect(Collectors.groupingBy(City::getContinent))
                .values()
                .stream()
                .map(cityList1 -> cityList1.stream()//city
                        .map(City::getPopulation)
                        .max(Double::compare)
                ).collect(Collectors.toList());
        System.out.println(highestPopulationCityContinent);
        
        //get Median & Lower quartile & upper quartile
        //get sorted array of population
        double[] sortedPopulationArray = cities.stream()
                .mapToDouble(City::getPopulation)
                .sorted()
                .toArray();
       
        System.out.println(sortedPopulationArray.length);
        System.out.println(Arrays.toString(sortedPopulationArray));
        
        //get median
        System.out.println("Median is: " + getMedian(sortedPopulationArray));
        
        //get lower quartile
        System.out.println("Lower Quartile is: " + getLowerquartile(sortedPopulationArray));
        
        //get upper quartile
        System.out.println("Upper Quartile is: " + getUpperquartile(sortedPopulationArray));
        
        //get Average of population values
        System.out.println("Average is: " + DoubleStream.of(sortedPopulationArray)
                .reduce(0,(x,y)->x+y) / sortedPopulationArray.length);
         
    }
    
    static String betterString(String str1, String str2, BiPredicate<String, String> biPredicate) {
        if (biPredicate.test(str1, str2))
            return str1;
        return str2;
    }
    
    public static boolean checkAlphapet(String str){
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static double getMedian(double[] sortedArray) {
        int middleIndx = sortedArray.length / 2;
        if (sortedArray.length % 2 == 0) {
            return (sortedArray[middleIndx] + sortedArray[middleIndx - 1]) / 2;
        } else
            return sortedArray[sortedArray.length / 2];
    }
    
    public static double getLowerquartile(double[] sortedArray){
        int middleIndx = (int) Math.ceil(sortedArray.length / 2);
        double[] LowerArray = Arrays.copyOfRange(sortedArray, 0, middleIndx);
        System.out.println(Arrays.toString(LowerArray));
        return getMedian(LowerArray);
    }
    
    public static double getUpperquartile(double[] sortedArray){
        int middleIndx = (int) Math.ceil((double) sortedArray.length / 2);
        System.out.println(middleIndx);
        int arrayLength = sortedArray.length;
        double[] upperArray = Arrays.copyOfRange(sortedArray, middleIndx , arrayLength);
        System.out.println(Arrays.toString(upperArray));
        return getMedian(upperArray);
    }
    
}