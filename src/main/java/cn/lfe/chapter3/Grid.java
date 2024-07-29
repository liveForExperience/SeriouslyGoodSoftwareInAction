package cn.lfe.chapter3;

/**
 * @author chen yue
 * @date 2024-07-29 17:55:42
 */
public class Grid {
    private final int maxPower;
    private int residualPower;

    public Grid(int maxPower) {
        this.maxPower = maxPower;
        this.residualPower = maxPower;
    }

    public void addPower(int power) {
        if (residualPower + power < 0) {
            throw new IllegalArgumentException();
        }

        if (residualPower + power > maxPower) {
            throw new IllegalArgumentException();
        }

        residualPower += power;
    }

    public static void main(String[] args) {
        Appliance tv = new Appliance(150), radio = new Appliance(30);
        Grid grid = new Grid(3000);

        tv.plugInto(grid);
        radio.plugInto(grid);

        System.out.println(grid.residualPower);

        tv.on();
        System.out.println(grid.residualPower);

        radio.on();
        System.out.println(grid.residualPower);
    }
}
