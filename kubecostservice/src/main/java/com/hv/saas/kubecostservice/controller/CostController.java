package com.hv.saas.kubecostservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hv.saas.kubecostservice.controller.model.AllocationRequest;
import com.hv.saas.kubecostservice.controller.model.AssetRequest;
import com.hv.saas.kubecostservice.controller.model.ClusterAllocationCost;
import com.hv.saas.kubecostservice.controller.model.ClusterAssetCost;
import com.hv.saas.kubecostservice.service.CostService;
import com.hv.saas.kubecostservice.util.SaaSException;

@RestController
@RequestMapping("/cost")
public class CostController {

	@Autowired
	private CostService costService;

	@RequestMapping(value = "/cluster/{clusterId}", method = RequestMethod.GET)
	public ClusterAssetCost getClusterCost(@PathVariable String clusterId, @RequestBody AssetRequest assetRequest)
			throws SaaSException {
		return costService.getClusterCost(clusterId, assetRequest);
	}

	@RequestMapping(value = "/allocation/{clusterId}", method = RequestMethod.POST)
	public ClusterAllocationCost getCostAllocations(@PathVariable String clusterId,
			@RequestBody AllocationRequest allocationRequest) throws SaaSException {

		return costService.getCostAllocations(allocationRequest);
	}

}
