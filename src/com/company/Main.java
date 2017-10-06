//Caameron Nakasone CS202 Program 5  Main.java August 28, 2017
//Main file that will contain the class that has the main method. This method will create a car object for the user to input
//their desired features and that car will be used to search for other cars similar to it. An All_lots object will also be created
//to store all the cars that can be searched for.

package com.company;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        String response;                     //Response variable

        //Create a car object that will be used to hold the users choices for features
        Car searchCar;
        searchCar = new Car();

        //Read in options for the three features from an external data file
        searchCar.readInOptions();

        //Ask user for basic information on the car
        System.out.println("Hello, this program will help you find a car to your liking. When prompted please enter in the specific");
        System.out.println("traits that you will want to search for in your car\n");
        searchCar.askCar();

        //Display all the features available for the user to choose from then ask the user to input their choices
        System.out.println();
        searchCar.displayAllFeatures();
        searchCar.chooseColor();
        searchCar.chooseFuel();
        searchCar.chooseSize();
        System.out.println();

        //Display the car that will be used to search for matches
        System.out.println("Here is the features you have chosen that we will use to search for a match");
        searchCar.displayCar();
        System.out.println();
        System.out.println("Based on your choices, this here is the car that we found to have the best match");


        //create an All_Lots object and read in all the cars
        Car matchCar = new Car();
        All_Lots cars = new All_Lots();
        cars.readInCars();

        //Call the compare function for the All Cars object, and pass catch the returned Car in matchCar.
        //Which will be the best match for the searchCar that the user entered
        matchCar = cars.compareAll(searchCar, matchCar);
        matchCar.displayCar();


        //Ask user if they want to see all of the selections available.
        System.out.println("Would you like to see all of the cars available? Y/N");
        response = input.nextLine();

        if(response.toUpperCase().equals("Y"))
            cars.displayTree();
        else
            System.out.println("Thank you for your time!");

    }
}
