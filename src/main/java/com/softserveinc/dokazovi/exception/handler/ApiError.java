package com.softserveinc.dokazovi.exception.handler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {
	private HttpStatus status;
	private List<String> errors;
}
