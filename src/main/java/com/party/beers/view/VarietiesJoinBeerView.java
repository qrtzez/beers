package com.party.beers.view;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.party.beers.model.Color;

import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class VarietiesJoinBeerView {
    private int id;

    @Length(min = 1, max = 20)
    private String name;


    @Min(1)
    @Max(10000)
    private int price;

    @Min(1)
    @Max(20)
    private int strength;

    private Color color;

    @JsonBackReference
    private BeerVarietiesView beer;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BeerVarietiesView getBeer() {
        return beer;
    }

    public void setBeer(BeerVarietiesView beer) {
        this.beer = beer;
    }
}
