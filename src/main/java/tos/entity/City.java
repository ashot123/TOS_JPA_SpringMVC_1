package tos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ashot Karakhanyan on 24-12-2013
 */

@Entity
@Table(name = "cities")
public class City extends AbstractEntity {

    @Column(name = "name")
    private String name;

    public City() {
    }

    /*public City(int id, String name) {
        this.name = name;
    }*/

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (!name.equals(city.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
