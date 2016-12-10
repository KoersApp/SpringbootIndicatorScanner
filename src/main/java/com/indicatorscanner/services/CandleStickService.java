package com.indicatorscanner.services;

import com.indicatorscanner.domain.CandleStick;
import com.indicatorscanner.domain.Indices;

public interface CandleStickService {
    Iterable<CandleStick> listAllProducts();

    CandleStick getCandleStickByIndex(Indices index);

    void deleteCandleStick(Long id);

    CandleStick saveCandleStick(CandleStick candleStick);

    CandleStick saveOrUpdateCandleStick(CandleStick candleStick);

}
