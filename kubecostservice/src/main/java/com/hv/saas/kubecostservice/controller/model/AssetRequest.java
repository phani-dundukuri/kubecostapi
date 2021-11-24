package com.hv.saas.kubecostservice.controller.model;

import java.util.List;

import lombok.Data;

@Data
public class AssetRequest {

	private String window;
	private String aggregate;
	private List<String> filterTypes;
	private List<String> filterLabels;
	private String idle;

}
