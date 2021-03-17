package com.party.beers.view;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.party.beers.model.VarietiesBeer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeerVarietiesView {
    private int id;

    private String name;

    @JsonManagedReference
    private List<VarietiesJoinBeerView> varietiesBeerList;

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

    public List<VarietiesJoinBeerView> getVarietiesBeerList() {
        return varietiesBeerList;
    }

    public void setVarietiesBeerList(List<VarietiesJoinBeerView> varietiesBeerList) {
        this.varietiesBeerList = varietiesBeerList;
    }
}
