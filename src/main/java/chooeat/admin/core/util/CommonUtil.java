package chooeat.admin.core.util;

import java.io.BufferedReader;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
@Import(DataSourceAutoConfiguration.class)
public class CommonUtil {
	
	@Autowired
	private DataSource DATASOURCE;
	
	@Autowired
	public Gson GSON;
	
	public Connection getConnection() throws NamingException, SQLException {
		if (DATASOURCE == null) {
			DATASOURCE = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/javaFramework");
		}
		return DATASOURCE.getConnection();
	}

	public <P> P json2Pojo(HttpServletRequest req, Class<P> classOfPojo) {
		try (BufferedReader br = req.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public <P> void writePojo2Json(HttpServletResponse res, P pojo) {
		res.setContentType("application/json");
		try (PrintWriter pw = res.getWriter()) {
			pw.print(GSON.toJson(pojo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
