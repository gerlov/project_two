package com.kth.project_dollarstore.repository;

import com.kth.project_dollarstore.model.ReceiptMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<ReceiptMetaData, Long> {
}
