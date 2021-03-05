package jap.exception;

import java.lang.Exception;

public class JapException extends Exception {
    public static String ERROR_CODE_0 = "调用异常，请检查目标Python文件是否正确。";
    public static String ERROR_CODE_1 = "返回值异常，请检查Python文件打印的二维矩阵形式";

    public JapException() {
        super();
    }

    public JapException(String msg) {
        super(msg);
    }
}
