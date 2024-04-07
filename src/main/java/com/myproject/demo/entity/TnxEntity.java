package com.myproject.demo.entity;

import com.myproject.demo.enums.TnxStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class TnxEntity {
    int tnxId;
    String lender;
    String author;
    TnxStatus status;
    MetaData metaData;
    List<SplitTnx> splits;
    Date date;

    @Builder
    public static class MetaData {
        String description;
        String imageUrl;
        String deletedBy;
    }

    @Getter
    @Builder
    public static class SplitTnx {
        String lender;
        String borrower;
        Double amount;
    }
}
