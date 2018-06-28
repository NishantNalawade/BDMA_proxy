package com.bdma.proxy.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bdma.proxy.exceptions.ProxyServicesException;
import com.bdma.proxy.model.OAuth2Details;
import com.bdma.proxy.utils.OAuthUtils;


@Service
public class DeviceService {

	private OAuth2Details oAuth2Details;
	public DeviceService() {
		oAuth2Details = OAuthUtils.getOAuth2Details();
	}

	public String getResponseFromCng(String url, HttpMethod httpMethod,
			String requestBody) throws ProxyServicesException {
		System.out.println("Inside getResponseFromCng");
		String respBody = null;
		try {
			String accessToken = OAuthUtils.getAccessToken(oAuth2Details);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Authorization", "Bearer " + accessToken);

			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			System.out.println("OAuth added headers");
			HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody, headers);
			System.out.println("Req entity" + requestEntity);
			RestTemplate rest = new RestTemplate(requestFactory);
			
			System.out.println("Rest entity" + rest);
			System.out.println(url);
			ResponseEntity<String> responseEntity =	rest.exchange(url, httpMethod, requestEntity, String.class);			
			
			System.out.println("Respbody" );
			System.out.println("before Respbody" );
			respBody = responseEntity.getBody();
			System.out.println(respBody);
			System.out.println("After Respbody and response entity" + responseEntity.getStatusCode() );
			if (responseEntity.getStatusCode() != HttpStatus.OK
					&& responseEntity.getStatusCode() != HttpStatus.ACCEPTED
					&& responseEntity.getStatusCode() != HttpStatus.CREATED) {
				throw new ProxyServicesException(respBody);
			}

		} catch (HttpClientErrorException e) {
			throw new ProxyServicesException(e.getMessage());

		} catch (Exception e) {
			throw new ProxyServicesException(e.getMessage());
		}
		return respBody;
	}

	public String status(HttpMethod httpMethod) throws JSONException {
		String output = null;
		String URL = "https://telcobigdata-test-margin-assurance-run-settings-definition.cfapps.sap.hana.ondemand.com/runsettingsdefinition/CalculationRuns?$orderby=CREATED_AT desc&$top=1";
//		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL)
		        // Add query parameter
		//		.queryParam("$orderby", "CREATED_AT desc")
//				.queryParam("order", "desc")
	//			.queryParam("$top", "1");
//				.queryParam("$format", "json");
		System.out.println(URL);
		try {
			String respBody = getResponseFromCng(URL, httpMethod, null);
			if (respBody != null) {
				System.out.println("Hello Inside status");
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(respBody);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Array Searching");
			JSONArray results = jsonObject.getJSONObject("d").getJSONArray("results");
			
			System.out.println(" After Results");
//			for ( int i = 0; i < results.length(); i++) {
//			int i = 0;
//				String category = new Category(results.getJSONObject(i).getString("STATUS"),
//						results.getJSONObject.getString("DESCRIPTION"));
				output = "The Name of the last run is " + results.getJSONObject(0).getString("DESCRIPTION_RUN");
				output = output + ". The Description of last run is " + results.getJSONObject(0).getString("DESCRIPTION_RUN").toLowerCase();
				output =  output + " and the status is " + results.getJSONObject(0).getString("STATUS").toLowerCase() +".";
				System.out.println(output);
				
//				categories.add(category);
				
//			}
		}
		} catch (ProxyServicesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	
	
	
	
	
	
	
	public String cluster_type(HttpMethod httpMethod) throws JSONException {
		String output = null;
		String URL = "https://telcobigdata-test-margin-assurance-run-settings-definition.cfapps.sap.hana.ondemand.com/runsettingsdefinition/CalculationRuns?$orderby=CREATED_AT desc&$top=1";
//		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL)
		        // Add query parameter
		//		.queryParam("$orderby", "CREATED_AT desc")
//				.queryParam("order", "desc")
	//			.queryParam("$top", "1");
//				.queryParam("$format", "json");
		System.out.println(URL);
		try {
			String respBody = getResponseFromCng(URL, httpMethod, null);
			if (respBody != null) {
				System.out.println("Inside status");
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(respBody);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Array Searching");
			JSONArray results = jsonObject.getJSONObject("d").getJSONArray("results");
			
			System.out.println(" After Results");
				output = "The Cluster type is " + results.getJSONObject(0).getString("CLUSTER_TYPE").toLowerCase();
				System.out.println(output);				
//				categories.add(category);
				
//			}
		}
		} catch (ProxyServicesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	
//	public List<Category> getCategories(String tenantId, HttpMethod httpMethod)
//			throws ProxyServicesException {
//		List<Category> categories = new ArrayList<>();
//		String categoryURL =
//				"https://sap-connected-goods-js-demo-cdemo.cfapps.eu10.hana.ondemand.com/sap/iot/scb/cng/metadata/services.xsodata/DeviceCategories";
//		try {
//			String respBody = getResponseFromCng(categoryURL, tenantId, httpMethod, null);
//
//			if (respBody != null) {
//				JSONObject jsonObject = new JSONObject(respBody);
//				JSONArray results = jsonObject.getJSONObject("d").getJSONArray("results");
//				for (int i = 0; i < results.length(); i++) {
//					Category category = new Category(results.getJSONObject(i).getString("ID"),
//							results.getJSONObject(i).getString("DESCRIPTION"));
//					categories.add(category);
//				}
//			}
//
//		} catch (Exception e) {
//			throw new ProxyServicesException(e.getMessage());
//		}
//		return categories;
//	}
//
//	public List<DeviceType> getDeviceTypes(String tenantId, String categoryId,
//			HttpMethod httpMethod) throws ProxyServicesException {
//		List<DeviceType> deviceTypes = new ArrayList<>();
//		String deviceTypeURL =
//				"https://sap-connected-goods-js-demo-cdemo.cfapps.eu10.hana.ondemand.com/sap/iot/scb/cng/metadata/services.xsodata/DeviceTypes?$filter=CATEGORY_ID eq '"
//						+ categoryId + "'";
//		try {
//			String respBody = getResponseFromCng(deviceTypeURL, tenantId, httpMethod, null);
//
//			if (respBody != null) {
//				JSONObject jsonObject = new JSONObject(respBody);
//				JSONArray results = jsonObject.getJSONObject("d").getJSONArray("results");
//				for (int i = 0; i < results.length(); i++) {
//					DeviceType deviceType =
//							new DeviceType(results.getJSONObject(i).getString("DEVICE_TYPE_GUID"),
//									results.getJSONObject(i).getString("EXT_DEVICE_TYPE_ID"),
//									results.getJSONObject(i).getString("DEVICE_TYPE_DESCRIPTION"));
//					deviceTypes.add(deviceType);
//				}
//			}
//
//		} catch (Exception e) {
//			throw new ProxyServicesException(e.getMessage());
//		}
//		return deviceTypes;
//	}
//
//	public List<DeviceProperty> getDeviceProperties(String tenantId, String deviceGUID,
//			HttpMethod httpMethod) throws ProxyServicesException {
//		List<DeviceProperty> deviceProperties = new ArrayList<>();
//		String deviceTypeURL =
//				"https://sap-connected-goods-js-demo-cdemo.cfapps.eu10.hana.ondemand.com/sap/iot/scb/cng/metadata/services.xsodata/DeviceProperties?$filter=DEVICE_TYPE_GUID eq '"
//						+ deviceGUID + "' and DATA_CATEGORY eq '1'";
//		try {
//			String respBody = getResponseFromCng(deviceTypeURL, tenantId, httpMethod, null);
//
//			if (respBody != null) {
//				JSONObject jsonObject = new JSONObject(respBody);
//				JSONArray results = jsonObject.getJSONObject("d").getJSONArray("results");
//				for (int i = 0; i < results.length(); i++) {
//					boolean isMandatory = false;
//					if (!(results.getJSONObject(i).get("MANDATORY")).toString().equals("null")) {
//						isMandatory =
//								("TRUE".equals(results.getJSONObject(i).getString("MANDATORY")))
//										? true : false;
//					}
//					DeviceProperty deviceProperty =
//							new DeviceProperty(results.getJSONObject(i).getString("EXT_FIELD_NAME"),
//									results.getJSONObject(i).getString("DATA_TYPE"), isMandatory);
//					deviceProperties.add(deviceProperty);
//				}
//			}
//
//		} catch (Exception e) {
//			throw new ProxyServicesException(e.getMessage());
//		}
//		return deviceProperties;
//	}
//
//
//	public String onboardDevice(String tenantId, Device device, HttpMethod httpMethod)
//			throws ProxyServicesException {
//		String createDeviceURL =
//				"https://sap-connected-goods-java-metadata-demo-cdemo.cfapps.eu10.hana.ondemand.com/devicemetadata/createdevice";
//		String respBody = "";
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//			String JsonStr = objectMapper.writeValueAsString(device);
//			respBody = getResponseFromCng(createDeviceURL, tenantId, httpMethod, JsonStr);
//
//		} catch (Exception e) {
//			throw new ProxyServicesException(e.getMessage());
//		}
//		return respBody;
//	}
//
//	public String offboardDevice(String tenantId, String deviceGuid, HttpMethod httpMethod)
//			throws ProxyServicesException {
//		String deleteDeviceURL =
//				"https://sap-connected-goods-java-metadata-demo-cdemo.cfapps.eu10.hana.ondemand.com/devicemetadata/DeviceTypes/deleteDevice/guid/"
//						+ deviceGuid;
//		String respBody = "";
//		try {
//
//			respBody = getResponseFromCng(deleteDeviceURL, tenantId, httpMethod, null);
//
//		} catch (Exception e) {
//			throw new ProxyServicesException(e.getMessage());
//		}
//		return respBody;
//	}

}
