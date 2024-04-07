package com.myproject.demo.dto;

import com.myproject.demo.enums.SplitType;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TnxRequest {
    String lenderId;
    SplitType splitType;
    double totalAmount;
    String authorId;
    Metadata metadata;
    Map<String, Double> splits;
    List<String> borrowers;
    String groupId;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class Metadata {
        String description;
        String imgUrl;
    }
}
