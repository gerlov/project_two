package com.kth.project_dollarstore.repository;

import com.kth.project_dollarstore.model.ReceiptMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptMetaData, Long> {
    List<ReceiptMetaData> findByCustomerId(Integer customerId);
}
