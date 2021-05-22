package com.oracle.interview.round4.dao.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oracle.interview.round4.dao.beans.SupplierTable;

@Repository
@Transactional
public interface SupplierRepository extends JpaRepository<SupplierTable, String> {


	@Query(value = "Select * from Supplier_Table where item In :item", nativeQuery = true)
	List<SupplierTable> getSpecificSupplier(String item);

	@Query(value = "Select * from Supplier_Table", nativeQuery = true)
	List<SupplierTable> getAllSupplier();

	SupplierTable findByItemIdContainingIgnoreCase(String itemId);

	List<SupplierTable> findAllByItemId(String itemId);

}
