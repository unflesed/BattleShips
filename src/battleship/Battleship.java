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
        } while (!exit.equalsIgnoreCase("exit"));
    }

    static void field() {
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(" \u20DE ");
            }
            System.out.println();
        }
    }

    static int[][] fieldFilling(String name) {
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
            System.out.println("1. Расположить вертикально.");
            System.out.println("2. Расположить горизонтально.");
            position = sc.nextInt();
            if (check(arr, position, deck, shipCoord1, shipCoord2)) {
                arr[shipCoord2][shipCoord1] = 1;
                for (int i = 0; i < deck; i++) {
                    if (position == 1) {
                        arr[shipCoord2 + i][shipCoord1] = 1;
                    } else {
                        arr[shipCoord2][shipCoord1 + i] = 1;
                    }
                }
                deck--;
            } else {
                System.out.println("Вы ввели неверные координаты. Повторите ввод.");
            }
        } while (deck != 0);
        return arr;
    }

    static boolean check(int[][] arr, int position, int deck, int shipCoord1, int shipCoord2) {
        if (position == 1) {
            for (int i = 0; i < deck; i++) {
                if (shipCoord1 == 0 && shipCoord2 == 0) {
                    if (arr[shipCoord1 + i][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2] == 1) {
                        return false;
                    }
                } else if (shipCoord1 == 0 && shipCoord2 < 9 && shipCoord2 > 0) {
                    if (arr[shipCoord1 + i][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2] == 1) {
                        return false;
                    }
                } else if (shipCoord1 > 0 && shipCoord2 == 0) {
                    if (arr[shipCoord1 + i][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2] == 1
                            || shipCoord1 + i + 1 > 9) {
                        return false;
                    }
                } else if (shipCoord1 == 0 && shipCoord2 == 9) {
                    if (arr[shipCoord1 + i][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2] == 1) {
                        return false;
                    }
                } else if (shipCoord1 > 0 && shipCoord2 == 9) {
                    if (shipCoord1 + i + 1 > 9
                            || arr[shipCoord1 + i][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2] == 1) {
                        return false;
                    }
                } else if (shipCoord1 + deck - 1 == 9 && shipCoord2 == 0) {
                    if (arr[shipCoord1 + i][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2] == 1) {
                        return false;
                    }
                } else if (shipCoord1 + deck - 1 == 9 && shipCoord2 > 0 && shipCoord2 < 9) {
                    if (arr[shipCoord1 + i][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2] == 1) {
                        return false;
                    }
                } else if(shipCoord1 + deck - 1 == 9 && shipCoord2 == 9){
                    if (arr[shipCoord1 + i][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2] == 1) {
                        return false;
                    }
                }else if (shipCoord1 > 0 && shipCoord1 < 9 && shipCoord2 < 9 && shipCoord2 > 0) {
                    if (shipCoord1 + i + 1 > 9
                            || arr[shipCoord1 + i][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2 - 1] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2 + 1] == 1
                            || arr[shipCoord1 + i - 1][shipCoord2] == 1
                            || arr[shipCoord1 + i + 1][shipCoord2] == 1) {
                        return false;
                    }
                }else if(shipCoord1 + deck - 1 > 9){
                    return false;
                }
            }
        }
        return true;
    }
}

