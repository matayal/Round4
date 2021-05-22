package com.oracle.interview.round4.service;

import java.util.List;

import com.oracle.interview.round4.dao.beans.SupplierTable;

public interface SupplierService {

	public String addSupplier(SupplierTable user);

	public String deleteSupplier(String itemId);

	public String updateSupplier(SupplierTable user);

	public List<SupplierTable> viewSupplier(String itemId);

}
