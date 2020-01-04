package edu.njusoftware.dossiermanagement.mapper;

import edu.njusoftware.dossiermanagement.domain.OperationRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DossierMapper {
    List<OperationRecord> findRecordsByCaseNum(String caseNum);

    List<String> findDirectoriesByCaseNum(String caseNum);

    int addDirectory(@Param("caseNum") String caseNum, @Param("directoryName") String directoryName);

    String getFilePathById(long dossierId);
}
