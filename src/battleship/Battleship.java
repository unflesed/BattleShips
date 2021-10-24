package battleship;

import java.util.Scanner;

public class Battleship {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String exit, player1, player2;
        int[][] arr = new int[10][10];
        System.out.println("Игрок 1 введите ваше имя:");
        player1 = sc.next();
        System.out.println("Игрок 2 введите ваше имя:");
        player2 = sc.next();
        do {
            System.out.println("Для выхода из игры введите 'exit'");
            field();
            arr = fieldFilling(player1);
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[i][j]);
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
        System.out.println(name + " вводит координаты кораблей");
        System.out.println("Четырёхпалубный корабль:");
        shipCoord1 = sc.nextInt();
        shipCoord2 = sc.nextInt();
        arr[shipCoord1][shipCoord2] = 1;
        System.out.println("1. Расположить вертикально.");
        System.out.println("2. Раположить горизонтально.");
        position = sc.nextInt();
        if (position == 1) {
            for (int i = shipCoord1; i < shipCoord1 + 4 ; i++) {
                arr[i][shipCoord2] = 1;
            }
        }
        return arr;
    }

}
