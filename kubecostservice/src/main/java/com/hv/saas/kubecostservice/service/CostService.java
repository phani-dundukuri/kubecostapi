package com.hv.saas.kubecostservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hv.saas.kubecostservice.controller.kubecost.model.KubecostAllocationData;
import com.hv.saas.kubecostservice.controller.kubecost.model.KubecostAllocationResponse;
import com.hv.saas.kubecostservice.controller.kubecost.model.KubecostAssertsResponse;
import com.hv.saas.kubecostservice.controller.kubecost.model.KubecostAssetsData;
import com.hv.saas.kubecostservice.controller.model.AllocationCost;
import com.hv.saas.kubecostservice.controller.model.AllocationRequest;
import com.hv.saas.kubecostservice.controller.model.AssetRequest;
import com.hv.saas.kubecostservice.controller.model.ClusterAllocationCost;
import com.hv.saas.kubecostservice.controller.model.ClusterAssetCost;
import com.hv.saas.kubecostservice.controller.model.ResourceCost;
import com.hv.saas.kubecostservice.util.Constants;
import com.hv.saas.kubecostservice.util.SaaSException;

@Service
public class CostService {

	private RestTemplate restTemplate = new RestTemplate();

	public ClusterAssetCost getClusterCost(String clusterId, AssetRequest assetRequest) throws SaaSException {

		KubecostAssertsResponse assertsResponse;
		try {
			assertsResponse = restTemplate.getForObject(
					Constants.KUBECOST_ENDPOINT + "/model/assets?" + getRequestParams(assetRequest),
					KubecostAssertsResponse.class);
		} catch (RestClientException exception) {
			System.out.println("" + exception);
			throw new SaaSException("Cost fetching api failed");
		}
		List<ResourceCost> resources = assertsResponse.getData().stream().flatMap(map -> map.values().stream())
				.filter(resource -> StringUtils.hasText(resource.getType())).map(this::buildResourceModel)
				.collect(Collectors.toList());
		ClusterAssetCost clusterCost = new ClusterAssetCost();
		clusterCost.setResources(resources);
		clusterCost.setTotalCost(resources.stream().mapToDouble(r -> r.getTotalCost()).sum());
		return clusterCost;
	}

	public ClusterAllocationCost getCostAllocations(AllocationRequest request) throws SaaSException {

		KubecostAllocationResponse allocationResponse;

		try {
			allocationResponse = restTemplate.getForObject(
					Constants.KUBECOST_ENDPOINT + "/model/allocation?" + getRequestParams(request),
					KubecostAllocationResponse.class);
		} catch (RestClientException exception) {
			System.out.println("" + exception);
			throw new SaaSException("Cost fetching api failed");
		}

		List<Map<String, AllocationCost>> allAllocations = new ArrayList<Map<String, AllocationCost>>();

		allocationResponse.getData().forEach(e -> {
			Map<String, AllocationCost> allocations = new HashMap<String, AllocationCost>();
			e.forEach((k, v) -> {
				allocations.put(k, buildResourceModel(v));
			});
			if (!allocations.isEmpty())
				allAllocations.add(allocations);

		});

		ClusterAllocationCost allocationCost = new ClusterAllocationCost();

		allocationCost.setAllocations(allAllocations);
		allocationCost.setTotalCost(allAllocations.stream().flatMap(map -> map.values().stream())
				.collect(Collectors.toList()).stream().mapToDouble(r -> r.getTotalCost()).sum());
		return allocationCost;

	}

	private String getRequestParams(AssetRequest allocationRequest) {
		StringBuilder builder = new StringBuilder();
		builder.append(
				StringUtils.hasText(allocationRequest.getWindow()) ? "window=" + allocationRequest.getWindow() : "");
		builder.append(
				StringUtils.hasText(allocationRequest.getAggregate()) ? "&aggregate=" + allocationRequest.getAggregate()
						: "");
		builder.append(CollectionUtils.isEmpty(allocationRequest.getFilterTypes()) ? ""
				: "&filterTypes=" + String.join(",", allocationRequest.getFilterTypes()));
		builder.append(CollectionUtils.isEmpty(allocationRequest.getFilterLabels()) ? ""
				: "&filterLabels=" + String.join(",", allocationRequest.getFilterLabels()));
		builder.append(StringUtils.hasText(allocationRequest.getIdle()) ? "&idle=" + allocationRequest.getIdle() : "");
		builder.append("&accumulate=" + false);
		return builder.toString();
	}

	private String getRequestParams(AllocationRequest allocationRequest) {
		StringBuilder builder = new StringBuilder();
		builder.append(
				StringUtils.hasText(allocationRequest.getWindow()) ? "window=" + allocationRequest.getWindow() : "");
		builder.append(
				StringUtils.hasText(allocationRequest.getAggregate()) ? "&aggregate=" + allocationRequest.getAggregate()
						: "");
		builder.append(CollectionUtils.isEmpty(allocationRequest.getFilterNamespaces()) ? ""
				: "&filterNamespaces=" + String.join(",", allocationRequest.getFilterNamespaces()));
		builder.append(CollectionUtils.isEmpty(allocationRequest.getFilterLabels()) ? ""
				: "&filterLabels=" + String.join(",", allocationRequest.getFilterLabels()));
		builder.append(StringUtils.hasText(allocationRequest.getIdle()) ? "&idle=" + allocationRequest.getIdle() : "");
		builder.append(
				StringUtils.hasText(allocationRequest.getStep()) ? "&accumulate=" + false : "&accumulate=" + true);
		builder.append(StringUtils.hasText(allocationRequest.getStep()) ? "&step=" + allocationRequest.getStep() : "");
		return builder.toString();
	}

	private ResourceCost buildResourceModel(KubecostAssetsData assetsData) {
		ResourceCost resourceCost = new ResourceCost();
		resourceCost.setType(assetsData.getType());
		resourceCost.setCpuCost(assetsData.getCpuCost());
		resourceCost.setGpuCost(assetsData.getGpuCost());
		resourceCost.setRamCost(assetsData.getRamCost());
		resourceCost.setAdjustment(assetsData.getAdjustment());
		resourceCost.setTotalCost(assetsData.getTotalCost());
		resourceCost.setProperties(assetsData.getProperties());
		return resourceCost;
	}

	private AllocationCost buildResourceModel(KubecostAllocationData allocationData) {
		AllocationCost cost = new AllocationCost();
		cost.setCpuCost(allocationData.getCpuCost());
		cost.setCpuCostAdjustment(allocationData.getCpuCostAdjustment());
		cost.setExternalCost(allocationData.getExternalCost());
		cost.setGpuCost(allocationData.getGpuCost());
		cost.setLoadBalancerCost(allocationData.getLoadBalancerCost());
		cost.setLoadBalancerCostAdjustment(allocationData.getLoadBalancerCostAdjustment());
		cost.setName(allocationData.getName());
		cost.setNetworkCost(allocationData.getNetworkCost());
		cost.setNetworkCostAdjustment(allocationData.getNetworkCostAdjustment());
		cost.setProperties(allocationData.getProperties());
		cost.setPvCost(allocationData.getPvCost());
		cost.setPvCostAdjustment(allocationData.getPvCostAdjustment());
		cost.setRamCost(allocationData.getRamCost());
		cost.setRamCostAdjustment(allocationData.getRamCostAdjustment());
		cost.setSharedCost(allocationData.getSharedCost());
		cost.setTotalCost(allocationData.getTotalCost());
		cost.setWindow(allocationData.getWindow());
		return cost;
	}

}
