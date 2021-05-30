/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day1;

/**
 *
 * @author Abdelrahman Mostafa
 */
public class Pyramid {
    //pyramid attributes
    String pharaoh,modernName,site;
    double height;
    //pyramid constructor
    public Pyramid(String pharaoh, String modernName, String site, double height) {
	this.modernName = modernName;
	this.site = site;
	this.pharaoh = pharaoh;
	this.height = height;
    }
    //methods
    public double getHeight() {
	return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public String getModern_name() {
	return modernName;
    }
    public void setModern_name(String modernName) {
        this.modernName = modernName;
    }
    public String getPharaoh() {
	return pharaoh;
    }
    public void setPharaoh(String pharaoh) {
        this.pharaoh = pharaoh;
    }
    public String getSite() {
	return site;
    }
    public void setSite(String site) {
            this.site = site;
    }
    
    @Override
    public String toString() {
        return "Pyramid:" +"pharaoh='" + pharaoh + "'" +", modern_name='" + modernName + "'" + ", site='" + site + "'" +
                ", height=" + height + "\n";
    }
    
}
