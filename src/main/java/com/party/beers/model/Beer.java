package com.party.beers.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beer")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;


    @OneToMany(mappedBy = "beer", orphanRemoval = true, cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private List<VarietiesBeer> varietiesBeerList;


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

    public List<VarietiesBeer> getVarietiesBeerList() {
        return varietiesBeerList;
    }

    public void setVarietiesBeerList(List<VarietiesBeer> varietiesBeerList) {
        this.varietiesBeerList = varietiesBeerList;
    }
}
