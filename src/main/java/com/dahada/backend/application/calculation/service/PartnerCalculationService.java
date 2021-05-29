package com.dahada.backend.application.calculation.service;

import com.dahada.backend.application.calculation.entity.Calculation;

import java.util.List;

public interface PartnerCalculationService {
    public void register(Calculation calculation) throws Exception;

    public Calculation read(Long boardNo) throws Exception;

    public void modify(Calculation calculation) throws Exception;

    public void remove(Long calculationNo) throws Exception;

    public List<Calculation> list() throws Exception;
}
