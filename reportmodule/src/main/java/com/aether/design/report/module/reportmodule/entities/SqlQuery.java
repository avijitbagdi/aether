package com.aether.design.report.module.reportmodule.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SqlQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long sqlid;
	private String sqldescription;

	private String query;

	public Long getSqlid() {
		return sqlid;
	}

	public void setSqlid(Long sqlid) {
		this.sqlid = sqlid;
	}

	public String getSqldescription() {
		return sqldescription;
	}

	public void setSqldescription(String sqldescription) {
		this.sqldescription = sqldescription;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
