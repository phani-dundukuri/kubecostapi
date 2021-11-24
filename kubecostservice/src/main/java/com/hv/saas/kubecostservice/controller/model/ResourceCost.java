package com.hv.saas.kubecostservice.controller.model;

import lombok.Data;

@Data
public class ResourceCost {
    private String type;
    private double cpuCost;
    private double gpuCost;
    private double ramCost;
    private double totalCost;
    private double adjustment;
    private Window window;
    private ResourceProperties properties;
}
