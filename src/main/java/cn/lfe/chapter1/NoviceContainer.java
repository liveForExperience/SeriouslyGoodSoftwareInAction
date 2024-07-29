package cn.lfe.chapter1;

import cn.lfe.Container;

/**
 * @author chen yue
 * @date 2024-07-27 20:03:14
 */
public class NoviceContainer {

    NoviceContainer[] g;
    int n;
    double x;

    public NoviceContainer() {
        g = new NoviceContainer[1000];
        g[0] = this;
        n = 1;
        x = 0;
    }

    public double getAmount() {
        return x;
    }

    public void connectTo(NoviceContainer c) {
        double z = (x * n + c.x * c.n) / (n + c.n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c.n; j++) {
                g[i].g[n + j] = c.g[j];
                c.g[j].g[c.n + i] = g[i];
            }
        }

        n += c.n;
        for (int i = 0; i < n; i++) {
            if (g[i] == null) {
                break;
            }

            g[i].n = n;
            g[i].x = z;
        }
    }

    public void addWater(double x) {
        double y = x / n;
        for (int i = 0; i < n; i++) {
            g[i].x = g[i].x + y;
        }
    }
}
