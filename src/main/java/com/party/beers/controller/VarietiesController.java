package com.party.beers.controller;

import com.party.beers.service.VarietiesService;
import com.party.beers.view.VarietiesBeerView;
import com.party.beers.view.VarietiesSaveView;
import com.party.beers.view.VarietiesUpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/varieties")
public class VarietiesController {
    private final VarietiesService varietiesService;

    public VarietiesController(VarietiesService varietiesService) {
        this.varietiesService = varietiesService;
    }

    @GetMapping("/find_id/{id}")
    public VarietiesBeerView findSortBeerById(@PathVariable("id") int id){
        return varietiesService.findSortBeerById(id);
    }

    @GetMapping("/all_beer")
    public List<VarietiesBeerView> allSortBeerList(){
        return varietiesService.allSortBeerList();
    }

    @PostMapping("/save")
    public VarietiesSaveView  saveSortBeer(@RequestBody VarietiesSaveView varietiesBeerView, @RequestParam int beerId){
        varietiesService.saveSortBeer(varietiesBeerView, beerId);
        return varietiesBeerView;
    }

    @PostMapping("/delete/{name}")
    public void deleteSortBeer(@PathVariable("name") String name){
        varietiesService.deleteSortBeer(name);
    }

    @PostMapping("/update")
    public VarietiesUpdateView updateSortBeer(@RequestBody VarietiesUpdateView varietiesUpdateView){
        varietiesService.updateSortBeer(varietiesUpdateView);
        return varietiesUpdateView;
    }
}
