package com.party.beers.service;

import com.party.beers.exception.IncorrectDataException;
import com.party.beers.model.Beer;
import com.party.beers.model.VarietiesBeer;
import com.party.beers.repository.BeerRepository;
import com.party.beers.view.BeerVarietiesView;
import com.party.beers.view.BeerView;
import com.party.beers.view.VarietiesJoinBeerView;
import ma.glasnost.orika.MapperFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerService {
    private final BeerRepository beerRepository;

    private final MapperFactory mapperFactory;
    


    public BeerService(BeerRepository beerRepository, MapperFactory mapperFactory) {
        this.beerRepository = beerRepository;
        this.mapperFactory = mapperFactory;
    }

    @Transactional
    public List<BeerView> allSortBeer(){
        List<Beer> beerList = beerRepository.findAll(Sort.by("name").ascending());
        return beerList.stream()
                .map(mapperFactory.getMapperFacade(Beer.class, BeerView.class)::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BeerVarietiesView> allBeersJoinVarieties() {
        List<Beer> beerList = beerRepository.findAll(Sort.by("name").ascending());
        List<VarietiesBeer> varietiesBeers = new ArrayList<>();
        List<BeerVarietiesView> beerVarietiesViewList = new ArrayList<>();
        for(Beer beer : beerList){
            varietiesBeers.addAll(beer.getVarietiesBeerList());
            BeerVarietiesView beerVarietiesView = mapperFactory.getMapperFacade(Beer.class, BeerVarietiesView.class)
                    .map(beer);
            List<VarietiesJoinBeerView> varietiesJoinBeerViews = varietiesBeers.stream()
                    .map(mapperFactory.getMapperFacade(VarietiesBeer.class, VarietiesJoinBeerView.class)::map)
                    .collect(Collectors.toList());
            beerVarietiesView.setVarietiesBeerList(varietiesJoinBeerViews);
            beerVarietiesViewList.add(beerVarietiesView);
            varietiesBeers.clear();
        }
        return beerVarietiesViewList;
    }

    @Transactional
    public void saveBeer(BeerView beerView){
        Beer beer = mapperFactory.getMapperFacade(BeerView.class, Beer.class).map(beerView);
        try {
            beerRepository.save(beer);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }

}
