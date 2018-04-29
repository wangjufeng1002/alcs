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
    IMAGE_UPLOAD_FAIL("0009","图片上传失败"),
    FILE_UPLOAD_FAIL("0010","文件上传失败"),
    IMAGE_FORMAT_ERROR("0011","图片格式错误"),
    FILE_FORMAT_ERROR("0012","文件格式错误"),

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
