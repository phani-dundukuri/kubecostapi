package com.hv.saas.kubecostservice.controller.model;

import java.util.List;

import lombok.Data;

@Data
public class ClusterAssetCost {

    private double totalCost;

    private List<ResourceCost> resources;

}
