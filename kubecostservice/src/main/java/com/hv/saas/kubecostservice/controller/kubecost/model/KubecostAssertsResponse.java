package com.hv.saas.kubecostservice.controller.kubecost.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class KubecostAssertsResponse {

    private Integer code;

    private List<Map<String, KubecostAssetsData>> data;

    private String warning;

}
