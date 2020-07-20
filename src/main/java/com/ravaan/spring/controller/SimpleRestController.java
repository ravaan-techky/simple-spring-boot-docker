/**
 * @(#)SimpleRestController.java file created on 20th July, 2020. 
 */
package com.ravaan.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class SimpleRestController.
 */
@RestController
public class SimpleRestController {

	/** The app name. */
	@Value("${spring.application.name}")
	String appName;

	/**
	 * Gets the application name.
	 *
	 * @return the application name
	 */
	@GetMapping("/getApplicationName")
	public Map<String, String> getApplicationName() {
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("appName", appName);
		return resultMap;
	}
}
