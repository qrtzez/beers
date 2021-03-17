package com.party.beers.controller;

import com.party.beers.service.BeerService;
import com.party.beers.view.BeerVarietiesView;
import com.party.beers.view.BeerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/sort_list")
    public List<BeerView> allSortBeer(){
        return beerService.allSortBeer();
    }

    @GetMapping("/all_sort_list")
    public List<BeerVarietiesView> allBeersJoinVarieties(){
        return beerService.allBeersJoinVarieties();
    }

    @PostMapping("/save")
    public BeerView saveBeer(@RequestBody BeerView beerView){
        beerService.saveBeer(beerView);
        return beerView;
    }
}
