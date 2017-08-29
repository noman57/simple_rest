package com.project.service.impl;

import com.project.dto.ResultsDTO;
import com.project.dto.TransactionDTO;
import com.project.model.StatisticModel;
import com.project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by abdullah.alnoman on 28.08.17.
 */

@Service
public class TransactionServiceImpl implements TransactionService {


    private static Logger logger = Logger.getLogger(TransactionServiceImpl.class.getName());


    @Autowired
    StatisticModel dataModel;



    @Override
    public ResultsDTO getCurrentStatistic() {
        return dataModel.getResult();
    }

    @Override
    public boolean saveTransactions(TransactionDTO transactionDTO) {
        if(isValidTransaction(transactionDTO))
            return dataModel.getTransactionDTOList().add(transactionDTO);
        return false;
    }


    @Override
    public void processTransactions() {

        double max=0,min=0;
        BigDecimal sum= new BigDecimal(0);
        BigDecimal avg= new BigDecimal(0);
        int count=0;


        List<TransactionDTO> transactionDTOList=dataModel.getTransactionDTOList();
        Iterator Iterator = transactionDTOList.iterator();
        while (Iterator.hasNext()) {
            TransactionDTO transaction = (TransactionDTO) Iterator.next();
            if(!isValidTransaction(transaction))
                Iterator.remove();
            else{
                //sum calculation
                sum=sum.add(BigDecimal.valueOf(transaction.getAmount()));

                // max calculation
                if(max==0 || max<transaction.getAmount())
                    max=transaction.getAmount();
                // min calculation
                if(min==0 || min>transaction.getAmount())
                    min=transaction.getAmount();




            }
        }

        ResultsDTO result= dataModel.getResult();

        if(dataModel.getTransactionDTOList().size()>0)
            avg=sum.divide(BigDecimal.valueOf(dataModel.getTransactionDTOList().size()),3, RoundingMode.CEILING);
        ResultsDTO newResult = new ResultsDTO();
        newResult.setAvg(avg);
        newResult.setSum(sum);
        newResult.setMax(max);
        newResult.setMin(min);
        newResult.setCount(dataModel.getTransactionDTOList().size());
        dataModel.setResult(newResult);


    }


    private boolean isValidTransaction(TransactionDTO transactionDTO){
        boolean value=(( System.currentTimeMillis()-transactionDTO.getTimestamp())/1000)<60;
        return value;

    }


}
