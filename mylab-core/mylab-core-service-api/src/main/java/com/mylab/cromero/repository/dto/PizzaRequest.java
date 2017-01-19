package com.mylab.cromero.repository.dto;

/**
 * <h1>BaseRequest</h1>
 * BaseRequest dto request
 * <p>
 * <b>PizzaRequest</b> BaseRequest data transfer object
 * for sevice layer
 *
 * @author Cristian Romero Matesanz
 */
public class PizzaRequest {

    private String name;

    private Float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String builder = "PizzaRequest [name=" +
                name +
                ", price=" +
                price +
                "]";
        return builder;
    }


}
