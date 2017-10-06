//Caameron Nakasone CS202 Program 5  Car.java August 28, 2017
//This file contains the code for the  Size Class. Will have display functions for Program 4
//and comparisons functions in program 5

package com.company;


//Size class that is derived from the feature class. Describes how big the car is and will have functions to display and
//compare
class Size extends Features {


    //Constants to access the right list in functions
    final int COLOR = 0;
    final int SIZE = 1;
    final int FUEL = 2;

    protected String carSize;

    //Default Constructor that calls super classes constructor
    public Size()
    {
        super();
    }


    //Constructor that takes a String as an argument and sets that to the type of feature for this specific reference
    public Size(String size)
    {
        this.featureName = "Size of Car";
        this.carSize = size;
        this.priority = 1;
        this.next = null;
    }


    //Display Function to display what the feature is and the specific feature that is chosen
    @Override
    public int display() {
        System.out.println(this.featureName + ":" + this.carSize);
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
        System.out.print(this.carSize);
        return 1;
    }


    //Method that returns the size of the car for this specific feature
    @Override
    public String getName()
    {
        return carSize;
    }

    //Function to compare the Size of one car to another, returns a 1 if it is a match and 0 if it is not
    public int compareFeature(Car toCompare)
    {
        if(this.carSize.toUpperCase().compareTo(toCompare.featureSelected[SIZE].getName().toUpperCase()) == 0)
            return 1 + priority;
        else
            return 0;
    }


}
