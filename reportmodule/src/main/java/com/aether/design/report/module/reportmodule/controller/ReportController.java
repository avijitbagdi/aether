package com.aether.design.report.module.reportmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aether.design.report.module.reportmodule.exception.model.BusinessException;
import com.aether.design.report.module.reportmodule.request.model.QueryParamsRequestModel;
import com.aether.design.report.module.reportmodule.response.model.Response;
import com.aether.design.report.module.reportmodule.response.model.ResponseWrapper;
import com.aether.design.report.module.reportmodule.service.ReportService;
import com.aether.design.report.module.reportmodule.view.QueryResponse;


/**
 * @author BAGDI
 * 
 * Use for Report service
 *
 */
@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;

	/**
	 * @param sqlId         Sql id e.g.-101,102
	 * @param requestParams parameter details e.g.
	 * {
	 *	"queryParamsModel":[
	 *		{
	 *			"paramName":"EmpId",
	 *			"paramValue":1
	 *		}
	 *	]
	 *	}
	 * @return ResponseWrapper ResponseWrapper of 
	 * @throws Throwable
	 */
	@GetMapping("/sqlid/{sqlId}")
	public ResponseEntity<ResponseWrapper> getSqlQuery(@PathVariable("sqlId") Long sqlId,
									 				   @RequestBody(required = false) QueryParamsRequestModel requestParams) throws BusinessException {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		Response<QueryResponse> response = new Response<QueryResponse>();

		QueryResponse query = reportService.prepareSQLQuery(sqlId, requestParams);
		response.setBody(query);
		response.setStatus(HttpStatus.OK);
		responseWrapper.setResponse(response);
		return new ResponseEntity<ResponseWrapper>(responseWrapper, HttpStatus.OK);
	}
}

