package com.grad.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grad.admin.service.OrganzService;
import com.grad.admin.vo.OrganzVo;
import com.grad.net.dto.JSONResult;


@Controller("orgnzAPIController")
@RequestMapping("/organz/api")
public class OrganzController {
	
	@Autowired
	OrganzService organzService;

	
	@ResponseBody
	@RequestMapping(value="/getlist")
	public JSONResult getList(@RequestParam("type") String type, @RequestParam("start") String start, @RequestParam("end") String end) {
		
		List<OrganzVo> list = organzService.getListByChar(type, start, end);
		return JSONResult.success(list);
		
	}
	
	
	}
	
	


