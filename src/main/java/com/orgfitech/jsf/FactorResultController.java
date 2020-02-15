package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.orgfitech.dao.FactorResultDao;
import com.orgfitech.model.FactorResultDTO;

@Named("factorResultController")
@ApplicationScoped
public class FactorResultController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    protected ExternalContext externalContext;
    
    protected FactorResultDao FactorResultDao;
    
    protected List<FactorResultDTO> FactorResults;

    @Inject
    protected FactorResultDTO FactorResult;

    @Inject
    public FactorResultController(FactorResultDao FactorResultDao) {
        this.FactorResultDao = FactorResultDao;
    }
    
    public List<FactorResultDTO> getFactorResults() {
        return FactorResults;
    }

    public void setFactorResults(List<FactorResultDTO> FactorResults) {
        this.FactorResults = FactorResults;
    }
    
    public void loadFactorResult(int userID) {
        setFactorResults(FactorResultDao.readFactorResult(userID));
    }
}
