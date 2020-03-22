package com.jason.base.system.dictionary.controller;

import com.jason.base.common.Constants;
import com.jason.base.entity.SysDictData;
import com.jason.base.entity.SysDictType;
import com.jason.base.entity.SysManager;
import com.jason.base.system.dictionary.service.DictionaryService;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqGridUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 字典信息维护
 * @author jason558han
 * @date 2020/3/16 5:38 下午
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;
    //日志处理
    private static final Logger logger = LogManager.getLogger(DictionaryController.class);

    /**
     * 字典列表页
     * @return 页面路径
     */
    @RequestMapping("/dictIndex")
    public ModelAndView dictIndex() {
        return new ModelAndView("base/system/dictIndex");
    }

    /**
     * 字典类型列表加载
     * @param response 服务
     */
    @RequestMapping("/dictTypeIndexJson")
    public void dictTypeIndexJson(HttpServletResponse response) {
        GridPageBean gpb = JqGridUtil.getGridPageParams();
        List<SysDictType> dictTypeList = dictionaryService.getSysDictTypeListByGpb(gpb);
        JqGridUtil.toGridJson(dictTypeList,SysDictType.class,gpb,response);
    }

    /**
     * 字典数据列表加载
     * @param id 字典类型id
     * @param response 服务对象
     */
    @RequestMapping("/dictDataIndexJson")
    public void dictDataIndexJson(String id, HttpServletResponse response)  {
        GridPageBean gpb = JqGridUtil.getGridPageParams();
        List<SysDictData> dictDataList = dictionaryService.getSysDictDataListByIdGpb(id, gpb);
        JqGridUtil.toGridJson(dictDataList,SysDictData.class,gpb,response);
    }

    /**
     * 字典类型信息编辑操作
     * @param request 请求对象
     * @param session session对象
     * @return 页面路径
     */
    @RequestMapping("dictTypeEdit")
    public @ResponseBody String dictTypeEdit(HttpServletRequest request, HttpSession session) {
        Map<String, String[]> map = request.getParameterMap();
        String msg = dictionaryService.editSysDictTypeByMap(map);

        //日志处理
        SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
        logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"字典类型信息["+ JqGridConstants.EDIT_OPER_MAP.get(map.get("oper")[0])
                + "]方法操作。");
        return msg;
    }

    /**
     * 字典数据编辑操作
     * @param request 请求对象
     * @param session session对象
     * @return 请求返回情况
     */
    @RequestMapping("/dictDataEdit")
    public @ResponseBody String dictDataEdit(HttpServletRequest request, HttpSession session) {
        Map<String, String[]> map = request.getParameterMap();
        String msg = dictionaryService.editSysDictDataByMap(map);

        //日志处理
        SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
        logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"字典数据信息["+ JqGridConstants.EDIT_OPER_MAP.get(map.get("oper")[0])
                + "]方法操作。");
        return msg;
    }

    /**
     * 获得select标签数据链接
     * @param response 传输id、name数据
     */
    @RequestMapping("/dictDataUrlIdNamesByTypeId")
    public void dictDataUrlSelectIdNamesByTypeId(String typeId, HttpServletResponse response) throws IOException {
        String selectStr = dictionaryService.getDictDataSelectStrByTypeId(typeId);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(selectStr);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 获得select标签数据链接
     * @param response 传输code、name数据
     */
    @RequestMapping("/dictDataUrlCodeNamesByTypeCode")
    public void dictDataUrlSelectCodeNamesByTypeCode(String typeCode, HttpServletResponse response) throws IOException {
        String selectStr = dictionaryService.getDictDataSelectStrByTypeCode(typeCode);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(selectStr);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
