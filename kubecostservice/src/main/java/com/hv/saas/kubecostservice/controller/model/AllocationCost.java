package com.hv.saas.kubecostservice.controller.model;

import com.hv.saas.kubecostservice.controller.kubecost.model.AllocationProperties;

import lombok.Data;

@Data
public class AllocationCost {

    public String name;
    public double cpuCost;
    public double cpuCostAdjustment;
    public double gpuCost;
    public double gpuCostAdjustment;
    public double networkCost;
    public double networkCostAdjustment;
    public double loadBalancerCost;
    public double loadBalancerCostAdjustment;
    public double pvCost;
    public double pvCostAdjustment;
    public double ramCost;
    public double ramCostAdjustment;
    public double sharedCost;
    public double externalCost;
    public double totalCost;

    public Window window;
    public AllocationProperties properties;

}
