package com.hv.saas.kubecostservice.controller.kubecost.model;

import java.util.Date;

import com.hv.saas.kubecostservice.controller.model.Window;

import lombok.Data;

@Data
public class KubecostAllocationData {

    @Data
    public static class Labels {
        public String app_kubernetes_io_name;
        public String kubernetes_io_metadata_name;
        public String pod_template_hash;
    }

    @Data
    public class RawAllocationOnly {
        public double cpuCoreUsageMax;
        public int ramByteUsageMax;
    }

    public String name;
    public AllocationProperties properties;
    public Window window;
    public Date start;
    public Date end;
    public double minutes;
    public double cpuCores;
    public double cpuCoreRequestAverage;
    public double cpuCoreUsageAverage;
    public double cpuCoreHours;
    public double cpuCost;
    public double cpuCostAdjustment;
    public double cpuEfficiency;
    public double gpuCount;
    public double gpuHours;
    public double gpuCost;
    public double gpuCostAdjustment;
    public double networkTransferBytes;
    public double networkReceiveBytes;
    public double networkCost;
    public double networkCostAdjustment;
    public double loadBalancerCost;
    public double loadBalancerCostAdjustment;
    public double pvBytes;
    public double pvByteHours;
    public double pvCost;
    public Object pvs;
    public double pvCostAdjustment;
    public double ramBytes;
    public double ramByteRequestAverage;
    public double ramByteUsageAverage;
    public double ramByteHours;
    public double ramCost;
    public double ramCostAdjustment;
    public double ramEfficiency;
    public double sharedCost;
    public double externalCost;
    public double totalCost;
    public double totalEfficiency;
    public RawAllocationOnly rawAllocationOnly;

}
