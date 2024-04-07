package com.myproject.demo.factory;

import com.myproject.demo.enums.SplitType;
import com.myproject.demo.splitexecutor.ExactSplitExecutor;
import com.myproject.demo.splitexecutor.PercentageSplitExecutor;
import com.myproject.demo.splitexecutor.EqualSplitExecutor;
import com.myproject.demo.splitexecutor.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SplitExecutorFactory {
    @Autowired
    ExactSplitExecutor exactSplitExecutor;
    @Autowired
    EqualSplitExecutor equalSplitExecutor;
    @Autowired
    PercentageSplitExecutor percentageSplitExecutor;


    public Executor getExecutor(SplitType splitType) {
        switch (splitType) {
            case EQUAL -> {
                return equalSplitExecutor;
            }
            case EXACT -> {
                return exactSplitExecutor;
            }
            case PERCENT -> {
                return percentageSplitExecutor;
            }
        }
        throw new IllegalArgumentException("Executor not found for split type : "+ splitType);
    }
}
