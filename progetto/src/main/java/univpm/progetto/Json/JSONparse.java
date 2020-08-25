package univpm.progetto.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownServiceException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class JSONparse {
	
	
	public JSONObject returnMetadataJson() {
			String url = "https://api.dropboxapi.com/2/files/get_metadata";
			try {

				HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
				openConnection.setRequestMethod("POST");
				openConnection.setRequestProperty("Authorization",
						"Bearer W5uaWGNcenwAAAAAAAAAATFdORMUJrzR-ejKmubYr5b24LEtFGkS_OinCWZToDWn");
				openConnection.setRequestProperty("Content-Type", "application/json");
				openConnection.setRequestProperty("Accept", "application/json");
				openConnection.setDoOutput(true);
				String jsonBody = "{\r\n" + 
						"    \"path\": \"/Applicazioni/AppOOP2/App\",\r\n" + 
						"    \"include_media_info\": true,\r\n" + 
						"    \"include_deleted\": false\r\n" + 
						"}";
				
				try (OutputStream os = openConnection.getOutputStream()) {
					byte[] input = jsonBody.getBytes("utf-8");
					os.write(input, 0, input.length);
				}
				catch (UnknownServiceException e) {
					e.printStackTrace();
				}

				InputStream in = openConnection.getInputStream();

				String data = "";
				String line = "";
				try {
					InputStreamReader inR = new InputStreamReader(in);
					BufferedReader buf = new BufferedReader(inR);

					while ((line = buf.readLine()) != null) {
						data += line;
					}
				} finally {
					in.close();
				}
				JSONObject json = (JSONObject) JSONValue.parseWithException(data);
				return json;
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
	
	
	
	
	public JSONObject returnSearchJson() {
	    String url = "https://api.dropboxapi.com/2/files/search_v2";
		
		try {

			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization",
					"Bearer W5uaWGNcenwAAAAAAAAAATFdORMUJrzR-ejKmubYr5b24LEtFGkS_OinCWZToDWn");
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);
			String jsonBody = "{\r\n" + 
					"    \"query\": \"App\"\r\n" + 
					"}";

			try (OutputStream os = openConnection.getOutputStream()) {
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			catch (UnknownServiceException e) {
				e.printStackTrace();
			}

			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
				}
			} finally {
				in.close();
			}
			JSONObject json = (JSONObject) JSONValue.parseWithException(data);
			return json;
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

