package com.orgfitech.dao;

import java.util.List;

import com.orgfitech.model.FactorResultDTO;

public interface FactorResultDao {

    public List<FactorResultDTO> readFactorResult(int userID);
}
