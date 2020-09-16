package univpm.progetto.Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import univpm.progetto.exception.ErroreFileException;

/**
 * Classe che contiene i metodi per il parsing del JSON, distinti in base alla richiesta 
 * fatta dal client
 * @author Proietti Marco
 * @author Traini Davide
 */
 
public class JSONparse {	
	
	/**
	 * Tale classe compie diverse chiamate all'indirizzo "https://api.dropboxapi.com/2/files/get_metadata"
	 * immagazzinando i vari JSON all'interno di un array
	 * 
	 * @return array è il JSONArray contenente tutti gli elementi sottoposti al parsing 
	 * @throws ErroreFileException  se il file è scritto in modo errato 
	 */
	
	public JSONArray returnMetadataJson() throws ErroreFileException {

		JSONArray array = new JSONArray();
		String url = "https://api.dropboxapi.com/2/files/get_metadata";
		JSONArray list = new JSONArray();
		// leggo da file i vari percorsi che rappresentano gli elementi all'interno di
		// DROPBOX
		File inFile = new File("JSONPath.txt");
		try {
			Scanner inp = new Scanner(inFile);
			String Json = "";
			while (inp.hasNextLine()) {
				Json += inp.nextLine();
			}
			JSONObject path = (JSONObject) JSONValue.parseWithException(Json);
			list = (JSONArray) path.get("JSON");

			for (int i = 0; i < list.size(); i++) {
				HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
				openConnection.setRequestMethod("POST");
				openConnection.setRequestProperty("Authorization",
						"Bearer W5uaWGNcenwAAAAAAAAAATFdORMUJrzR-ejKmubYr5b24LEtFGkS_OinCWZToDWn");
				openConnection.setRequestProperty("Content-Type", "application/json");
				openConnection.setRequestProperty("Accept", "application/json");
				openConnection.setDoOutput(true);

				JSONObject obj = (JSONObject) list.get(i);
				String jsonBody = "{\"path\":\"" + obj.get("path") + "\",\"include_media_info\":"
						+ obj.get("include_media_info") + "}";

				try (OutputStream os = openConnection.getOutputStream()) {
					byte[] input = jsonBody.getBytes("utf-8");
					os.write(input, 0, input.length);
				}
				// entro se non è possibile richiedere un output al protocollo
				catch (UnknownServiceException e) {
					e.printStackTrace();
				}

				InputStream in = openConnection.getInputStream();

				String data = "";
				String line = "";

				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
				}
				in.close();
				inp.close();

				JSONObject json = (JSONObject) JSONValue.parseWithException(data);
				array.add(json);
			}
			return array;
			// entro se ci sono stati problemi con flussoIO o problemi generici
		} catch (IOException | ParseException e1) {
			throw new ErroreFileException(
					"Inserire in modo corretto gli elementi nel file, in modo che sia presente un array di JSON ognuno costituito da 2 elementi: path (String) e include_media_info (Boolean)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Tale classe compie una chiamata all'indirizzo "https://api.dropboxapi.com/2/files/search_v2"
	 * 
	 * @return json è il JSONObject contenente tutti gli elementi sottoposti al parsing 
	 */
	
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
			String jsonBody = "{\r\n" + "    \"query\": \"1\"\r\n" + "}";

			try (OutputStream os = openConnection.getOutputStream()) {
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			// entro se non è possibile richiedere un output al protocollo
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
			// entro se ci sono stati problemi con flussoIO o problemi generici
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
