package com.corep.webapp.dto;


import com.corep.webapp.enums.Status;

public class ApiResponse<T> {
	private String message;
	private Status status;
	private T data;

	public ApiResponse(String message, Status status, T data){
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public ApiResponse(String message, Status status){
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
