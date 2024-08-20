package cn.lfe.chapter9.parametic;

/**
 * @author chen yue
 * @date 2024-08-20 17:23:27
 */
public interface ParametricFunction {

    /**
     * 返回参数数量
     */
    int getNParams();

    /**
     * 返回第i个参数的名字
     */
    String getParamName(int i);

    /**
     * 返回第i个参数的值
     */
    double getParam(int i);

    /**
     * 设置第i个参数的值
     */
    void setParam(int i, double val);

    /**
     * 返回给定x时这个函数的值
     */
    double eval(double x);
}
