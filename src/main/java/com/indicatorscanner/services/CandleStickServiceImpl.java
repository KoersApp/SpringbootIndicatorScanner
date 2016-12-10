package com.indicatorscanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indicatorscanner.domain.CandleStick;
import com.indicatorscanner.domain.Indices;
import com.indicatorscanner.repository.CandleStickRepository;

@Service
public class CandleStickServiceImpl implements CandleStickService {
    private CandleStickRepository candleStickRepository;

    @Autowired
    public void setProductRepository(
            CandleStickRepository candleStickRepository) {
        this.candleStickRepository = candleStickRepository;
    }

    @Override
    public Iterable<CandleStick> listAllProducts() {
        return candleStickRepository.findAll();
    }

    @Override
    public void deleteCandleStick(Long id) {
        candleStickRepository.delete(id);
    }

    @Override
    public CandleStick saveCandleStick(CandleStick candleStick) {
        candleStickRepository.save(candleStick);
        return candleStick;
    }

    @Override
    public CandleStick saveOrUpdateCandleStick(CandleStick candleStick) {
        candleStickRepository.save(candleStick);
        return candleStick;
    }

    @Override
    public CandleStick getCandleStickByIndex(Indices index) {
        CandleStick candleStick = candleStickRepository.findByIndex(index);
        return candleStick;
    }

}
