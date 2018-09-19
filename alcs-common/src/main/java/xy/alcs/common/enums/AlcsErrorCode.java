package xy.alcs.common.enums;


import xy.alcs.common.errcode.ErrorCode;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 11:46 2018-03-03
 */
public enum AlcsErrorCode implements ErrorCode {

    STUDENT_LOGIN("1", "学生登录"),
    RATER_LOGIN("2", "评委登录"),
    SUCCESS("0000", "成功"),
    SYSTEM_ERROR("0001", "系统错误"),
    PARAM_EXCEPTION("0002", "参数异常"),
    PARAM_ISNULL("0003", "参数为空"),
    DAO_EXCEPTION("0004", "DAO调用异常"),
    PARAM_SIZE_BIG("0005", "参数过多"),
    CONETST_NOT_EXIST("0006", "竞赛不存在"),
    USER_NOT_EXIST("0007", "用户不存在"),
    PASSWORD_ERROR("0008", "密错误"),
    NOT_LOGIN("0009", "未登录"),
    ENROLL_FAIL("0008", "报名失败"),
    IMAGE_UPLOAD_FAIL("0009", "图片上传失败"),
    FILE_UPLOAD_FAIL("0010", "文件上传失败"),
    IMAGE_FORMAT_ERROR("0011", "图片格式错误"),
    FILE_FORMAT_ERROR("0012", "文件格式错误"),
    SAVE_WORK_FAIL("0013", "保存失败"),
    SUBMIT_WORK_FAIL("0014", "提交失败"),
    USER_IS_EXIST("0015", "用户已存在"),
    SUPER_ADMIN("00016", "超级管理员"),
    ORDINARY_ADMIN("00017", "普通管理员"),
    LOGIN_FAIL("00018", "登录失败"),
    ENROLLD("00019", "重复报名"),
    IMPORT_ERROR("00020", "导入文件失败"),
    COLLEGE_NOT_EXIST("00021", "学院不存在"),
    MAJOR_NOT_EXIST("00022", "专业不存在"),
    CLA_NOT_EXIST("00023", "学院不存在"),
    EXCAL_IS_NULL("00024", "文件内容为空"),
    CANCEL_FAIL("00025", "取消失败"),
    TEAM_NOT_EXIT("00026","小组不存在"),
    NOT_TEAM_CAPTAIN("00027","不是组长")
    ,
    ;

    private String code;
    private String description;


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    AlcsErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
