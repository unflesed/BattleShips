package battleship;

import java.util.Scanner;

public class Battleship {
    static String player1 = "player1";
    static String player2 = "player2";
    static Scanner sc = new Scanner(System.in);
    static int[][] arr1 = new int[10][10];
    static int[][] arr2 = new int[10][10];
    public static void main(String[] args) {
        String exit;

        //System.out.println("Игрок 1 введите ваше имя:");
        //player1 = sc.next();
        //System.out.println("Игрок 2 введите ваше имя:");
        //player2 = sc.next();
        do {
            System.out.println("Для выхода из игры введите 'exit'");
            field(arr1);
            arr1 = fieldFilling(player1, arr1);
            exit = sc.next();
        } while (!exit.equalsIgnoreCase("exit"));
    }

    static void field(int[][] arr) {
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                if (arr[i][j] == 1) {
                    System.out.print(" \u25A0 ");
                }else {
                    System.out.print(" \u20DE ");
                }
            }
            System.out.println();
        }
    }
    //метод заполнения игрового поля кораблями
    static int[][] fieldFilling(String name, int[][] arr) {
        int shipCoord1, shipCoord2;
        int position;
        int deck = 4;
        System.out.println(name + " вводит координаты кораблей");
        do {
            for (int i = 0; i < 5 - deck; i++){
                System.out.println(deck + "-палубный корабль. Осталось разместить " + (5 - deck - i));
                System.out.println("Координата х:");
                shipCoord1 = sc.nextInt();
                System.out.println("Координата у:");
                shipCoord2 = sc.nextInt();
                if (deck != 1) {
                    System.out.println("1. Расположить вертикально.");
                    System.out.println("2. Расположить горизонтально.");
                    position = sc.nextInt();
                }else {
                    position = 1;
                }
                if (check(arr, position, deck, shipCoord2, shipCoord1)) {
                    arr[shipCoord2][shipCoord1] = 1;
                    for (int j = 0; j < deck; j++) {
                        if (position == 1) {
                            arr[shipCoord2 + j][shipCoord1] = 1;
                        } else {
                            arr[shipCoord2][shipCoord1 + j] = 1;
                        }
                    }
                    field(arr);
                } else {
                    System.out.println("Вы ввели неверные координаты. Повторите ввод.");
                    i--;
                }
            }
            deck--;
        } while (deck != 0);
        return arr;
    }
    //проверка корректности ввода координат кораблей
    static boolean check(int[][] arr, int position, int deck, int shipCoord1, int shipCoord2) {
        //для вертикальных
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
                } else if (shipCoord1 > 0 && shipCoord1 + deck < 9 && shipCoord2 == 0) {
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
                } else if (shipCoord1 > 0 && shipCoord1 + deck < 9 && shipCoord2 == 9) {
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
                }else if(shipCoord1 + deck - 1 > 9 || shipCoord2 > 9){
                    return false;
                }
            }
        //для горизонтальных
        }else if (position == 2) {
            for (int i = 0; i < deck; i++) {
                if (shipCoord1 == 0 && shipCoord2 == 0) {
                    if (arr[shipCoord1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                } else if (shipCoord1 == 0 && shipCoord2 + deck - 1 < 9 && shipCoord2 > 0) {
                    if (arr[shipCoord1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                } else if (shipCoord1 > 0 && shipCoord1 < 9 && shipCoord2 == 0) {
                    if (arr[shipCoord1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                } else if (shipCoord1 == 0 && shipCoord2 + deck - 1 == 9) {
                    if (arr[shipCoord1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                } else if (shipCoord1 > 0 && shipCoord1 < 9 && shipCoord2 + deck - 1 == 9) {
                    if (arr[shipCoord1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                } else if (shipCoord1 == 9 && shipCoord2 == 0) {
                    if (arr[shipCoord1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                } else if (shipCoord1 == 9 && shipCoord2 > 0 && shipCoord2 + deck - 1 < 9) {
                    if (arr[shipCoord1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                } else if(shipCoord1 == 9 && shipCoord2 + deck - 1 == 9){
                    if (arr[shipCoord1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                }else if (shipCoord1 > 0 && shipCoord1 < 9 && shipCoord2 < 9 && shipCoord2 > 0) {
                    if (shipCoord2 + i + 1 > 9
                            || arr[shipCoord1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i - 1] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i + 1] == 1
                            || arr[shipCoord1 - 1][shipCoord2 + i] == 1
                            || arr[shipCoord1 + 1][shipCoord2 + i] == 1) {
                        return false;
                    }
                }else if(shipCoord1 > 9 || shipCoord2 + deck - 1 > 9){
                    return false;
                }
            }
        }else if (arr[shipCoord1][shipCoord2] == 1) {
            return false;
        }
        return true;
    }
}

