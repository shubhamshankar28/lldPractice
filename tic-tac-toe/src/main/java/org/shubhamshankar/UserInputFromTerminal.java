package org.shubhamshankar;

import java.util.Scanner;

public class UserInputFromTerminal implements UserInput {


    @Override
    public InputCoordinate collectInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter x and y co-ordinates for the next move: ");
        String inputLine = scanner.nextLine();

        // Split the input line by space
        String[] parts = inputLine.split(" ");

        // Parse the two integers from the split parts
        int firstInteger = Integer.parseInt(parts[0]);
        int secondInteger = Integer.parseInt(parts[1]);


        return new InputCoordinate(firstInteger, secondInteger);
    }
}
