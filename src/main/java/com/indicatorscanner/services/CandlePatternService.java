package com.indicatorscanner.services;

import com.indicatorscanner.domain.CandlePattern;

public interface CandlePatternService {
    Iterable<CandlePattern> listAllProducts();

    void deleteCandlePattern(String id);

    CandlePattern saveCandlePattern(CandlePattern index);

    CandlePattern saveOrUpdateCandlePattern(CandlePattern index);

}
