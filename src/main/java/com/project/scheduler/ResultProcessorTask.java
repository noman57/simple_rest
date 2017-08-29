package com.project.scheduler;

import com.project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by abdullah.alnoman on 29.08.17.
 */
@Component
public class ResultProcessorTask {

    @Autowired
    TransactionService service;


    @Scheduled(fixedDelay = 1000)
    void processTransactions(){
        service.processTransactions();
    }
}
