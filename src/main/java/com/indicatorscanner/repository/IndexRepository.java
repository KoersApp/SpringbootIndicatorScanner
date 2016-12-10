
package com.indicatorscanner.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.indicatorscanner.domain.Indices;

public interface IndexRepository extends CrudRepository<Indices, Long> {
    List<Indices> findById(Long id);

    @Override
    void delete(Long id);

    @Override
    Indices findOne(Long id);

    Indices findByIndexName(String indexName);
}
