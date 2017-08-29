package com.project;

import com.project.dto.ResultsDTO;
import com.project.dto.TransactionDTO;
import com.project.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by abdullah.alnoman on 29.08.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    TransactionService service;


    @Test
    public void whenValidTime_transactionShouldBeSaved() {
        TransactionDTO transactionDTO =  new TransactionDTO();
        transactionDTO.setAmount(12.3);
        transactionDTO.setTimestamp(System.currentTimeMillis());
        boolean flag=service.saveTransactions(transactionDTO);
        assert (flag);

    }



    @Test
    public void wheninvalidTime_transactionShouldNotSave() {
        TransactionDTO transactionDTO =  new TransactionDTO();
        transactionDTO.setAmount(12.3);
        transactionDTO.setTimestamp(System.currentTimeMillis()-100000);
        boolean flag=service.saveTransactions(transactionDTO);
        assert (!flag);

    }


    @Test
    public void whenValidData_ShowValidResult() {

        for(int i=0;i<10;i++){
            TransactionDTO transactionDTO =  new TransactionDTO();
            transactionDTO.setAmount(12.3+i);
            transactionDTO.setTimestamp(System.currentTimeMillis());
            boolean flag=service.saveTransactions(transactionDTO);
        }


        ResultsDTO expected= new ResultsDTO();
        expected.setSum(new BigDecimal("180.3"));
        expected.setAvg(new BigDecimal("16.391"));
        expected.setCount(11);
        expected.setMax(21.3);
        expected.setMin(12.3);

        service.processTransactions();
        ResultsDTO result=service.getCurrentStatistic();
        System.out.println(result.toString());
        assertEquals(result,expected);

    }


    @Test
    public void whenExpires_updateResult() {

        for(int i=0;i<10;i++){
            TransactionDTO transactionDTO =  new TransactionDTO();
            transactionDTO.setAmount(12.3+i);
            transactionDTO.setTimestamp(System.currentTimeMillis()-60000);
            boolean flag=service.saveTransactions(transactionDTO);
        }


        ResultsDTO expected= new ResultsDTO();
        expected.setSum(new BigDecimal("180.3"));
        expected.setAvg(new BigDecimal("16.391"));
        expected.setCount(11);
        expected.setMax(21.3);
        expected.setMin(12.3);

        service.processTransactions();
        ResultsDTO result=service.getCurrentStatistic();
        assertNotEquals(result,expected);

    }


}
