package com.dahada.backend.application.calculation.service;

import java.util.List;

import com.dahada.backend.application.calculation.entity.Calculation;
import com.dahada.backend.application.calculation.repository.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartnerCalculationServiceImpl implements PartnerCalculationService {

    @Autowired
    private CalculationRepository repository;

    @Override
    @Transactional
    public void register(Calculation calculation) throws Exception {
        repository.save(calculation);
    }

    @Override
    @Transactional(readOnly = true)
    public Calculation read(Long boardNo) throws Exception {
        return repository.getOne(boardNo);
    }

    @Override
    @Transactional
    public void modify(Calculation calculation) throws Exception {
        Calculation calculationEntity = repository.getOne(calculation.getCalculationNo());

        // calculationEntity.setTitle(calculation.getTitle());
    }

    @Override
    @Transactional
    public void remove(Long calculationNo) throws Exception {
        repository.deleteById(calculationNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Calculation> list() throws Exception {
        return repository.findAll(Sort.by(Direction.DESC, "calculationNo"));
    }

}