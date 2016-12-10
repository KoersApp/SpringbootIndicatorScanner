package com.indicatorscanner.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.indicatorscanner.domain.CandlePattern;

public interface CandlePatternRepository
        extends CrudRepository<CandlePattern, String> {
    List<CandlePattern> findByPatternName(String patternName);

    @Override
    void delete(String patternName);

    @Override
    CandlePattern findOne(String patternName);
}
