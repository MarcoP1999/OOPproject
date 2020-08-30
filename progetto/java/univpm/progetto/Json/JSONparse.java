package univpm.progetto.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class JSONparse {	
	
	
	public JSONArray returnMetadataJson() {
		JSONArray array = new JSONArray();
			String url = "https://api.dropboxapi.com/2/files/get_metadata";
			String meta1 = "{\r\n" + 
					"    \"path\": \"/Applicazioni/AppOOP2/App/App/BOB.jpg\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": true\r\n" + 
					"}";
			String meta2 = "{\r\n" + 
					"    \"path\": \"/Applicazioni/AppOOP2/App/App/medioevo.jpg\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": true\r\n" + 
					"}";
			String meta3 = "{\r\n" + 
					"    \"path\": \"/Applicazioni/AppOOP2/App/App/genji.tiff\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": true\r\n" + 
					"}";
			String meta4 = "{\r\n" + 
					"    \"path\": \"/Applicazioni/AppOOP2/App/App/fisica1.jpeg\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": true\r\n" + 
					"}";
			String meta5 = "{\r\n" + 
					"    \"path\": \"/Applicazioni/AppOOP2/App/App/fisica2.jpeg\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": true\r\n" + 
					"}";
			String meta6 = "{\r\n" + 
					"    \"path\": \"/Applicazioni/AppOOP2/App/App/app.png\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": true\r\n" + 
					"}";
			String meta7 = "{\r\n" + 
					"    \"path\": \"/Applicazioni/AppOOP2/App/App/fotoposte.jpg\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": true\r\n" + 
					"}";
			String meta8 = "{\r\n" + 
					"    \"path\": \"/Applicazioni/AppOOP2/App/App/cervo.jpg\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": true\r\n" + 
					"}";
			
			List<String> list = new ArrayList<>(Arrays.asList(meta1,meta2,meta3,meta4,meta5,meta6,meta7,meta8));
			
			try {
				for (int i=0;i<list.size();i++) {
				HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
				openConnection.setRequestMethod("POST");
				openConnection.setRequestProperty("Authorization",
						"Bearer W5uaWGNcenwAAAAAAAAAATFdORMUJrzR-ejKmubYr5b24LEtFGkS_OinCWZToDWn");
				openConnection.setRequestProperty("Content-Type", "application/json");
				openConnection.setRequestProperty("Accept", "application/json");
				openConnection.setDoOutput(true);
				
				
					String jsonBody = list.get(i);
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
					inR.close();
					buf.close();
					in.close();
				} finally {
				}
				JSONObject json = (JSONObject) JSONValue.parseWithException(data);
				array.add(json);
				}
				return array;
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
					"    \"query\": \"2\"\r\n" + 
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

