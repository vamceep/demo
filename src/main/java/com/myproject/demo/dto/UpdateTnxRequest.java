package com.myproject.demo.dto;

import lombok.Getter;

@Getter
public class UpdateTnxRequest extends TnxRequest{
    int oldTnxId;
}
