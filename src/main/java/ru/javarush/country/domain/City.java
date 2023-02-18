package ru.javarush.country.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(schema = "world", name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 35)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "district", length = 20)
    private String district;

    @Column(name = "population")
    private int population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return population == city.population
                && Objects.equals(id, city.id)
                && Objects.equals(name, city.name)
                && Objects.equals(country, city.country)
                && Objects.equals(district, city.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                country,
                district,
                population);
    }
}
