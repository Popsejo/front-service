package kdg.microservices.client.model;

import java.io.Serializable;

public class School implements Serializable {

    private int id;
    private String name;
    private String city;

    public School() {
    }

    public School(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}