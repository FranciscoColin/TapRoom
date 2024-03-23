package com.desarrollosPatito.taproom.Catalogo;
import com.google.gson.annotations.SerializedName;
public class ObjCerveza {
    @SerializedName("id")
    private String id;
    @SerializedName("beerName")
    private String beerName;

    @SerializedName("beerStyle")
    private String beerStyle;

    @SerializedName("abv")
    private double abv;

    @SerializedName("ibu")
    private int ibu;

    @SerializedName("flavorDescription")
    private String flavorDescription;

    @SerializedName("brewery")
    private String brewery;

    @SerializedName("servingSize")
    private String servingSize;

    @SerializedName("price")
    private double price;

    @SerializedName("origin")
    private String origin;

    public String getBeerName() {
        return beerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public String getFlavorDescription() {
        return flavorDescription;
    }

    public void setFlavorDescription(String flavorDescription) {
        this.flavorDescription = flavorDescription;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
