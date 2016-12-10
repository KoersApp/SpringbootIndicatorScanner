package com.indicatorscanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indicatorscanner.domain.CandlePattern;
import com.indicatorscanner.repository.CandlePatternRepository;

@Service
public class CandlePatternServiceImpl implements CandlePatternService {
    private CandlePatternRepository candlePatternRepository;

    @Autowired
    public void setCandlePatternRepository(
            CandlePatternRepository candlePatternRepository) {
        this.candlePatternRepository = candlePatternRepository;
    }

    @Override
    public Iterable<CandlePattern> listAllProducts() {
        return candlePatternRepository.findAll();
    }

    @Override
    public void deleteCandlePattern(String id) {
        candlePatternRepository.delete(id);
    }

    @Override
    public CandlePattern saveCandlePattern(CandlePattern index) {
        candlePatternRepository.save(index);
        return index;
    }

    @Override
    public CandlePattern saveOrUpdateCandlePattern(CandlePattern index) {
        if (index == null) {
            return null;
        }
        boolean foundInDB = false;
        for (CandlePattern candlePattern : candlePatternRepository
                .findByPatternName(index.getPatternID())) {
            if (index.getPatternID().equals(candlePattern.getPatternID())) {
                index = candlePattern;
                foundInDB = true;
                break;
            }
        }
        if (!foundInDB) {
            candlePatternRepository.save(index);
        }

        return index;
    }

}
