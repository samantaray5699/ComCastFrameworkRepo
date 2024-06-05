package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDataFromJsonFile(String key) throws Throwable, ParseException

	{
		FileReader fr= new FileReader("./confgAppData/commondatajson.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);
		JSONObject jobj= (JSONObject)obj;
		String value = jobj.get(key).toString();
		return value;
	}
}
