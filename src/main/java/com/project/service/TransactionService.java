package com.project.service;

import com.project.dto.ResultsDTO;
import com.project.dto.TransactionDTO;

import java.util.List;

/**
 * Created by abdullah.alnoman on 28.08.17.
 */


public interface TransactionService {

    ResultsDTO  getCurrentStatistic();


    boolean saveTransactions(TransactionDTO  transactionDTO);


    void processTransactions();


}
