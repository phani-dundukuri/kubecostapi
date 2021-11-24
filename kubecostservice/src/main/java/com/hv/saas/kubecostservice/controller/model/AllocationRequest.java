package com.hv.saas.kubecostservice.controller.model;

import java.util.List;

import lombok.Data;

@Data
public class AllocationRequest {

	private String clusterId;
	private String window;
	private String aggregate;
	private List<String> filterNamespaces;
	private List<String> filterLabels;
	private String idle;
	private String step;
}
