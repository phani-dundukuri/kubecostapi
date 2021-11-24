package com.hv.saas.kubecostservice.controller.kubecost.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class KubecostAllocationResponse {

    private Integer code;

    private List<Map<String, KubecostAllocationData>> data;

    private String warning;

}
