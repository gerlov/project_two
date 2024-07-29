package com.kth.project_dollarstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kth.project_dollarstore.model.DeleteReason;

@Repository
public interface DeleteReasonRepository extends JpaRepository<DeleteReason, Integer> {}
