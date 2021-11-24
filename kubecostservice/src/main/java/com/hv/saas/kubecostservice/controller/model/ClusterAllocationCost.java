package com.hv.saas.kubecostservice.controller.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ClusterAllocationCost {

    private double totalCost;

    private List<Map<String, AllocationCost>> allocations;

}
