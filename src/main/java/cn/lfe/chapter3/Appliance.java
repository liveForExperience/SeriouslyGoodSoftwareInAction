package cn.lfe.chapter3;

/**
 * @author chen yue
 * @date 2024-07-29 18:00:03
 */
public class Appliance {
    private final int powerAbsorbed;
    private Grid grid;
    private boolean isOn;

    public Appliance(int powerAbsorbed) {
        this.powerAbsorbed = powerAbsorbed;
    }

    public void plugInto(Grid grid) {
        this.grid = grid;
    }

    public void on() {
        this.isOn = true;
        this.grid.addPower(-powerAbsorbed);
    }
}
