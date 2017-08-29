package com.project.model;

import com.project.dto.ResultsDTO;
import com.project.dto.TransactionDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdullah.alnoman on 28.08.17.
 */

public class StatisticModel {



    private  List<TransactionDTO> transactionDTOList = new ArrayList<TransactionDTO>();


    private ResultsDTO result;

    public List<TransactionDTO> getTransactionDTOList() {
        return transactionDTOList;
    }

    public void setTransactionDTOList(List<TransactionDTO> transactionDTOList) {
        this.transactionDTOList = transactionDTOList;
    }

    public ResultsDTO getResult() {
        return result;
    }

    public void setResult(ResultsDTO result) {
        this.result = result;
    }
}
