package com.project.config;

import com.project.dto.ResultsDTO;
import com.project.model.StatisticModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by abdullah.alnoman on 29.08.17.
 */
@Configuration
@EnableScheduling
public class Configaration {


    @Bean
    StatisticModel dataModel(){
        StatisticModel dataModel= new StatisticModel();
        ResultsDTO result = new ResultsDTO();
        dataModel.setResult(result);
        return dataModel;
    }
}
