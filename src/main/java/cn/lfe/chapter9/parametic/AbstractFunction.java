package cn.lfe.chapter9.parametic;

/**
 * @author chen yue
 * @date 2024-08-20 17:26:39
 */
public abstract class AbstractFunction implements ParametricFunction {
    private final int n;
    protected final double[] a;

    public AbstractFunction(int n) {
        this.n = n;
        this.a = new double[n];
    }

    @Override
    public int getNParams() {
        return n;
    }

    @Override
    public String getParamName(int i) {
        return Character.toString((char) (97 + i));
    }

    @Override
    public double getParam(int i) {
        return a[i];
    }

    @Override
    public void setParam(int i, double val) {
        a[i] = val;
    }
}
