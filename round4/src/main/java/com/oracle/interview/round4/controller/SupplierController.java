package com.oracle.interview.round4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.interview.round4.dao.beans.SupplierTable;
import com.oracle.interview.round4.service.SupplierService;

@RequestMapping("/oracle/lab/supplier")
@RestController
@CrossOrigin(maxAge = 3600)

public class SupplierController {

	@Autowired
	SupplierService supplierService;
	Logger logger = LoggerFactory.getLogger(SupplierController.class);

	@GetMapping(value = "/addSupplier")
	public Map<String, Object> addSupplier(@ModelAttribute SupplierTable user) throws Exception {
		Map<String, Object> response = new HashMap();

		String status = supplierService.addSupplier(user);

		if (status.equalsIgnoreCase("Success")) {
			response.put("ResponseCode", "200");
			response.put("ResponseText", "Successfully created");
		} else {
			response.put("ResponseCode", "300");
			response.put("ResponseText", "Failed to create");
		}
		logger.info("AddSupplier response:{}", response);

		return response;
	}

	@GetMapping(value = "/deleteSupplier/{itemId}/")
	public Map<String, Object> deleteSupplier(@PathVariable String itemId) throws Exception {
		Map<String, Object> response = new HashMap();

		String status = supplierService.deleteSupplier(itemId);

		if (status.equalsIgnoreCase("Success")) {
			response.put("ResponseCode", "200");
			response.put("ResponseText", "Successfully deleted");
		} else {
			response.put("ResponseCode", "300");
			response.put("ResponseText", "Failed to delete");
		}
		logger.info("DeleteSupplier response:{}", response);

		return response;
	}

	@GetMapping(value = "/updateSupplier")
	public Map<String, Object> updateSupplier(@ModelAttribute SupplierTable user) throws Exception {
		Map<String, Object> response = new HashMap();

		String status = supplierService.updateSupplier(user);

		if (status.equalsIgnoreCase("Success")) {
			response.put("ResponseCode", "200");
			response.put("ResponseText", "Successfully updated");
		} else {
			response.put("ResponseCode", "300");
			response.put("ResponseText", "Failed to update");
		}
		logger.info("UpdateSupplier response:{}", response);

		return response;
	}

	@GetMapping(value = "/viewSupplier")
	public Map<String, Object> viewSupplier(@RequestParam(name = "itemId", required = false) String itemId)
			throws Exception {
		Map<String, Object> response = new HashMap();

		List<SupplierTable> supplierData = supplierService.viewSupplier(itemId);

		if (!CollectionUtils.isEmpty(supplierData)) {
			response.put("ResponseCode", "200");
			response.put("ResponseText", "Success");
			response.put("ResponseBody", supplierData);
		} else {
			response.put("ResponseCode", "300");
			response.put("ResponseText", "Failed");
		}
		logger.info("ViewSupplier response:{}", response);

		return response;
	}
}
