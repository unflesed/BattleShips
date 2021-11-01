import java.io.IOException;
import java.util.Scanner;

public class Battleship {
    static String player1 = "player1";
    static String player2 = "player2";
    static Scanner sc = new Scanner(System.in);
    static int[][] arr1 = new int[10][10];
    static int[][] arr2 = new int[10][10];
    static int[][] arrShots1 = new int[10][10];
    static int[][] arrShots2 = new int[10][10];
    static boolean exit = false;
    //внутренний класс для работы с игровым полем (прорисовка, заполнение кораблями, проверки)
    class Field{
        //прорисовка игрового поля
        static void fieldDraw(int[][] arr) {
            System.out.println("   0  1  2  3  4  5  6  7  8  9");
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < 10; j++) {
                    if (arr[i][j] == 1) {
                        System.out.print(" U ");
                    }else if (arr[i][j] == 0){
                        System.out.print(" _ ");
                    }else if (arr[i][j] == 2) {
                        System.out.print(" X ");
                    }else if (arr[i][j] == 3) {
                        System.out.print(" O ");
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
            fieldDraw(arr);
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
                        clearScreen();
                        fieldDraw(arr);
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
                    }else if(shipCoord1 + deck - 1 > 9 || shipCoord2 > 9 || shipCoord1 < 0 || shipCoord2 < 0){
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
                    }else if(shipCoord1 > 9 || shipCoord2 + deck - 1 > 9 || shipCoord1 < 0 || shipCoord2 < 0){
                        return false;
                    }
                }
                //проверка совпадения
            }else if (arr[shipCoord1][shipCoord2] == 1) {
                return false;
            }
            return true;
        }
    }
    public static void main(String[] args) {
        System.out.println("Игрок 1 введите ваше имя:");
        player1 = sc.next();
        System.out.println("Игрок 2 введите ваше имя:");
        player2 = sc.next();

        clearScreen();
        arr1 = Field.fieldFilling(player1, arr1);
        clearScreen();
        arr2 = Field.fieldFilling(player2, arr2);
        clearScreen();
        do {
            arrShots1 = shot(player1, arr2, arrShots1);
            if (exit) break;
            arrShots2 = shot(player2, arr1, arrShots2);
        }while(!exit);
    }

    //осуществление выстрелов
    static int[][] shot(String name, int[][] arrShips, int[][] arrShots){
        int shotCoord1, shotCoord2;
        boolean shot = true;
        Field.fieldDraw(arrShots);
        System.out.println(name + " осуществляет выстрел.");
        do {
            shotCoord1 = checkShot("x");
            shotCoord2 = checkShot("y");
            if (arrShips[shotCoord2][shotCoord1] != 1) {
                shot = false;
                arrShots[shotCoord2][shotCoord1] = 3;   //промах
                Field.fieldDraw(arrShots);
                clearScreen();
            } else {
                arrShots[shotCoord2][shotCoord1] = 2;   //попадание
                if (player1.equals(name)) {
                    arr2[shotCoord2][shotCoord1] = 2;
                }else {
                    arr1[shotCoord2][shotCoord1] = 2;
                }
                arrShips[shotCoord2][shotCoord1] = 2;
                clearScreen();
                Field.fieldDraw(arrShots);
                System.out.println(name + " попал!");
                if (isVictory(name, arrShips)) {
                    shot = false;
                }
            }
        }while(shot);
        return arrShots;
    }
    static int checkShot(String nameOfCoord){
        int coord;
        do {
            System.out.println("Координата "+ nameOfCoord +":");
            coord = sc.nextInt();
            if (coord >= 0 && coord <= 9) {
                break;
            }else{
                System.out.println("Вы ввели не верное значение. Повторите ввод.");
            }
        }while(true);
        return coord;
    }
    //проверка на победу
    static boolean isVictory(String name, int[][] arrShips){
        boolean check = true;
        for (int i = 0; i < arrShips.length; i++) {
            for (int j = 0; j < arrShips.length; j++) {
                if (arrShips[i][j] == 1) {
                    check = false;
                    break;
                }
            }
            if(!check) break;
        }
        if (check) {
            System.out.println("Игрок " + name + " ПОБЕДИЛ!!!");
            exit = true;
        }
        return check;
    }
    //очистка экрана (работает в командной строке)
    static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

