package com.hc.trr.platform.aid.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.workSpace.utils.JsonUtils;

import com.hc.trr.platform.aid.entity.Aid;
import com.hc.trr.platform.aid.service.AidService;


@Controller
@RequestMapping("/Aid")
public class AidAction{

	@Resource(name = "aidService")
	private AidService aidService;

	@RequestMapping(value="index" , produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public void index(@RequestParam Map<String, String> params, HttpServletRequest request,
			HttpServletResponse response) {
	}
	
	/**
	 * 查询所有，不带分页
	 * @param params
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="doSelect" , produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String doSelect(@RequestParam Map<String, String> params, HttpServletRequest request,
			HttpServletResponse response) {
		List<Map<String,String>> reList = null;
		/**
		 * 请先校验传入的参数
		 */
		try {
			Aid Aid = new Aid();
			reList = aidService.select(params);//此查询不传参数会显示所有的数据，请慎用
			return JsonUtils.genUpdateDataReturnJsonStr(true, "查询成功", reList);
		} catch (Exception x) {
			return JsonUtils.genUpdateDataReturnJsonStr(false, "操作由于异常失败。", reList);
		}
	}

}
