package edu.njusoftware.dossiermanagement.controller;

import edu.njusoftware.dossiermanagement.domain.CaseInfo;
import edu.njusoftware.dossiermanagement.domain.Dossier;
import edu.njusoftware.dossiermanagement.service.ICaseService;
import edu.njusoftware.dossiermanagement.service.IDossierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 前端页面跳转控制器，初步计划用PageController来处理页面的跳转，其他Controller处理数据请求
 * 页面跳转不能使用 @RestController
 */
@Controller
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ICaseService caseService;

    @Autowired
    private IDossierService dossierService;

    /**
     * 主页
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String index(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<CaseInfo> caseList = caseService.getCaseList(pageNum, pageSize);
        model.addAttribute("caseList", caseList);
        return "index";
    }

    /**
     * 添加案件页面
     * @param model
     * @return
     */
    @RequestMapping("/addCase")
    public String addCase(Model model) {
        CaseInfo caseInfo = new CaseInfo();
        model.addAttribute( caseInfo);
        return "addCase";
    }

    /**
     * 用户个人主页
     * @param model
     * @param jobNum
     * @return
     */
    @RequestMapping("/user/{jobNum}")
    public String getUserMainPage(Model model, @PathVariable String jobNum) {
        return "userPage";
    }

    /**
     * 案件主页
     * @param model
     * @param caseNum
     * @return
     */
    @RequestMapping("/case/{caseNum}")
    public String getCaseMainPage(Model model, @PathVariable String caseNum) {
        model.addAttribute("caseInfo", caseService.getCaseInfo(caseNum));
        model.addAttribute("dossierList", dossierService.getDossiersByCaseNum(caseNum));
        model.addAttribute("directoryMap", dossierService.getDirectoryMap(caseNum));
        return "casePage";
    }
}
