package cn.manualIntervention.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.httpUtil.HttpRestImpl;
import cn.manualIntervention.utils.WorkingStatusHelper;
import cn.manualIntervention.utils.bo.TotalCountVo;

@Controller
public class RepairAndJump {
	
	/**
	 * repairAndJump对应VMS的资源
	 * @param bizids
	 * @param cook
	 * @param pd
	 * @param key
	 */
	@RequestMapping(value = "/vms/repairAndJump", method = RequestMethod.POST )
    @ResponseBody
    public void vmsRepairAndJump(@RequestParam(value = "bizids")String bizids, 
    		@RequestParam(value = "cook")String cook, @RequestParam(value = "pd")int pd,
    		@RequestParam(value = "key")String key) {
		String[] bizid = bizids.split("\n");
		WorkingStatusHelper.init(key, bizid.length);
		for(String l : bizid) {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WorkingStatusHelper.addOne(key);
		}
		/*if (pdNUll(bizids) && pdNUll(pd) && pdNUll(key)) {
			return ;
		}
		String[] bizid = bizids.split("\n");
		for (String s: bizid) {
			if(s.length() != 32) {
				return;
			}
		}
		String urlrepair = "http://10.144.240.150:8080/bizTask/repair";
		String urljump = "http://10.144.240.150:8080/bizTask/jump";
		WorkingStatusHelper.init(key, bizid.length);
		 cn.httpUtil.dao.HttpRest hr = new HttpRestImpl();
		 Map<String, String> mapHeader = new HashMap<String, String>();
	        Map<String, String> mapBody = new HashMap<String, String>();
	        mapHeader.put("Accept","*\/*");
	        mapHeader.put("Accept-Encoding","gzip, deflate");
	        mapHeader.put("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
	        mapHeader.put("Connection","keep-alive");
	        mapHeader.put("Cookie",cook);
	        mapHeader.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:59.0) Gecko/20100101 Firefox/59.0");
	        mapHeader.put("X-Requested-With","XMLHttpRequest");
		 for(String l : bizid) {
			 mapBody.put("id", l);
			 if (pd == 0) {
				 hr.postParamAsMap(urlrepair, mapBody, mapHeader, (Map)null);
			 } else if (pd == 1) {
				 hr.postParamAsMap(urljump, mapBody, mapHeader, (Map)null);
			 }
			 try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WorkingStatusHelper.addOne(key);
		 }*/
    }
	@RequestMapping(value = "/hws/repairAndCancel", method = RequestMethod.POST )
    @ResponseBody
    public void hwsRepairAndcancel(@RequestParam(value = "bizids")String bizids, 
    		@RequestParam(value = "pd")int pd,@RequestParam(value = "key")String key) {
		String[] bizid = bizids.split("\n");
		/*for (String s: bizid) {
			if(s.length() != 32) {
				return;
			}
		}*/
		String urlrepair = "http://10.144.242.126/rest/resourceRoute/repair";
		String urlcancel = "http://10.144.242.126/rest/resourceRoute/cancelTask";
		 cn.httpUtil.dao.HttpRest hr = new HttpRestImpl();
		 WorkingStatusHelper.init(key, bizid.length);
		 Map<String, String> mapHeader = new HashMap<String, String>();
	        Map<String, String> mapBody = new HashMap<String, String>();
		 for(String l : bizid) {
			 mapBody.put("taskId", l);
			 if (pd == 0) {
				 hr.postParamAsMap(urlrepair, mapBody, mapHeader, (Map)null);
			 } else if (pd == 1) {
				 hr.postParamAsMap(urlcancel, mapBody, mapHeader, (Map)null);
			 }
			 try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WorkingStatusHelper.addOne(key);
		 }
    }
	@RequestMapping(value = "/getCont", method = RequestMethod.POST )
	@ResponseBody
    public TotalCountVo getWorkFinishedCount(@RequestParam(value = "key", required = true) String key){
        TotalCountVo res = WorkingStatusHelper.get (key);
        return res;
    }
	private Boolean pdNUll(Object o){
		if(o == null || o == "") {
			return false;
		}
		return true;
	}
	
}
