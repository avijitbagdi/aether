package com.aether.design.report.module.reportmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aether.design.report.module.reportmodule.entities.SqlQuery;


@Repository
public interface ReportSQLRepository extends JpaRepository<SqlQuery, Long> {

	@Query("SELECT s.query FROM SqlQuery s WHERE s.sqlid = :sqlId")
	public String findSqlQuery(@Param("sqlId") Long sqlId);
}
