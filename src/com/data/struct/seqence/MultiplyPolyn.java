package com.data.struct.seqence;

import java.util.LinkedList;
import java.util.List;

public class MultiplyPolyn {

    static class Polyn {
        private int xiShu;
        private int zhiShu;
        public int getXiShu() {
            return xiShu;
        }
        public void setXiShu(int xiShu) {
            this.xiShu = xiShu;
        }
        public int getZhiShu() {
            return zhiShu;
        }
        public void setZhiShu(int zhiShu) {
            this.zhiShu = zhiShu;
        }
        public Polyn(int xiShu, int zhiShu) {
            this.xiShu = xiShu;
            this.zhiShu = zhiShu;
        }

        @Override
        public String toString() {
            return xiShu + "x" + "^" + zhiShu;
        }
    }

    public static void main(String[] args) {
        // p = 2x + 5x^3 + 3x^5;
        // q = 3x^2 + x^3 + 5x^6 + 2x^8;
        // m = p + q;
        List<Polyn> p = new LinkedList<>();
        p.add(new Polyn(2, 1));
        p.add(new Polyn(5, 3));
        p.add(new Polyn(3, 5));

        List<Polyn> q = new LinkedList<>();
        q.add(new Polyn(3, 2));
        q.add(new Polyn(1, 3));
        q.add(new Polyn(5, 6));
        q.add(new Polyn(2, 8));

        multi(p, q);

        System.out.println(q);

    }

    public static List<Polyn> multi(List<Polyn> min, List<Polyn> max) {
        int size = min.size();
        int maxI = 0;
        for (int i = 0; i < size; i++) {
            if (min.get(i).getZhiShu() < max.get(maxI).getZhiShu()) {
                ((LinkedList<Polyn>) max).add(maxI, min.get(i));
            } else if (min.get(i).getZhiShu() == max.get(maxI).getZhiShu()) {
                int sum = min.get(i).getXiShu() + max.get(maxI).getXiShu();
                max.get(maxI).setXiShu(sum);
            } else {
                i --;
            }
            maxI ++;
        }
        min = null;
        return max;
    }

}
