package com.myproject.demo.factory;

import com.myproject.demo.enums.SplitType;
import com.myproject.demo.splitexecutor.ExactSplitSplitCalculator;
import com.myproject.demo.splitexecutor.PercentageSplitSplitCalculator;
import com.myproject.demo.splitexecutor.EqualSplitSplitCalculator;
import com.myproject.demo.splitexecutor.SplitCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SplitExecutorFactory {
    @Autowired
    ExactSplitSplitCalculator exactSplitCalculator;
    @Autowired
    EqualSplitSplitCalculator equalSplitCalculator;
    @Autowired
    PercentageSplitSplitCalculator percentageSplitCalculator;


    public SplitCalculator getCalculator(SplitType splitType) {
        switch (splitType) {
            case EQUAL -> {
                return equalSplitCalculator;
            }
            case EXACT -> {
                return exactSplitCalculator;
            }
            case PERCENT -> {
                return percentageSplitCalculator;
            }
        }
        throw new IllegalArgumentException("Executor not found for split type : "+ splitType);
    }
}
