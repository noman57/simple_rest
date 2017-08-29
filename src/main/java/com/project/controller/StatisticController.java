package com.project.controller;


import com.project.dto.TransactionDTO;
import com.project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by abdullah.alnoman on 28.08.17.
 */

@RestController
public class StatisticController {



    private static Logger logger = Logger.getLogger(StatisticController.class.getName());


    @Autowired
    TransactionService service;


    /**
     *
     * Retrive statistics
     * @param
     * @return
     */
    @RequestMapping(
            value = "/statistics",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getStatistics() {
        logger.info("Get Statistics is being called");
        return new ResponseEntity<>(service.getCurrentStatistic(), HttpStatus.OK);
    }





    /**
     * Save a new transection
     *
     * @param transactionDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(
            value = "/transactions",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> saveTransactions(@Valid @RequestBody TransactionDTO transactionDTO) {


        logger.info("New transaction requested");
        if(service.saveTransactions(transactionDTO))
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }





}
