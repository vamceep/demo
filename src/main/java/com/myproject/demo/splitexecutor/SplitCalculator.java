package com.myproject.demo.splitexecutor;

import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.entity.TnxEntity;

import java.util.List;

public interface SplitCalculator {
    List<TnxEntity.SplitTnx> calculate(TnxRequest tnxRequest);
}
