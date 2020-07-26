package com.retryanalyzer;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryTranformer implements IAnnotationTransformer{

	public void transform(ITestAnnotation annotaions, Class testClass, Constructor testConstructor, Method testMethod) {
		
		IRetryAnalyzer retry = annotaions.getRetryAnalyzer();

		if (retry == null)	{
			annotaions.setRetryAnalyzer(RetryAnalyzer.class);
		}
		
	}


}