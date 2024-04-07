package com.myproject.demo.splitexecutor;

import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.entity.TnxEntity;

import java.util.List;

public interface Executor {
    List<TnxEntity.SplitTnx> execute(TnxRequest tnxRequest);
}
