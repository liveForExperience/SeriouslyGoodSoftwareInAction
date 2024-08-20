package cn.lfe.chapter9.parametic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-08-20 17:29:55
 */
public class ObservableFunction implements ParametricFunction {

    private final ParametricFunction f;
    private final List<ActionListener> listeners = new ArrayList<>();
    private final ActionEvent dummyEvent = new ActionEvent(this, ActionEvent.ACTION_FIRST, "update");

    public ObservableFunction(ParametricFunction f) {
        this.f = f;
    }

    @Override
    public int getNParams() {
        return f.getNParams();
    }

    @Override
    public String getParamName(int i) {
        return f.getParamName(i);
    }

    @Override
    public double getParam(int i) {
        return f.getParam(i);
    }

    @Override
    public void setParam(int i, double val) {
        f.setParam(i, val);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(dummyEvent);
        }
    }

    @Override
    public double eval(double x) {
        return f.eval(x);
    }
}
