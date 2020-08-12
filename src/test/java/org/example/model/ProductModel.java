package org.example.model;

/**
 * @author Julia Marushkina
 */
public class ProductModel {

    private final String categoryName = "Portativie datori";

    private final String brand = "Lenovo";

    private final String mostStarsFilterName = "TOP prece";

    private String name;

    private String price;

    public String getCategoryName() {
        return categoryName;
    }

    public String getBrand() {
        return brand;
    }

    public String getMostStarsFilterName() {
        return mostStarsFilterName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
