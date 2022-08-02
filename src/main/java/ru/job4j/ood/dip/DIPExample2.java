package ru.job4j.ood.dip;

import java.util.Scanner;

/**
* This class is created to demonstrate a violation of DIP.
* @author Grossevich Lev
*/
public class DIPExample2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input first number from 0 to 9");
        int num1 = Integer.parseInt(input.nextLine());
        if (num1 < 0 || num1 > 9) {
            /**
             * The program depends on console output of exception info.
             * We should use Logger to have possibility to choose the kind of output we need.
             * Output to file for example.
             */
            System.out.println("Get error with " + num1);
            throw new IllegalArgumentException("Wrong input!");
        }
        int num2 = Integer.parseInt(input.nextLine());
        if (num1 < 0 || num1 > 9) {
            /**
             * The same as described above.
             */
            System.out.println("Get error with " + num2);
            throw new IllegalArgumentException("Wrong input!");
        }
        int sum = num1 + num2;
        System.out.println("First number + second number is " + sum);
    }
}
