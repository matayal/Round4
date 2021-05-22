package com.oracle.interview.round4.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.interview.round4.dao.beans.SupplierTable;
import com.oracle.interview.round4.dao.repo.SupplierRepository;
import com.oracle.interview.round4.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepo;
	Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

	@Override
	public String addSupplier(SupplierTable user) {

		String status = "";
		boolean flag = false;

		List<SupplierTable> existingData = supplierRepo.findAll();

		for (SupplierTable data : existingData) {
			if (user.getItemId().equalsIgnoreCase(data.getItemId())) {
				flag = true;
			} else {
				flag = false;
			}
		}
		if (!flag) {
			try {
				SupplierTable newSupplier = new SupplierTable();

				newSupplier.setItemId(user.getItemId());
				newSupplier.setItemName(user.getItemName());
				newSupplier.setItemCount(user.getItemCount());
				newSupplier.setSupplierName(user.getSupplierName());
				supplierRepo.saveAndFlush(newSupplier);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			status = "Success";
		} else {
			status = "Failed";
		}
		return status;
	}

	@Override
	public String deleteSupplier(String itemId) {
		String status = "Failed";
		List<SupplierTable> existingData = supplierRepo.findAll();

		for (SupplierTable record : existingData) {

			if (itemId.equalsIgnoreCase(record.getItemId())) {
				try {
					supplierRepo.deleteById(itemId);
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
				status = "Success";
			}

		}
		return status;
	}

	@Override
	public String updateSupplier(SupplierTable user) {
		String status = "";
		SupplierTable existingData = supplierRepo.findByItemIdContainingIgnoreCase(user.getItemId());
		if (null != existingData) {
			try {
				if (user.getItemName() != null) {
					existingData.setItemName(user.getItemName());
				}
				existingData.setItemCount(user.getItemCount());

				if (user.getSupplierName() != null) {
					existingData.setSupplierName(user.getSupplierName());
				}
				supplierRepo.saveAndFlush(existingData);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			status = "Success";
		} else {
			status = "Failed";
		}
		return status;
	}

	@Override
	public List<SupplierTable> viewSupplier(String itemId) {
		List<SupplierTable> supplierData = new ArrayList<SupplierTable>();
		try {
			if (itemId != null) {
				supplierData = supplierRepo.findAllByItemId(itemId);
			} else if (itemId == null || itemId.isEmpty()) {
				supplierData = supplierRepo.findAll();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return supplierData;
	}

}
