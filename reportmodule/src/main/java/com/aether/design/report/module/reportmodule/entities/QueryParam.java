package com.aether.design.report.module.reportmodule.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QueryParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long sqlid;
	private Long paramid;
	private String paramname;
	private String paramtype;

	public Long getSqlid() {
		return sqlid;
	}

	public void setSqlid(Long sqlid) {
		this.sqlid = sqlid;
	}

	public Long getParamid() {
		return paramid;
	}

	public void setParamid(Long paramid) {
		this.paramid = paramid;
	}

	public String getParamname() {
		return paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getParamtype() {
		return paramtype;
	}

	public void setParamtype(String paramtype) {
		this.paramtype = paramtype;
	}
}