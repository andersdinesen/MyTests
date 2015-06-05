package com.anders.tests;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class TestingClass {

	public static void main(String[] args) throws Exception {
		TestingClass testingClass = new TestingClass();
		testingClass.readSomeJSON();

	}

	
	public void readSomeJSON() throws Exception {
		URL url = new URL("http://prod.middleman.dk/api/webcam/json/synced?type=webCam&syncVersion=1&");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("X-VD-APPVERSION", "500");
		connection.setRequestProperty("X-VD-APPID", "ANDROID");
		connection.connect();

		InputStream stream = connection.getInputStream();
		//ufferedInputStream reader = new BufferedInputStream(stream);
	
		BufferedReader r = new BufferedReader(new InputStreamReader(stream));
		StringBuilder total = new StringBuilder();
		String line;
		while ((line = r.readLine()) != null) {
		    total.append(line);
		}		
		/*
		StringWriter stringWriter = new StringWriter();
		IOUtils.copy(stream, stringWriter, "UTF-8");
		String result = stringWriter.toString();
		*/
		System.out.println("Result " + total.toString());
		
		JSONParser parser = new JSONParser();
		Object parsed = parser.parse(total.toString());
		
		JSONObject entities = (JSONObject)((JSONObject)parsed).get("entities");
		
		System.out.println("entities: " + entities.get("entities"));
		JSONArray neworchanged = (JSONArray) entities.get("neworchanged");
		for(Object element : neworchanged){
			System.out.println("Element: " + element.toString());
		}
		
		
	}
}
