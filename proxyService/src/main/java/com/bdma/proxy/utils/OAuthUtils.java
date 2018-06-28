package com.bdma.proxy.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.bdma.proxy.model.OAuth2Details;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class OAuthUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OAuthUtils.class);

	public static OAuth2Details createOAuthDetails(Properties config) {
		OAuth2Details oauthDetails = new OAuth2Details();
		oauthDetails.setAccessToken((String) config
				.get(OAuthConstants.ACCESS_TOKEN));
		oauthDetails.setRefreshToken((String) config
				.get(OAuthConstants.REFRESH_TOKEN));
		oauthDetails.setGrantType((String) config
				.get(OAuthConstants.GRANT_TYPE));
		oauthDetails.setClientId((String) config.get(OAuthConstants.CLIENT_ID));
		oauthDetails.setClientSecret((String) config
				.get(OAuthConstants.CLIENT_SECRET));
		oauthDetails.setScope((String) config.get(OAuthConstants.SCOPE));
		oauthDetails.setAuthenticationServerUrl((String) config
				.get(OAuthConstants.AUTHENTICATION_SERVER_URL));
		oauthDetails.setUsername((String) config.get(OAuthConstants.USERNAME));
		oauthDetails.setPassword((String) config.get(OAuthConstants.PASSWORD));
		oauthDetails.setResourceServerUrl((String) config.get(OAuthConstants.RESOURCE_SERVER_URL));
		
		if(!isValid(oauthDetails.getResourceServerUrl())){
			System.out.println("Resource server url is null. Will assume request is for generating Access token");
			oauthDetails.setAccessTokenRequest(true);
		}

		return oauthDetails;
	}

	public static Properties getClientConfigProps(String path) {
		InputStream is = OAuthUtils.class.getClassLoader().getResourceAsStream(
				path);
		Properties config = new Properties();
		try {
			config.load(is);
		} catch (IOException e) {
			System.out.println("Could not load properties from " + path);
			e.printStackTrace();
			return null;
		}
		return config;
	}

	public static OAuth2Details getOAuth2Details() {
		Properties config = OAuthUtils.getClientConfigProps(OAuthConstants.CONFIG_FILE_PATH);

		OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);
		if (!OAuthUtils.isValidInput(oauthDetails)) {
			LOGGER.info("Please provide valid config properties to continue.");
			return null;
		}
		return oauthDetails;
	}
	
	public static String getAccessToken(OAuth2Details oauthDetails) {
		System.out.println("Inside AccessToken");
//		String authURL = "https://" + tenantId + ".authentication.eu10.hana.ondemand.com/oauth/token";
//		HttpPost post = new HttpPost(authURL);
//		String clientId = oauthDetails.getClientId();
//		String clientSecret = oauthDetails.getClientSecret();
//		String scope = oauthDetails.getScope();
//
//		List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
//		parametersBody.add(new BasicNameValuePair(OAuthConstants.GRANT_TYPE,
//				oauthDetails.getGrantType()));
//		
//		parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_ID,
//					clientId));
//		
//		parametersBody.add(new BasicNameValuePair(
//					OAuthConstants.CLIENT_SECRET, clientSecret));
//		
//		if (isValid(scope)) {
//			parametersBody.add(new BasicNameValuePair(OAuthConstants.SCOPE,
//					scope));
//		}
//
//		DefaultHttpClient client = new DefaultHttpClient();
//		HttpResponse response = null;
//		String accessToken = null;
//		try {
//			post.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));
//
//			response = client.execute(post);
//			int code = response.getStatusLine().getStatusCode();
//			if (code == OAuthConstants.HTTP_UNAUTHORIZED) {
//				System.out
//						.println("Authorization server expects Basic authentication");
//				// Add Basic Authorization header
//				post.addHeader(
//						OAuthConstants.AUTHORIZATION,
//						getBasicAuthorizationHeader(oauthDetails.getClientId(),
//								oauthDetails.getClientSecret()));
//				System.out.println("Retry with client credentials");
//				post.releaseConnection();
//				response = client.execute(post);
//				code = response.getStatusLine().getStatusCode();
//				if (code == 401 || code == 403) {
//					System.out.println("Could not authenticate using client credentials.");
//					throw new RuntimeException(
//								"Could not retrieve access token for client: "
//										+ oauthDetails.getClientId());
//					
//				}
//
//			}
//			Map<String, String> map = handleResponse(response);
//			accessToken = map.get(OAuthConstants.ACCESS_TOKEN);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		return accessToken;
		return "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImtleS1pZC0xIn0.eyJqdGkiOiI4ZjQ4NTQyOTNjNGE0OTA2YjJhMTJjMTZkMDk3ZjUyMCIsImV4dF9hdHRyIjp7ImVuaGFuY2VyIjoiWFNVQUEiLCJ6ZG4iOiJ0ZWxjb2JpZ2RhdGEifSwic3ViIjoiZWVjZDVkNTUtMDdlMC00NTNiLWJhZWEtMjBmOTg5OGExZTgwIiwic2NvcGUiOlsiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkNIQU5HRV9TTSIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5ESVNQTEFZX1JFIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkRJU1BMQVlfQU0iLCJvcGVuaWQiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuQ0hBTkdFX0lDQyIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5ESVNQTEFZX1FMIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkNIQU5HRV9SRSIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5DSEFOR0VfQU0iLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuRElTUExBWV9ETSIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5DSEFOR0VfUUwiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuRElTUExBWV9GVEQiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuRElTUExBWV9CMkJFIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkRJU1BMQVlfSUNDIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkRJU1BMQVlfU00iLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuQ0hBTkdFX1BDIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkNIQU5HRV9CU0UiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuQ0hBTkdFX0NDQkEiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuRElTUExBWV9VQV9DRiIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5ESVNQTEFZX0JTRSIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5ESVNQTEFZX0ZDIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkRJU1BMQVlfQ0NCQSIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5DSEFOR0VfQ0NTIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkRJU1BMQVlfTU0iLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuQ0hBTkdFX0ZDIiwidWFhLnVzZXIiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuRElTUExBWV9SU0QiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuQ0hBTkdFX0IyQkUiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuQ0hBTkdFX01NIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkRJU1BMQVlfUEMiLCJUZWxjb0JpZ0RhdGEtVEVTVC11aSF0OTYuRElTUExBWV9HUyIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5DSEFOR0VfRlREIiwiVGVsY29CaWdEYXRhLVRFU1QtdWkhdDk2LkNIQU5HRV9ETSIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5ESVNQTEFZX0NDUyIsIlRlbGNvQmlnRGF0YS1URVNULXVpIXQ5Ni5DSEFOR0VfUlNEIl0sImNsaWVudF9pZCI6InNiLVRlbGNvQmlnRGF0YS1URVNULXVpIXQ5NiIsImNpZCI6InNiLVRlbGNvQmlnRGF0YS1URVNULXVpIXQ5NiIsImF6cCI6InNiLVRlbGNvQmlnRGF0YS1URVNULXVpIXQ5NiIsImdyYW50X3R5cGUiOiJ1cm46aWV0ZjpwYXJhbXM6b2F1dGg6Z3JhbnQtdHlwZTpzYW1sMi1iZWFyZXIiLCJ1c2VyX2lkIjoiZWVjZDVkNTUtMDdlMC00NTNiLWJhZWEtMjBmOTg5OGExZTgwIiwib3JpZ2luIjoieHN1YWEtbW9uaXRvcmluZy1pZHAiLCJ1c2VyX25hbWUiOiJuaXNoYW50Lm5hbGF3YWRlQHNhcC5jb20iLCJlbWFpbCI6Im5pc2hhbnQubmFsYXdhZGVAc2FwLmNvbSIsImdpdmVuX25hbWUiOiJuaXNoYW50Lm5hbGF3YWRlIiwiZmFtaWx5X25hbWUiOiJzYXAuY29tIiwicmV2X3NpZyI6IjdiZTAxMWJmIiwiaWF0IjoxNTMwMTYyMjcyLCJleHAiOjE1MzAyMDU0NzIsImlzcyI6Imh0dHA6Ly90ZWxjb2JpZ2RhdGEubG9jYWxob3N0OjgwODAvdWFhL29hdXRoL3Rva2VuIiwiemlkIjoidGVsY29iaWdkYXRhIiwieHMudXNlci5hdHRyaWJ1dGVzIjp7InNpdGVHcm91cFJvbGVzIjpbIkJJR19EQVRBX01BUkdJTl9BU1NVUkFOQ0UiLCJVU0VSX0FTU0lTVEFOQ0UiXX0sImF1ZCI6W119.xaqgliuVN34F6j45Pl6xoGXVXZ9bgWsv75RRDZxaps1mb3DWGQ1fhviFktlNHQw6lNHHRctdATkUIChwQXtpBQTUf3QU8Ae5jPesYrbmW0bHr8hDTe7l3K5GWhlJRjCS7JNKq8UpZNYV9jjteFQdqNVNwtQC65cLC8bZM49yQWCIe08zp9-5dJwlEJV6qhvhOuCEoG1tfh1S82cBxFUJRAH8HPJf_tSeoZvduW6ufRTwWH6JJm1rEZPzqi-kNIfjvdGB2gOUoaPXsPimilOVoGgdgzwrxS6v2Ipbbl2EcC0AdYi4OtOv_WePMC1woqjb66Da4204SCGljLV6VI6WHA";
	}

	public static Map<String, String> handleResponse(HttpResponse response) {
		
		//temp logs
		
		
		
		String contentType = OAuthConstants.JSON_CONTENT;
		if (response.getEntity().getContentType() != null) {
			contentType = response.getEntity().getContentType().getValue();
		}
		if (contentType.contains(OAuthConstants.JSON_CONTENT)) {
			return handleJsonResponse(response);
		} else if (contentType.contains(OAuthConstants.URL_ENCODED_CONTENT)) {
			return handleURLEncodedResponse(response);
		} else if (contentType.contains(OAuthConstants.XML_CONTENT)) {
			return handleXMLResponse(response);
		} else {
			// Unsupported Content type
			throw new RuntimeException(
					"Cannot handle "
							+ contentType
							+ " content type. Supported content types include JSON, XML and URLEncoded");
		}

	}

	public static Map<String, String> handleJsonResponse(HttpResponse response) {
		
		Map<String, String> oauthLoginResponse = null;
		try {
			JsonElement je = new JsonParser().parse(EntityUtils.toString(response.getEntity()));

		    String value = je.getAsJsonObject().get("access_token").getAsString();
		    //LOGGER.error("access_token is : " + value);
		    oauthLoginResponse = new HashMap<>();
		    oauthLoginResponse.put("access_token", value);
		    //System.out.println(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (RuntimeException e) {
			System.out.println("Could not parse JSON response");
			throw e;
		}
		System.out.println();
		System.out.println("********** Response Received **********");
		return oauthLoginResponse;
	}

	public static Map<String, String> handleURLEncodedResponse(HttpResponse response) {
		Map<String, Charset> map = Charset.availableCharsets();
		Map<String, String> oauthResponse = new HashMap<String, String>();
		Set<Map.Entry<String, Charset>> set = map.entrySet();
		Charset charset = null;
		HttpEntity entity = response.getEntity();

		System.out.println();
		System.out.println("********** Response Received **********");

		for (Map.Entry<String, Charset> entry : set) {
			System.out.println(String.format("  %s = %s", entry.getKey(),
					entry.getValue()));
			if (entry.getKey().equalsIgnoreCase(HTTP.UTF_8)) {
				charset = entry.getValue();
			}
		}

		try {
			List<NameValuePair> list = URLEncodedUtils.parse(
					EntityUtils.toString(entity), Charset.forName(HTTP.UTF_8));
			for (NameValuePair pair : list) {
				System.out.println(String.format("  %s = %s", pair.getName(),
						pair.getValue()));
				oauthResponse.put(pair.getName(), pair.getValue());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not parse URLEncoded Response");
		}

		return oauthResponse;
	}

	public static Map<String, String> handleXMLResponse(HttpResponse response) {
		Map<String, String> oauthResponse = new HashMap<String, String>();
		try {

			String xmlString = EntityUtils.toString(response.getEntity());
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			InputSource inStream = new InputSource();
			inStream.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(inStream);

			System.out.println("********** Response Receieved **********");
			parseXMLDoc(null, doc, oauthResponse);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Exception occurred while parsing XML response");
		}
		return oauthResponse;
	}

	public static void parseXMLDoc(Element element, Document doc,
			Map<String, String> oauthResponse) {
		NodeList child = null;
		if (element == null) {
			child = doc.getChildNodes();

		} else {
			child = element.getChildNodes();
		}
		for (int j = 0; j < child.getLength(); j++) {
			if (child.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				org.w3c.dom.Element childElement = (org.w3c.dom.Element) child
						.item(j);
				if (childElement.hasChildNodes()) {
					System.out.println(childElement.getTagName() + " : "
							+ childElement.getTextContent());
					oauthResponse.put(childElement.getTagName(),
							childElement.getTextContent());
					parseXMLDoc(childElement, null, oauthResponse);
				}

			}
		}
	}

	public static String getAuthorizationHeaderForAccessToken(String accessToken) {
		return OAuthConstants.BEARER + " " + accessToken;
	}

	public static String getBasicAuthorizationHeader(String username,
			String password) {
		return OAuthConstants.BASIC + " "
				+ encodeCredentials(username, password);
	}

	public static String encodeCredentials(String username, String password) {
		String cred = username + ":" + password;
		String encodedValue = null;
		byte[] encodedBytes = Base64.encodeBase64(cred.getBytes());
		encodedValue = new String(encodedBytes);
		System.out.println("encodedBytes " + new String(encodedBytes));

		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		System.out.println("decodedBytes " + new String(decodedBytes));

		return encodedValue;

	}
	
	public static boolean isValidInput(OAuth2Details input){
		
		
		
		if(input == null){
			return false;
		}
		
		String grantType = input.getGrantType();
		
		if(!isValid(grantType)){
			System.out.println("Please provide valid value for grant_type");
			return false;
		}
		
		if(!isValid(input.getAuthenticationServerUrl())){
			System.out.println("Please provide valid value for authentication server url");
			return false;
		}
		
		if(grantType.equals(OAuthConstants.GRANT_TYPE_PASSWORD)){
			if(!isValid(input.getUsername()) || !isValid(input.getPassword())){
				System.out.println("Please provide valid username and password for password grant_type");
				return false;
			}
		}
		
		if(grantType.equals(OAuthConstants.GRANT_TYPE_CLIENT_CREDENTIALS)){
			if(!isValid(input.getClientId()) || !isValid(input.getClientSecret())){
				System.out.println("Please provide valid client_id and client_secret for client_credentials grant_type");
				return false;
			}
		}
		
		System.out.println("Validated Input");
		return true;
		
	}

	public static boolean isValid(String str) {
		return (str != null && str.trim().length() > 0);
	}

}