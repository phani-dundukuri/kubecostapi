package com.hv.saas.kubecostservice.controller.model;

import lombok.Data;

@Data
public class ResourceProperties {
    private String category;
    private String service;
    private String cluster;
    private String name;
    private String providerID;
}
