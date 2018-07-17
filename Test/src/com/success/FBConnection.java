package com.success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class FBConnection {
	private static final String APP_ID = "1369087726569175";
	private static final String APP_SECRET = "b403de8e03c40fa99e07c27c0342fea1";

	private void connect() {
		StringBuilder data = new StringBuilder();
		URL url;
		try {
			url = new URL(getFBGraphUrl("moi"));
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			String line;

			if (conn.getResponseCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					data.append(line);
				}
			} else {
				System.out.println(" failed .." + conn.getResponseCode());
			}
			System.out.println(" data ->" + data.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + APP_ID + "&client_secret="
					+ APP_SECRET + "&code=" + code;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	private void connect1() {
		String g = "https://graph.facebook.com/me?EAATdLZAObxtcBAEr3nOpkO6nZAbXPZCMYY2PhFKNEvfo8jSqPqyinV3DKtCO8ULZCRUWZCwjatOVIQHMdRS54wa5aNjVjcJn625KV4IJZAvjIQioiujZC0peCIIceU0Fmr9byTluwZADsWR3T6fGmBBUhhWAp34FGvDGbeKCtl6IQLrUGWwoftA3TKgarydI1gEmHFfTCh26aYCzayM1XZBb6";
		URL u;
		try {
			u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			String graph = b.toString();
			System.out.println(graph);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new FBConnection().connect1();
	}

}
