package com.hv.saas.kubecostservice.controller.kubecost.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hv.saas.kubecostservice.controller.model.ResourceProperties;
import com.hv.saas.kubecostservice.controller.model.Window;

import lombok.Data;

@Data
public class KubecostAssetsData {

    @Data
    public static class Labels {
        private String instance;
        private String job;
        private String label_agentpool;
        private String label_beta_kubernetes_io_arch;
        private String label_beta_kubernetes_io_instance_type;
        private String label_beta_kubernetes_io_os;
        private String label_failure_domain_beta_kubernetes_io_region;
        private String label_failure_domain_beta_kubernetes_io_zone;
        private String label_kubernetes_azure_com_agentpool;
        private String label_kubernetes_azure_com_cluster;
        private String label_kubernetes_azure_com_mode;
        private String label_kubernetes_azure_com_node_image_version;
        private String label_kubernetes_azure_com_os_sku;
        private String label_kubernetes_azure_com_role;
        private String label_kubernetes_azure_com_storageprofile;
        private String label_kubernetes_azure_com_storagetier;
        private String label_kubernetes_io_arch;
        private String label_kubernetes_io_hostname;
        private String label_kubernetes_io_os;
        private String label_kubernetes_io_role;
        private String label_node_kubernetes_io_instance_type;
        private String label_storageprofile;
        private String label_storagetier;
        private String label_topology_kubernetes_io_region;
        private String label_topology_kubernetes_io_zone;
        private String node;
    }

    @Data
    public class CpuBreakdown {
        private double idle;
        private double other;
        private double system;
        private double user;
    }

    @Data
    public class RamBreakdown {
        private double idle;
        private int other;
        private double system;
        private double user;
    }

    private String type;
    private ResourceProperties properties;
    private Labels labels;
    private Window window;
    private Date start;
    private Date end;
    private double minutes;
    private String nodeType;
    private double cpuCores;
    private double ramBytes;
    private double cpuCoreHours;
    private double ramByteHours;
    @JsonProperty("GPUHours")
    private double gPUHours;
    private CpuBreakdown cpuBreakdown;
    private RamBreakdown ramBreakdown;
    private double preemptible;
    private double discount;
    private double cpuCost;
    private double gpuCost;
    private double gpuCount;
    private double ramCost;
    private double adjustment;
    private double totalCost;

}
