package edu.njusoftware.dossiermanagement.service;

import edu.njusoftware.dossiermanagement.domain.CaseInfo;
import edu.njusoftware.dossiermanagement.domain.req.CaseQueryCondition;
import edu.njusoftware.dossiermanagement.domain.rsp.BaseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICaseService {
    CaseInfo getCaseInfo(String caseNum);

    List<CaseInfo> getAllCases();

    List<CaseInfo> getCasesByType(String type);

    boolean saveCase(CaseInfo caseInfo);

    BaseResponse removeCase(String caseNum);

    Page<CaseInfo> getCaseList(CaseQueryCondition caseQueryCondition);
}
