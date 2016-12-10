package com.indicatorscanner.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.indicatorscanner.domain.CandleStick;
import com.indicatorscanner.domain.Indices;

public interface CandleStickRepository
        extends CrudRepository<CandleStick, Long> {
    List<CandleStick> findById(Long id);

    @Override
    void delete(Long id);

    @Override
    CandleStick findOne(Long id);

    CandleStick findByIndex(Indices index);
}
