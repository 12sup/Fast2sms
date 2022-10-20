package com.te.fast2.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class sendSms {

	public static void sendSms(String message, String number) throws IOException {
		// System.out.println(message);
		// System.out.println(number);

		try {

			String apiKey = "pul8K31rZhsoC96ncgf5Bze4G2IFWqjMyRAbD0ViwUYdtXQNkvorGIn9MVj8FZyt5JQdXKkizPECOfqg";
			String senderId = "FSTSMS";
			message = URLEncoder.encode(message, "UTF-8");
			System.out.println(message);
			String langauage = "English";
			String route = "dlt";
			String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&sender_id=" + senderId
					+ "&message=" + message + "&langauage=" + langauage + "&route=" + route + "&number=" + number;
			// sending get request using java
			URL url = new URL(myUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setRequestProperty("cache-control", "no-cache");
			System.out.println("wait.............");
			int responseCode = connection.getResponseCode();
			System.out.println(responseCode);
			StringBuffer buffer = new StringBuffer();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while (true) {
				String line = bufferedReader.readLine();
				if (line == null) {
					break;
				}
				buffer.append(line);
				System.out.println(buffer);
			}
			System.out.println(url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(sendSms.class, args);
		sendSms.sendSms("this is my first sms through fast2sms:" + new Date().toLocaleString(), "6261458904");
	}

}
