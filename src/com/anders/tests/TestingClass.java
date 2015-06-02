package com.anders.tests;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.io.IOUtils;


public class TestingClass {

	public static void main(String[] args) throws Exception {
		TestingClass testingClass = new TestingClass();
		testingClass.readSomeJSON();

	}

	
	public void readSomeJSON() throws Exception {
		URL url = new URL("http://prod.middleman.dk/api/webcam/json/synced&");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("X-VD-APPVERSION", "500");
		connection.setRequestProperty("X-VD-APPID", "ANDROID");
		connection.connect();

		InputStream stream = connection.getInputStream();
		BufferedInputStream reader = new BufferedInputStream(stream);
	
		StringWriter stringWriter = new StringWriter();
		IOUtils.copy(stream, stringWriter, "UTF-8");
		String result = stringWriter.toString();
		
		System.out.println("Result " + result);
	}
}
