package com.aric.common.utils;

/**
 * Created by tom.lee on 2016/3/15.
 */
public class PrinterUtils<T> {

    /**
     * 打印InFo
     * @param str
     * @param <T>
     */
    public static <T> void printILog(T str) {

        System.out.println("．．．．．．．．．．．．．．．" + str + "．．．．．．．．．．．．．．．");
    }

    /**
     * 打印Error
     * @param str
     * @param <T>
     */
    public static <T> void printELog(T str) {

        System.err.println("．．．．．．．．．．．．．．．" + str + "．．．．．．．．．．．．．．．");
    }

    /**
     * 打印标志
     */
    public static void printBefore() {
        println();
        System.err.println("．．．．．．．．．．．．．．.WARNING．．．．．．．．．．．．．．．");
        System.err.println("．．．．．．．．．．．．．Console Begin．．．．．．．．．．．．．．．");
    }

    public static void printAfter(){
        println();
        System.err.println("．．．．．．．．．．．．．．.WARNING．．．．．．．．．．．．．．．");
        System.err.println("．．．．．．．．．．．．． Console Off．．．．．．．．．．．．．．．");
    }

    public static void printJun(){
        System.err.println("" +
                "▇▇▇▇▇▇▇▇▇▇▇▇\n" +
                "          ▇         ▇\n"+
                "    ▇▇▇▇▇▇▇▇▇▇▇▇\n" +
                "          ▇         ▇\n"+
                "▇▇▇▇▇▇▇▇▇▇▇▇\n" +
                "         ▇\n" +
                "        ▇ \n"+
                "      ▇▇▇▇▇▇▇▇▇\n" +
                "    ▇▇              ▇\n" +
                "  ▇  ▇              ▇\n" +
                "▇     ▇▇▇▇▇▇▇▇\n" +
                "\n" +
                "");
    }


   public   static  void  printLove(){
        System.err.println("\n" +
                " ╭⌒╮¤　　　　　　`  \n" +
                "╭╭ ⌒╮ ●╭○╮　  \n" +
                "╰ ----╯/█∨█\\ 　\n" +
                "~~~~~~~~~~∏~~∏~~~~~~~~~~~\n" +
                "﹎ ┈ ┈ .o┈ ﹎  ﹎.. ○\n" +
                "﹎┈﹎ ●  ○ .﹎ ﹎o▂▃▅▆\n" +
                "┈ ┈ /█\\/▓\\ ﹎ ┈ ﹎﹎ ┈ ﹎ \n" +
                "▅▆▇█████▇▆▅▃▂┈﹎\n" +
                " ");
    }


    static void Symbol(){
        for (int i = 1; i < 10; i++) {
            //K
            System.out.print("\t★");
            if (i == 5) {
                System.out.print(" ★");
            }
            for (int j = 1; j < 10 / 2; j++) {
                System.out.print("  ");
                if ((i + j == 5) || (i - j == 5)) {
                    System.out.print(" ★");
                }
            }
            //I
            System.out.print("\t ★");

            //N
            System.out.print("\t\t★");
            for (int m = 10; m > 10 - i; m--) {
                System.out.print(" ");
            }
            System.out.print("★");
            for (int m = 1; m < 10 - i; m++) {
                System.out.print(" ");
            }
            System.out.print(" ★\t\t");
            //G
            if (i == 1 || i == 10 - 1) {
                for (int n = 1; n <= 5; n++) {
                    if (n == 1) System.out.print(" ");
                    System.out.print(" ★");
                }
                System.out.print(" ");
            } else {
                System.out.print("★");
            }

            if (i == 5) {
                System.out.print("\t\t ★ ★ ★");
            }

            if ((i > 1 && i < 4) || (i > 5 && i < 9)) {
                for (int n = 1; n <= 6; n++) {
                    System.out.print("  ");
                }
                System.out.print(" ★");
            }
            System.out.print("\n");
        }
    }

    public static void println(){
        System.out.println();
    }



}

