package L1Q4_1349;

import java.util.Scanner;

public class L1Q4 {
    static int[] cc;
    static int[] rr;
    static int[][] match;
    static int instL;
    static long upper = 10000000000000000L;

    public static void main(String[] args) {
        read();
        if (can()) {
            System.out.println(search(upper));
        } else {
            System.out.println(-1);
        }
    }

    static long search(long max) {
        long min = 1;
        long mid = (max + min) / 2;
        while (min < max) {
            if (checkT(mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
            mid = (max + min) / 2;
        }
        return mid;
    }

    static boolean can() {
        //return checkTD(upper + instL) < checkTD(upper);
        return checkT(upper);
    }

    static long checkTD(long t) {
        long periods = t / (long) instL;
        long mod = t % instL;
        long[] now = new long[]{(long) rr[0], (long) rr[1]};
        now = add(now, new long[]{(long) match[instL - 1][0] * periods, (long) match[instL - 1][1] * periods});
        if (mod > 0) {
            now = add(now, match[(int) mod - 1]);
        }
        return dis(cc, now) - t;
    }

    static boolean checkT(long t) {
        return checkTD(t) <= 0;
    }

    static long dis(int[] cc, long[] rr) {
        return Math.abs(rr[0] - cc[0]) + Math.abs(rr[1] - cc[1]);
    }

    static void read() {
        Scanner sc = new Scanner(System.in);
        rr = new int[]{sc.nextInt(), sc.nextInt()};
        cc = new int[]{sc.nextInt(), sc.nextInt()};
        instL = sc.nextInt();
        match = new int[instL][];
        String seq = sc.next();
        int[] move = {0, 0};
        for (int i = 0; i < instL; i++) {
            rAct(move, seq.charAt(i));
            match[i] = new int[]{move[0], move[1]};
        }
        sc.close();
    }

    static long[] add(long[] before, long[] move) {
        return new long[]{before[0] + move[0], before[1] + move[1]};
    }

    static long[] add(long[] before, int[] move) {
        return new long[]{before[0] + (long) move[0], before[1] + (long) move[1]};
    }

    static void rAct(int[] co, char c) {
        switch (c) {
            case 'U':
                co[1] += 1;
                break;
            case 'D':
                co[1] -= 1;
                break;
            case 'L':
                co[0] -= 1;
                break;
            case 'R':
                co[0] += 1;
                break;
        }
    }
}
