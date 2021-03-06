package edu.njusoftware.dossiermanagement.service;

import edu.njusoftware.dossiermanagement.domain.DossierOperationRecord;
import edu.njusoftware.dossiermanagement.domain.UserOperationRecord;
import edu.njusoftware.dossiermanagement.repository.DossierOperationRecordRepository;
import edu.njusoftware.dossiermanagement.repository.UserOperationRecordRepository;
import edu.njusoftware.dossiermanagement.util.Constants;
import edu.njusoftware.dossiermanagement.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OperationRecordService {
    private static DossierOperationRecordRepository dossierOperationRecordRepository;

    private static UserOperationRecordRepository userOperationRecordRepository;

    @Autowired
    public OperationRecordService(DossierOperationRecordRepository dossierRecordRepository,
                                  UserOperationRecordRepository userRecordRepository) {
        dossierOperationRecordRepository = dossierRecordRepository;
        userOperationRecordRepository = userRecordRepository;
    }

    /**
     * 根据id查找卷宗操作记录
     * @param id
     */
    public DossierOperationRecord getDossierOperationRecordById(long id) {
        return dossierOperationRecordRepository.findById(id).get();
    }

    /**
     * 存储案件的操作记录
     * @param caseNum
     * @param operation
     */
    public void saveCaseOperationRecord(String caseNum, String operation) {
        DossierOperationRecord record = new DossierOperationRecord(SecurityUtils.getLoginUserName(), caseNum, operation, new Date());
        dossierOperationRecordRepository.save(record);
    }

    /**
     * 存储普通的卷宗操作记录
     * @param caseNum
     * @param dossierId
     * @param dossierName
     * @param operation
     */
    public void saveNormalDossierOperationRecord(String caseNum, long dossierId, String dossierName, String operation) {
        DossierOperationRecord record = new DossierOperationRecord(SecurityUtils.getLoginUserName(), caseNum, dossierId, dossierName, operation, new Date());
        dossierOperationRecordRepository.save(record);
    }

    /**
     * 存储修正卷宗文本内容的卷宗操作记录
     * @param caseNum
     * @param dossierId
     * @param dossierName
     * @param pageNum
     * @param before
     * @param after
     * @param locationInfo
     */
    public void saveContentModificationOperationRecord(String caseNum, long dossierId, String dossierName, int pageNum,
                                                       String before, String after, String locationInfo) {
        DossierOperationRecord record = new DossierOperationRecord(SecurityUtils.getLoginUserName(), caseNum, dossierId,
                dossierName, pageNum, Constants.OPERATION_MODIFY, new Date(), before, after, locationInfo, 2);
        dossierOperationRecordRepository.save(record);
    }

    /**
     * 存储系统识别卷宗文本内容的卷宗操作记录
     * @param caseNum
     * @param dossierId
     * @param dossierName
     * @param after
     */
    public void saveContentRecognitionOperationRecord(String caseNum, long dossierId, String dossierName, int pageNum, String after) {
        DossierOperationRecord record = new DossierOperationRecord("system", caseNum, dossierId, dossierName,
                pageNum, Constants.OPERATION_MODIFY, new Date(), null, after, 3);
        dossierOperationRecordRepository.save(record);
    }

    /**
     * 记录账号的添加和删除
     * @param operator
     * @param jobNum
     * @param operation
     */
    public void saveAccountOperation(String operator, String jobNum, String operation) {
        UserOperationRecord userOperationRecord = new UserOperationRecord(operator, jobNum, operation, new Date());
        userOperationRecordRepository.save(userOperationRecord);
    }

    /**
     * 记录用户密码和角色的修改
     * @param operator
     * @param jobNum
     * @param operation
     * @param field
     * @param before
     * @param after
     */
    public void saveUserModificationOperation(String operator, String jobNum, String operation,
                                                     String field, String before, String after) {
        UserOperationRecord userOperationRecord =
                new UserOperationRecord(operator, jobNum, operation, field, before, after, new Date());
        userOperationRecordRepository.save(userOperationRecord);
    }

    /**
     * 获取卷宗某部分的修改记录
     * @param dossierId
     * @param part
     */
    public List<DossierOperationRecord> getDossierContentPartHis(long dossierId, int part) {
        return dossierOperationRecordRepository.findAllByDossierIdAndPageNumAndOperation(dossierId,
                part, Constants.OPERATION_MODIFY, Sort.by(Sort.Direction.DESC, "operateTime"));
    }
}
