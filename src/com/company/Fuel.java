//Caameron Nakasone CS202 Program 5  Car.java August 28, 2017
//This file contains the code for the Fuel Class. Will have display functions for Program 4
//and comparisons functions in program 5

package com.company;


//Fuel class that is derived from the feature class. Describes describes what fuel the car will take and will have methods to display and
//compare
class Fuel extends Features {


    //Constants to access the right list in functions
    final int COLOR = 0;
    final int SIZE = 1;
    final int FUEL = 2;

    protected  String carFuel;

    //Default Constructor that calls super classes constructor
    public Fuel()
    {
        super();
    }


    //Constructor that takes a String as an argument and sets that to the type of feature for this specific reference
    public Fuel (String fuel)
    {
        this.featureName = "Fuel type of Car";
        this.carFuel = fuel;
        this.priority = 2;
        this.next = null;
    }


    //Display Function to display what the feature is and the specific feature that is chosen
    @Override
    public int display() {
        System.out.println(this.featureName + ":" + this.carFuel);
        return 1;
    }

    //Function to display just the feature type
    public int displayFeature()
    {
        System.out.println("Feature:" + this.featureName);
        return 1;
    }

    //Function to display just the specific feature choice of the object
    public int displayChoice()
    {
        System.out.print(this.carFuel);
        return 1;
    }


    //Method that returns the specific fuel for this feature
    @Override
    public String getName()
    {
        return carFuel;
    }

    //Function to compare the Fuel Type of one car to another, returns a 1 if it is a match and 0 if it is not
    public int compareFeature(Car toCompare)
    {
        if(this.carFuel.toUpperCase().compareTo(toCompare.featureSelected[FUEL].getName().toUpperCase()) == 0)
            return 1 + priority;
        else
            return 0;
    }


}
