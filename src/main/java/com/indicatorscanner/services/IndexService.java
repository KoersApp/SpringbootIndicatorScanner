package com.indicatorscanner.services;

import com.indicatorscanner.domain.Indices;

public interface IndexService {
    Iterable<Indices> listAllProducts();

    void deleteCandleStick(Long id);

    Indices saveIndices(Indices index);

    Indices saveOrUpdateCandleStick(Indices index);

    Indices findByIndexName(String indexName);

}
