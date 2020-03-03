package com.orgfitech.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.orgfitech.dao.FactorResultDao;
import com.orgfitech.model.FactorResultDTO;

@Named("factorResultController")
@SessionScoped
public class FactorResultController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    protected ExternalContext externalContext;
    
    protected FactorResultDao factorResultDao;

    protected List<FactorResultDTO> factorResult;

    @Inject
    public FactorResultController(FactorResultDao FactorResultDao) {
        this.factorResultDao = FactorResultDao;
    }
    
    private void logMsg(String msg) {
        ((ServletContext)externalContext.getContext()).log(getClass().getSimpleName()+":"+msg);
    }
    
    public List<FactorResultDTO> getFactorResult() {
        return factorResult;
    }

    public void setFactorResult(List<FactorResultDTO> factorResult) {
        this.factorResult = factorResult;
    }
    
    public String loadFactorResult(String loginEmail) {
        logMsg("loginEmail " + loginEmail + " login.");
        setFactorResult(factorResultDao.readFactorResultByLoginEmail(loginEmail));
        return "create_factor_result";
    }
}
