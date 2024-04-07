package com.myproject.demo.splitexecutor;

import com.myproject.demo.dto.TnxRequest;

public interface SplitValidator {
    boolean isValid(TnxRequest tnxRequest);
}
