package edu.njusoftware.dossiermanagement.util;

public interface Constants {
    String DOSSIER_BASE_DIRECTORY = "C:/Users/Kratos/Desktop/dossier/";

    String ROLE_ADMIN = "admin";

    String ROLE_JUDGE = "judge";

    String ROLE_PARTNER = "partner";

    String ROLE_VISITOR = "visitor";

    String FILE_TYPE_PDF = "pdf";

    String FILE_TYPE_PIC = "image";

    String FILE_TYPE_VIDEO = "video";

    String FILE_TYPE_AUDIO = "audio";

    String CASE_XINGSHI = "刑事";

    String CASE_MINSHI = "民事";

    String CASE_XINGZHENG = "行政";

    String CASE_PEICHANG = "赔偿";

    String CASE_ZHIXING = "执行";

    String OPERATION_ADD = "添加";

    String OPERATION_REMOVE = "删除";

    String OPERATION_MODIFY = "修改";

    String OPERATION_VIEW = "查看";

    int CODE_SUCCESS = 0;

    String MSG_SUCCESS = "请求成功";

    int CODE_FAIL = 1;

    String MSG_FAIL = "请求失败";

    int CODE_RESOURCE_NOT_FOUND = 2;

    String MSG_RESOURCE_NOT_FOUND = "找不到请求的资源";

    String TIME_INFO_SEPARATOR = "_";
}
