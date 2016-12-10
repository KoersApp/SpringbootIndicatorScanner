package com.indicatorscanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indicatorscanner.domain.Indices;
import com.indicatorscanner.repository.IndexRepository;

@Service
public class IndexServiceImpl implements IndexService {
    private IndexRepository indexRepository;

    @Autowired
    public void setProductRepository(
            IndexRepository indexRepository) {
        this.indexRepository = indexRepository;
    }

    @Override
    public Iterable<Indices> listAllProducts() {
        return indexRepository.findAll();
    }

    @Override
    public void deleteCandleStick(Long id) {
        indexRepository.delete(id);
    }

    @Override
    public Indices saveIndices(Indices index) {
        indexRepository.save(index);
        return index;
    }

    @Override
    public Indices saveOrUpdateCandleStick(Indices index) {
        indexRepository.save(index);
        return index;
    }

    @Override
    public Indices findByIndexName(String indexName) {
        return indexRepository.findByIndexName(indexName);
    }

}
