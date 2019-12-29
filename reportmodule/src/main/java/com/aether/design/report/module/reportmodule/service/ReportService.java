package com.aether.design.report.module.reportmodule.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aether.design.report.module.reportmodule.exception.model.BusinessException;
import com.aether.design.report.module.reportmodule.repository.ReportSQLRepository;
import com.aether.design.report.module.reportmodule.request.model.QueryParamRequestModel;
import com.aether.design.report.module.reportmodule.request.model.QueryParamsRequestModel;
import com.aether.design.report.module.reportmodule.view.QueryResponse;



@Service
public class ReportService {

	@Autowired
	private ReportSQLRepository reportSQLRepository;

	/**
	 * @param sqlId         Sql id e.g.-101,102
	 * @param requestParams parameter details e.g. paramName:EmpId, paramValue:001
	 * @return QueryResponse QueryResponse
	 * @throws Exception
	 */
	public QueryResponse prepareSQLQuery(Long sqlId, QueryParamsRequestModel requestParams) throws BusinessException {

		QueryResponse queryResponse = new QueryResponse();
		StringBuffer actaulQuery = new StringBuffer();

		// Fetch query from table
		String query = reportSQLRepository.findSqlQuery(sqlId);
		// Sql id is valid
		if (query != null) {
			for (int i = 0; i < query.length(); i++) {
				// $ replacement
				// find 1st $
				if (query.charAt(i) == '$') {
					// iterate till $ not found
					// find 2nd $
					StringBuffer replaceParamWithValue = new StringBuffer();
					for (int j = i + 1; j < query.length(); j++) {
						// find the 2nd $ if found break otherwise append to string buffer variable
						if (query.charAt(j) == '$') {
							i = j + 1;// i should be after finding 2nd $
							break;
						} else {
							replaceParamWithValue.append(query.charAt(j));
						}
					}
					// Set actual Param value with sql param
					if (StringUtils.isNotBlank(replaceParamWithValue) && requestParams != null) {
						for (QueryParamRequestModel requestParam : requestParams.getQueryParamsModel()) {
							if (requestParam != null && StringUtils.equalsIgnoreCase(requestParam.getParamName(),
									String.valueOf(replaceParamWithValue))) {
								actaulQuery.append(requestParam.getParamValue());
								break;
							}
						}
					}
				} else {
					actaulQuery.append(query.charAt(i));
				}
			}
		} else {
			throw new BusinessException(HttpStatus.NOT_FOUND, "sql id is not correct");
		}
		if (actaulQuery.charAt(actaulQuery.length() - 1) != ';') {
			actaulQuery.append(";");
		}
		if (actaulQuery != null) {
			queryResponse.setQuery(actaulQuery.toString());
		}
		return queryResponse;
	}
}
