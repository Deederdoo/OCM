package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

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
    
    private void logMsg(String msg) {
        ((ServletContext)externalContext.getContext()).log(getClass().getSimpleName()+msg);
    }
    
    public List<FactorResultDTO> getFactorResults() {
        return FactorResults;
    }

    public void setFactorResults(List<FactorResultDTO> FactorResults) {
        this.FactorResults = FactorResults;
    }
    
    public String loadFactorResult(String loginEmail) {
        //setFactorResults(FactorResultDao.readFactorResultByUserId(userID));
        logMsg("loginEmail " + loginEmail + " login.");
        FactorResultDao.readFactorResultByLoginEmail(loginEmail);
        return null;
    }
}
