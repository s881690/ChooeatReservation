package chooeat.admin.core.util;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class Constants {
	
	public DataSource DATASOURCE;
	public Gson GSON = new GsonBuilder().create();
//	public String JSON_MIME_TYPE = "application/json";
//	public String PREFIX_WEB_INF = "/WEB-INF";
}
