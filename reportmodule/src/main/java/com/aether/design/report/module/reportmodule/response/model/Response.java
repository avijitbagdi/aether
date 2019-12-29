package com.aether.design.report.module.reportmodule.response.model;

import org.springframework.http.HttpStatus;

public class Response<T> {
	T body;
	Message message;
	HttpStatus status;

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
