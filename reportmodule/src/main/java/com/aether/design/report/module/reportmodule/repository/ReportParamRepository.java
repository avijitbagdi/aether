package com.aether.design.report.module.reportmodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aether.design.report.module.reportmodule.entities.QueryParam;


@Repository
public interface ReportParamRepository extends JpaRepository<QueryParam, Long> {

	@Query("SELECT p.paramname, p.paramtype FROM QueryParam p WHERE p.sqlid = :sqlId")
	public List<QueryParam> findParams(@Param("sqlId") Long sqlId);
}
