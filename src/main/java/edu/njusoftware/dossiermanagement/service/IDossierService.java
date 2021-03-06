package edu.njusoftware.dossiermanagement.service;

import edu.njusoftware.dossiermanagement.domain.Dossier;
import edu.njusoftware.dossiermanagement.domain.DossierOperationRecord;
import edu.njusoftware.dossiermanagement.domain.rsp.CaseSearchResult;

import java.util.List;
import java.util.Map;

public interface IDossierService {

    Dossier getDossier(long id);

    List<Dossier> getDossiersByCaseNum(String caseNum);

    boolean saveDossier(Dossier dossier);

    List<DossierOperationRecord> getDossierOperationRecordsByDossierId(long dossierId);

    List<DossierOperationRecord> getDossierOperationRecordsByCaseNum(String caseNum);

    List<DossierOperationRecord> getDossierOperationRecordsByJobNum(String jobNum);

    boolean removeDossierById(long dossierId);

    Map<String, List<Dossier>> getDirectoryMap(String caseNum);

    List<String> getDirectoriesByCaseNum(String caseNum);

    void addDirectory(String caseNum, String directoryName);

    String getFilePathById(long dossierId);

    void processDossierContent(Dossier dossier);

    List<CaseSearchResult> caseSearch(String caseNum, String keyword);

    List<CaseSearchResult> search(long dossierId, String keyword);
}
