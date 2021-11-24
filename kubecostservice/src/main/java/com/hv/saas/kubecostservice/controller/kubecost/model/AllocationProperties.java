package com.hv.saas.kubecostservice.controller.kubecost.model;

import java.util.List;

import com.hv.saas.kubecostservice.controller.kubecost.model.KubecostAllocationData.Labels;

import lombok.Data;

@Data
public class AllocationProperties {

    public String cluster;
    public String node;
    public String container;
    public String controller;
    public String controllerKind;
    public String namespace;
    public String pod;
    public List<String> services;
    public String providerID;
    public Labels labels;

}
