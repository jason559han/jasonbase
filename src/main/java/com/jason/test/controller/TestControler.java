package com.jason.test.controller;

import com.jason.base.common.utils.DateUtil;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqGridUtil;
import com.jason.test.entity.TestPeople;
import com.jason.test.service.TestPeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/testFirster")
public class TestControler {

	@Resource
	private TestPeopleService testPeopleService;

	@RequestMapping("/testIndex")
    public String index() {
        return "test/testIndex";
    }

	@RequestMapping("/testIndex2")
    public String index2() {
        return "test/testIndex2";
    }

	@RequestMapping("/testIndexJson")
	public void indexJson(HttpServletResponse response) throws IOException {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		List<TestPeople> list = testPeopleService.getTestPeopleListByGpb(gpb);
		this.toGridJson2(list,gpb,response);
	}

	@RequestMapping("/testIndexJson2")
	public void indexJson2(HttpServletResponse response) {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		List<TestPeople> list = testPeopleService.getTestPeopleListByGpb(gpb);
		JqGridUtil.toGridJson(list,TestPeople.class,gpb,response);
	}

	private void toGridJson2(List<TestPeople> list, GridPageBean gpb, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		StringBuilder builder = new StringBuilder("{");
		builder.append("\"total\":").append(Math.ceil((double)gpb.getTotal()/(double)gpb.getRows())).append(",");
		builder.append("\"page\":\"").append(gpb.getPage()).append("\",");
		builder.append("\"records\":\"").append(gpb.getTotal()).append("\",");
		builder.append("\"rows\":[");
		StringBuilder builders = new StringBuilder();
		for (TestPeople testPeople : list) {
			if (builders.length() > 0) {
				builders.append(",");
			}
			builders.append("{\"id\":\"").append(testPeople.getId()).append("\",");
			builders.append("\"cell\":[");
			builders.append("\"").append(testPeople.getId()).append("\",");
			builders.append("\"").append(testPeople.getName()).append("\",");
			builders.append("\"").append(testPeople.getSex()).append("\",");
			builders.append("\"").append(testPeople.getAge()).append("\",");
			builders.append("\"").append(testPeople.getPassword()!=null?testPeople.getPassword():"").append("\",");
			builders.append("\"").append(DateUtil.getDateStr(testPeople.getBirthday(), "yyyy-MM-dd")).append("\",");
			builders.append("\"").append(testPeople.getStatus()).append("\",");
			builders.append("\"").append(DateUtil.getDateStr(testPeople.getEditDate(), "yyyy-MM-dd")).append("\"");
			builders.append("]}");
		}
		builder.append(builders).append("]}");
		pw.write(builder.toString());
		pw.flush();
		pw.close();
	}

	@RequestMapping("/testEdit")
	public @ResponseBody String testEdit(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		return testPeopleService.editTestPeopleByMap(map);
	}
}
