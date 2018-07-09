package com.bdma.proxy.controller;
//package com.iot.connectedgoods.proxy.controller;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdma.proxy.exceptions.ProxyServicesException;
import com.bdma.proxy.model.Category;
import com.bdma.proxy.model.Device;
import com.bdma.proxy.model.DeviceProperty;
import com.bdma.proxy.model.DeviceType;
import com.bdma.proxy.service.DeviceService;




@RestController
public class DeviceController {
	
	final static String logger_name = "sample";
	final static Logger LOGGER = LoggerFactory.getLogger(DeviceController.class); 
	
	@Autowired
	private DeviceService deviceService;

	@RequestMapping("/status")
	public String status() throws ProxyServicesException, JSONException{
		return deviceService.status(HttpMethod.GET);
	}
	
	@RequestMapping("/metric_insights")
	public String cluster_type() throws ProxyServicesException, JSONException{
		return deviceService.metric_insights(HttpMethod.GET);
	}
	@RequestMapping("/product")
	public String product() throws ProxyServicesException, JSONException{
		return deviceService.product(HttpMethod.GET);
	}
	@RequestMapping("/service")
	public String service() throws ProxyServicesException, JSONException{
		return deviceService.service(HttpMethod.GET);
	}
//	
//	@RequestMapping("/deviceTypes/{tenantId}/{categoryId}")
//	public List<DeviceType> getDeviceTypes(@PathVariable String tenantId,@PathVariable String categoryId) throws ProxyServicesException{
//		return deviceService.getDeviceTypes(tenantId,categoryId, HttpMethod.GET);
//	}
//	
//	@RequestMapping("/deviceProperties/{tenantId}/{deviceGUID}")
//	public List<DeviceProperty> getDeviceProperties(@PathVariable String tenantId,@PathVariable String deviceGUID) throws ProxyServicesException{
//		return deviceService.getDeviceProperties(tenantId,deviceGUID, HttpMethod.GET);
//	}
//	
//	@RequestMapping(method = RequestMethod.DELETE , value ="/deleteDevice/{tenantId}/{deviceGuid}")
//	public String offboardDevice(@PathVariable String tenantId,@PathVariable String deviceGuid) throws ProxyServicesException{
//		return deviceService.offboardDevice(tenantId, deviceGuid, HttpMethod.DELETE);
//	}
//	
//	@RequestMapping(method = RequestMethod.POST, value="/createDevice/{tenantId}", consumes = "application/json")
//	public String onboardDevice(@PathVariable String tenantId,@RequestBody Device device) throws ProxyServicesException{
//		return deviceService.onboardDevice(tenantId, device, HttpMethod.POST);
//	}
	
	
	
}
