package univpm.progetto.elaborazione;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.progetto.Json.JSONparse;
import univpm.progetto.exception.FailDimException;
import univpm.progetto.exception.FormatoNonTrovatoException;

public class Searchper {

	public JSONArray Dimensione(Long in) {

	//	if (in == null || !(in instanceof Long) || in == 0) {
	//		FailDimException e = new FailDimException("inserire un intero come body");
	//	}
		
		JSONArray finale = new JSONArray();
		JSONObject meta = new JSONObject();
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		Long size = (long) 0;
		JSONparse json2 = new JSONparse();
		JSONObject obj2 = json2.returnSearchJson();

		JSONArray array = (JSONArray) obj2.get("matches");

		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			metadata1 = (JSONObject) meta.get("metadata");
			metadata2 = (JSONObject) metadata1.get("metadata");
			size = (Long) metadata2.get("size");

			if (size == null) {
				size = (long) 0;
			}

			if ((long) size == (long) in) {
				finale.add(meta);
			}
		}

		return finale;

	}
	
	public Boolean FiltroDimensione(Long in, Long size, char c) {
			
			if (size == null) {
				size = (long) 0;
			}
			
			if ((long) size < (long) in && c=='<') {
				return true;
			}

			if ((long) size == (long) in && c=='='){
				return true; 			
			}
			
			if ((long) size > (long) in && c=='>') {
				return true;
			}
		

		return false;
		
	}

	public JSONArray TipoDiFile(String tipo, String inp, char c) {
		
		Long in = (long) 0;
		try { 
		 in = Long.parseLong(inp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	
		
		JSONArray finale = new JSONArray();

		if (tipo.contentEquals("jpg") || tipo.contentEquals("png") || tipo.contentEquals("tiff")) {

			JSONObject meta = new JSONObject();
			JSONObject metadata1 = new JSONObject();
			JSONObject metadata2 = new JSONObject();
			String nome = "";
			Long dimensione = (long) 0;
			JSONparse json2 = new JSONparse();
			JSONObject obj2 = json2.returnSearchJson();

			if (tipo.contentEquals("jpg"))
				tipo = ".jpg";
			if (tipo.contentEquals("png"))
				tipo = ".png";
			if (tipo.contentEquals("tiff"))
				tipo = ".tiff";

			JSONArray array = (JSONArray) obj2.get("matches");

			for (int i = 0; i < array.size(); i++) {
				meta = (JSONObject) array.get(i);
				metadata1 = (JSONObject) meta.get("metadata");
				metadata2 = (JSONObject) metadata1.get("metadata");
				nome = (String) metadata2.get("name");
				dimensione = (Long) metadata2.get("size");

				int lunghezza = nome.length();
				int lungh = tipo.length();

				int j = lunghezza - 1;
				int k = lungh - 1;
				Boolean flag = true;
				Boolean dim = FiltroDimensione(in,dimensione,c);
				
				while (k >= 0 && flag) {

					flag = false;

					if (tipo.charAt(k) == nome.charAt(j)) {
						flag = true;
						j--;
						k--;
					}
				}
				
				if (flag == true && dim==true) {

					finale.add(meta);

				}

			}

		} else {
			FormatoNonTrovatoException e = new FormatoNonTrovatoException("inserire un formato tipo jpg,png o tiff");
			e.printStackTrace();
			}

		return finale;

	}

	public JSONArray NomeFile(String body) {

		String nomeinput = body.toLowerCase();

		JSONArray finale = new JSONArray();

		JSONObject meta = new JSONObject();
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		String nome = "";
		String nomeJ = "";
		JSONparse json2 = new JSONparse();
		JSONObject obj2 = json2.returnSearchJson();

		JSONArray array = (JSONArray) obj2.get("matches");

		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			metadata1 = (JSONObject) meta.get("metadata");
			metadata2 = (JSONObject) metadata1.get("metadata");
			nome = (String) metadata2.get("name");

			nomeJ = nome.toLowerCase();

			Boolean flag = true;
			int k = nomeinput.length();
			int j = nome.length();

			if (k > j)
				flag = false;
			int n = 0;

			while (flag && n < k) {

				flag = false;

				if (nomeinput.charAt(n) == nomeJ.charAt(n)) {
					flag = true;
					n++;
				}

			}

			if (flag == true) {
				finale.add(meta);

			}

		}

		return finale;
	}

	public JSONArray Data(List<String> body) {
		
		if (body.size()>2) return null;
		
		
		
		String data1 = body.get(0);
		String data2 = body.get(1);
		String st1 = "";
		String st2 = "";
		String st3 = "";
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String stri1 = "";
		String stri2 = "";
		String stri3 = "";
		int anno1;
		int anno2;
		int anno3;
		int mese1;	
		int mese2;
		int mese3;
		int giorno1;
		int giorno2;
		int giorno3;
		
		
		for (int i=0;i<4;i++) {
			st1 += data1.charAt(i);
			st2 += data2.charAt(i);
		}
		
		for (int i=5;i<7;i++) {
			str1 += data1.charAt(i);
			str2 += data2.charAt(i);
		}
		
		for (int i=8;i<10;i++) {
			stri1 += data1.charAt(i);
			stri2 += data2.charAt(i);
		}
		
		try {
		anno1 = Integer.parseInt(st1);
		anno2 = Integer.parseInt(st2);
		mese1 = Integer.parseInt(str1);
		mese2 = Integer.parseInt(str2);
		giorno1 = Integer.parseInt(stri1);
		giorno2 = Integer.parseInt(stri2);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		
		JSONArray finale = new JSONArray();

		JSONObject meta = new JSONObject();
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		String mod = "";
		JSONparse json2 = new JSONparse();
		JSONObject obj2 = json2.returnSearchJson();

		JSONArray array = (JSONArray) obj2.get("matches");
		
		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			metadata1 = (JSONObject) meta.get("metadata");
			metadata2 = (JSONObject) metadata1.get("metadata");
			mod = (String) metadata2.get("client_modified");
			
			if (mod != null) {
			
			for (int j=0;j<4;j++) {
				st3 += mod.charAt(i);
			}
			
			for (int j=5;j<7;j++) {
				str3 += mod.charAt(i);
			}
			
			for (int j=8;j<10;j++) {
				stri3 += mod.charAt(i);
			}
		
			try {
				anno3 = Integer.parseInt(st3);
				mese3 = Integer.parseInt(str3);
				giorno3 = Integer.parseInt(stri3);
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
					return null;
				}
			 
			Boolean cont1 = false;
			Boolean cont2 = false;
			Boolean cont3 = false;
			
			if (anno3<anno1 && anno3<anno2) {
				cont1 = true;
			}
		
			if (mese3<mese1 && mese3<mese2) {
				cont2 = true;
			}
			
			if (giorno3<giorno1 && giorno3<giorno2) {
				cont3 = true;
			}
			
			if (cont1 && cont2 && cont3) finale.add(meta);
		}
		}
				
				
		return finale;
	}
	}

