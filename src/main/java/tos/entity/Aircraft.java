package tos.entity;

import javax.persistence.*;

/**
 * Created by Ashot Karakhanyan on 24-12-2013
 */

@Entity
@Table(name = "aircrafts")
public class Aircraft extends AbstractEntity {

    @Column(name = "model")
    private String model;

    @Column(name = "class1_count")
    private int class1Count;

    @Column(name = "class2_count")
    private int class2Count;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public Aircraft() {
    }

    public Aircraft(String model, int class1Count, int class2Count) {
        this.model = model;
        this.class1Count = class1Count;
        this.class2Count = class2Count;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getClass1Count() {
        return class1Count;
    }

    public void setClass1Count(int class1Count) {
        this.class1Count = class1Count;
    }

    public int getClass2Count() {
        return class2Count;
    }

    public void setClass2Count(int class2Count) {
        this.class2Count = class2Count;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "model='" + model + '\'' +
                ", class1Count=" + class1Count +
                ", class2Count=" + class2Count +
                ", company=" + company +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft)) return false;

        Aircraft aircraft = (Aircraft) o;

        if (class1Count != aircraft.class1Count) return false;
        if (class2Count != aircraft.class2Count) return false;
        if (!company.equals(aircraft.company)) return false;
        if (!model.equals(aircraft.model)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + class1Count;
        result = 31 * result + class2Count;
        result = 31 * result + company.hashCode();
        return result;
    }
}
