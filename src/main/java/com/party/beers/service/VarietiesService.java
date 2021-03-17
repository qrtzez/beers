package com.party.beers.service;

import com.party.beers.exception.IncorrectDataException;
import com.party.beers.exception.NotFoundException;
import com.party.beers.model.Beer;
import com.party.beers.model.VarietiesBeer;
import com.party.beers.repository.BeerRepository;
import com.party.beers.repository.VarietiesRepository;
import com.party.beers.view.VarietiesBeerView;
import com.party.beers.view.VarietiesSaveView;
import com.party.beers.view.VarietiesUpdateView;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VarietiesService {
    private final VarietiesRepository varietiesRepository;

    private final MapperFactory mapperFactory;

    private final BeerRepository beerRepository;

    public VarietiesService(MapperFactory mapperFactory, VarietiesRepository varietiesRepository, BeerRepository beerRepository) {
        this.mapperFactory = mapperFactory;
        this.varietiesRepository = varietiesRepository;
        this.beerRepository = beerRepository;
    }

    @Transactional
    public VarietiesBeerView findSortBeerById(int id) {
        VarietiesBeer varietiesBeer = varietiesRepository.findById(id).orElse(null);
        if(varietiesBeer == null){
            throw new NotFoundException();
        }
        return mapperFactory.getMapperFacade(VarietiesBeer.class, VarietiesBeerView.class).map(varietiesBeer);
    }

    @Transactional
    public List<VarietiesBeerView> allSortBeerList() {
        List<VarietiesBeer> varietiesBeers = varietiesRepository.findAll(Sort.by("beer_id", "name").ascending());
        return varietiesBeers.stream()
                .map(mapperFactory.getMapperFacade(VarietiesBeer.class, VarietiesBeerView.class)::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveSortBeer(VarietiesSaveView varietiesBeerView, int beerId) {
        Beer beer = beerRepository.getOne(beerId);
        VarietiesBeer varietiesBeer = mapperFactory.getMapperFacade(VarietiesSaveView.class, VarietiesBeer.class)
                .map(varietiesBeerView);
        varietiesBeer.setBeer(beer);
        try {
            varietiesRepository.save(varietiesBeer);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }

    @Transactional
    public void deleteSortBeer(String name){
        VarietiesBeer varietiesBeer = varietiesRepository.findByName(name);
        try {
            varietiesRepository.delete(varietiesBeer);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }

    @Transactional
    public void updateSortBeer(VarietiesUpdateView varietiesUpdateView){
        VarietiesBeer varietiesBeer1 = varietiesRepository.getOne(varietiesUpdateView.getId());
        VarietiesBeer varietiesBeer = mapperFactory.getMapperFacade().map(varietiesUpdateView, VarietiesBeer.class);
        varietiesBeer.setBeer(varietiesBeer1.getBeer());
        try {
            varietiesRepository.save(varietiesBeer);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }
}
