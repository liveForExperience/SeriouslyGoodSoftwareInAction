package cn.lfe.chapter8;

/**
 * @author chen yue
 * @date 2024-08-19 17:11:57
 */
public class Time {
    private final int hours, minutes, seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Time addNoWrapping(Time delta) {
        int s = seconds, m = minutes, h = hours;
        s += delta.seconds;
        if (s > 59) {
            s -= 60;
            m++;
        }

        m += delta.minutes;
        if (m > 59) {
            m -= 60;
            h++;
        }

        h += delta.hours;
        if (h > 23) {
            h = 23;
            m = 59;
            s = 59;
        }
        return new Time(h, m, s);
    }
}
