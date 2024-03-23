package com.desarrollosPatito.taproom.Catalogo.Edita;

public class ObjCerveza {
    private String id;
    private String beerName;
    private String beerStyle;
    private String abv;
    private String ibu;
    private String flavorDescription;
    private String brewery;
    private String servingSize;
    private String price;
    private String origin;
    private String status;

    public ObjCerveza(String id, String beerName, String beerStyle, String abv, String ibu, String flavorDescription, String brewery, String servingSize, String price, String origin, String status) {
        this.id = id;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.abv = abv;
        this.ibu = ibu;
        this.flavorDescription = flavorDescription;
        this.brewery = brewery;
        this.servingSize = servingSize;
        this.price = price;
        this.origin = origin;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeerName() {
        return beerName;
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

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
