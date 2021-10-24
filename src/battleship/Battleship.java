package battleship;

import java.util.Scanner;

public class Battleship {
    static String player1 = "player1";
    static String player2 = "player2";
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String exit;
        int[][] arr1 = new int[10][10];
        int[][] arr2 = new int[10][10];
        //System.out.println("Игрок 1 введите ваше имя:");
        //player1 = sc.next();
        //System.out.println("Игрок 2 введите ваше имя:");
        //player2 = sc.next();
        do {
            System.out.println("Для выхода из игры введите 'exit'");
            field();
            arr1 = fieldFilling(player1);
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1.length; j++) {
                    System.out.print(arr1[i][j]);
                }
                System.out.println();
            }
            exit = sc.next();
        }while(!exit.equalsIgnoreCase("exit"));
    }
    static void field(){
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(" \u20DE ");
            }
            System.out.println();
        }
    }
    static int[][] fieldFilling(String name){
        int[][] arr = new int[10][10];
        int shipCoord1, shipCoord2;
        int position;
        int deck = 4;
        System.out.println(name + " вводит координаты кораблей");
        do {
            System.out.println(deck + "-палубный корабль:");
            System.out.println("Координата х:");
            shipCoord1 = sc.nextInt();
            System.out.println("Координата у:");
            shipCoord2 = sc.nextInt();
            arr[shipCoord1][shipCoord2] = 1;
            System.out.println("1. Расположить вертикально.");
            System.out.println("2. Раположить горизонтально.");
            position = sc.nextInt();
            for (int i = 0; i < deck; i++) {
                if (position == 1) {
                    arr[shipCoord1 + i][shipCoord2] = 1;
                } else {
                    arr[shipCoord1][shipCoord2 + i] = 1;
                }
            }
            deck--;
        }while(deck !=0);
        return arr;
    }
    static void surroundShip(int[][] arr){
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] != 1 && i + 1 < arr.length) {

                }
            }
        }
    }
}
