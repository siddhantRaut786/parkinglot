package com.example.parkinglot.services.factories;

import com.example.parkinglot.models.SpotType;
import com.example.parkinglot.services.strategies.feesCalculationStrategy.FeesCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeesCalculationStrategyFactory {

    Map<SpotType, FeesCalculationStrategy> feesCalculationStrategyMap = new HashMap<>();

    @Autowired
    public FeesCalculationStrategyFactory(List<FeesCalculationStrategy> feesCalculationStrategyList) {
        for (FeesCalculationStrategy feesCalculationStrategy : feesCalculationStrategyList) {
            feesCalculationStrategyMap.put(feesCalculationStrategy.getSpotType(), feesCalculationStrategy);
        }
    }

    public FeesCalculationStrategy getFeesCalculationStrategy(SpotType spotType) {
        return feesCalculationStrategyMap.get(spotType);
    }
}
