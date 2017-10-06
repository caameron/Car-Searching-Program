//Caameron Nakasone CS202 Program 5  Car.java August 28, 2017
//Car class file, that will contain the all the details for the car object. It will contain an array of features where
//each feature will be a base class reference and have a list of options for that specific feature.

package com.company;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;


//Car Class that will contain all of the details about a car, such as name, price, and features.
//Has an array of features where the features will also be a class
//Also contains methods to work with the linear linked list of features
class Car {

    //Constants to access the right list in functions
    final int COLOR = 0;
    final int SIZE = 1;
    final int FUEL = 2;


    //Data members
    protected String name;                  //Name of car
    protected String price;                 //Price of car
    protected int matchScore;               //How good of a match the car is to the searchCar
    protected Features [] featureList;      //Array of the options of features available
    protected Features [] featureSelected;  //Array of features selected for that car
    protected Scanner input;

    //Data members for Binary Search Tree
    protected Car right;
    protected Car left;


    //Default Constructor
    public Car()
    {
        this.name = "N/A";
        this.price = "0";
        this.featureList = new Features[3];
        this.featureSelected = new Features[3];
        this.input = new Scanner(System.in);
        this.matchScore = 0;
    }


    //Constructor that will take in the name of the car and the price of the car and set the values to the
    //Corresponding variables
    public Car(String nameOfCar, String priceOfCar)
    {
        this.name = nameOfCar;
        this.price = priceOfCar;
        this.featureList = new Features[3];
        this.featureSelected = new Features[3];
        this.input = new Scanner(System.in);
        this.matchScore = 0;
    }


    //Function that will read in from an external data file the options that are available when searching for
    //a car. It will take each of these options are append it on to the corresponding list of features
    //Function will return a 1 if everything is read in correctly or 0 if an error occurred
    public int readInOptions() throws IOException
    {
        int type = 0;                       //Number to determine if it is reading in a color, size, or fuel(color = 0, size =1, fuel = 2)
        String tempResponse;                //String variable to read in contents of the external file
        String [] split;                    //Array of strings to hold the different strings after splitString is called
        File inFile = new File("Options.txt");
       // File inFile = new File("C:\\Users\\caamnaka\\Desktop\\Computer Science\\CS202\\Program # 4 and 5\\Program 4\\Program#4\\src\\com\\company\\Options.txt");
        Scanner inputFile = new Scanner(inFile);

        //The code will read in each line of the text file that has "," as a delimeter. It will then split that string using split function using ","
        //and depending on the type of feature that was read in, add it to the list of options for that feature
        while(inputFile.hasNextLine())
        {
            tempResponse = inputFile.nextLine();
            split = tempResponse.split(",");
            int sizeOfArray = split.length;


            //Check the type of the feature options that being read in and then call the corresponding constructor
            if(type == 0)
            {
                for(int i = 0; i < sizeOfArray; ++i)
                    addFeatureColor(new Color(split[i]));
            }
            else if(type == 1)
            {
                for(int i = 0; i < sizeOfArray; ++i)
                    addFeatureSize(new Size(split[i]));
            }
            else
            {
                for (int i = 0; i < sizeOfArray; ++i)
                    addFeatureFuel(new Fuel(split[i]));
            }
            ++type;
        }
        return 1;
    }


    //Function that will ask the user the name of the car and the price point they want to search for. Returns a 1
    public int askCar()
    {
        System.out.println("Enter in the price you want look for");
        this.price = input.nextLine();
        return 1;
    }


    //Display Function that will display all the details of the car and the features selected Returns a 1
    public int displayCar()
    {
        System.out.println("CAR:" + name + "   Price:" + price);

        if(featureSelected[COLOR] == null)
            return 0;
        featureSelected[COLOR].display();
        featureSelected[SIZE].display();
        featureSelected[FUEL].display();

        return 1;
    }


    //Function to display all the options for each Feature Returns a 1 for success
    public int displayAllFeatures()
    {
        System.out.println("Here is the list of features available to search for");

        //For loop to go through each feature in the array of features, calling their respective display function
        for(int i = 0; i < 3; ++i) {
            displayList(i);
            System.out.println("\n");
        }
        return 1;
    }


    //Function to display a specific Feature List. Will be called in the displayAllFeatures function
    //The list that will be displayed will be based on the number passed in
    //Returns a 0 if the list is empty or greater than 0 if successfull
    public int displayList(int num)
    {

        //If that features head reference is null return 0. Else call the recursive displayList function with the head reference
        if(featureList[num] == null)
            return 0;
        featureList[num].displayFeature();
        return displayList(featureList[num]);

    }

    //Recursive function for displayList function Returns 1 when it has reached the end of the list for success
    public int displayList(Features head)
    {
        //If the head.next reference is null, we are at the end of the list so display the last feature and return a 1
        //Else display the current feature and call recursive function
        if(head.next == null) {
            head.displayChoice();
            return 1;
        }
        head.displayChoice();
        System.out.print(", ");
        return displayList(head.next);

    }


    //Function to add an option to the Color Feature List
    public Features addFeatureColor(Features toAdd)
    {
        //Add at the beginning of the list if it is empty to begin with. Else call the recursive function
        //to add at the end of the list
        if(featureList[COLOR] == null) {
            featureList[COLOR] = toAdd;
            return featureList[COLOR];
        }
        else
            return addFeatureColor(featureList[COLOR], toAdd);
    }


    //Recursive Function to add an option to the Color Feature List
    public Features addFeatureColor(Features head, Features toAdd)
    {
        //Once we have reached the end of the list, add the feature on to the next reference. Else call recursive function with head.next
        if(head.getNext() == null) {
            head.setNext(toAdd);
            return head;
        }
        else
            return addFeatureColor(head.getNext(), toAdd);
    }


    //Function to add an option to the Size Feature List
    public Features addFeatureSize(Features toAdd)
    {

        //Add at the beginning of the list if it is empty to begin with. Else call the recursive function
        //to add at the end of the list
        if(featureList[SIZE] == null) {
            featureList[SIZE] = toAdd;
            return featureList[SIZE];
        }
        else
            return addFeatureSize(featureList[SIZE], toAdd);
    }


    //Recursive Function to add on option the Size Feature List
    public Features addFeatureSize(Features head, Features toAdd)
    {

        //Once we have reached the end of the list, add the feature on to the next reference. Else call recursive function with head.next
        if(head.getNext() == null) {
            head.setNext(toAdd);
            return head;
        }
        else
            return addFeatureSize(head.getNext(), toAdd);
    }


    //Function to add an option to the Fuel Feature List
    public Features addFeatureFuel(Features toAdd)
    {

        //Add at the beginning of the list if it is empty to begin with. Else call the recursive function
        //to add at the end of the list
        if(featureList[FUEL] == null) {
            featureList[FUEL] = toAdd;
            return featureList[FUEL];
        }
        else
            return addFeatureFuel(featureList[FUEL], toAdd);
    }


    //Recursive Function to add an option to the Fuel Feature List
    public Features addFeatureFuel(Features head, Features toAdd)
    {

        //Once we have reached the end of the list, add the feature on to the next reference. Else call recursive function with head.next
        if(head.getNext() == null) {
            head.setNext(toAdd);
            return head;
        }
        else
            return addFeatureFuel(head.getNext(), toAdd);
    }


    //Function to add a color choice to the selected features for the car
    public int chooseColor()
    {
        //Prompt user for their choice for that specific feature and then create an object of that feature using their input
        //and add it onto the array of features
        String response;
        System.out.println("Based on the choices given please enter in your desired color for your car");
        response = input.nextLine();

        featureSelected[COLOR] = new Color(response);
        return 1;
    }

    //Function to add a size choice to the selected features for the car
    public int chooseSize()
    {
        //Prompt user for their choice for that specific feature and then create an object of that feature using their input
        //and add it onto the array of features
        String response;
        System.out.println("Based on the choices given please enter in your desired size for your car");
        response = input.nextLine();

        featureSelected[SIZE] = new Size(response);
        return 1;
    }

    //Function to add a fuel choice to the selected features for the car
    public int chooseFuel()
    {
        //Prompt user for their choice for that specific feature and then create an object of that feature using their input
        //and add it onto the array of features
        String response;
        System.out.println("Based on the choices given please enter in your desired fuel type for your car");
        response = input.nextLine();

        featureSelected[FUEL] = new Fuel(response);
        return 1;
    }


    //Function to add a features to all features of the car when given 3 Strings. Returns a 1 if successful and 0 if not
    public int selectFeatures(String color, String size, String fuel)
    {
        featureSelected[COLOR] = new Color(color);
        featureSelected[SIZE] = new Size(size);
        featureSelected[FUEL] = new Fuel(fuel);
        return 1;
    }


    //Function to compare a car object to another car based on its name. If the car passed in as an argument is
    //alphabetically greater than the current car it will return greater than 0. If it is alphabetically before it will return
    //less than 0. If it is equal it will return a 0. Uses the String compareTo function
    public int compareCarName(Car toCompare)
    {
        return this.name.toUpperCase().compareTo(toCompare.name.toUpperCase());
    }


    //Function to compare the current car object with another based on its features and price. Compares all three features
    //Using their own comparison methods and also compares the price of the car. If a match or partial match is found it
    //will be added to the list of matches that is passed in else if there is no match then nothing is added. returns
    //List of car objects. Returns a 1 if this is a better match than the previous one or 0 if not
    //Will also take into consideration the priority of the feature, If it has a higher priority then it will be
    //worth more
    public int compareCar(Car toCompare, Car root, Car match)
    {
        //Int data memeber that keeps track of the amount of matches made. 0 means there was no match. Anything above 0 means
        //that there was at least one similarity. The higher the number the better the match
        int goodMatch = 0;

        //Compare the prices of the two cars, if its within 100000 of the price the user made, add 1 to goodMatch
        int comparePrice = Integer.parseInt(toCompare.price);
        int rootPrice = Integer.parseInt(root.price);
        if(rootPrice >= comparePrice - 10000 && rootPrice <= comparePrice+10000)
            goodMatch+=1;

        //Compare all the features of the root car to toCompare car adding the return value to goodMatch. These methods return integers based on if the features were
        //a match or not.
        goodMatch += toCompare.featureSelected[COLOR].compareFeature(root);
        goodMatch += toCompare.featureSelected[SIZE].compareFeature(root);
        goodMatch += toCompare.featureSelected[FUEL].compareFeature(root);

        //Compare the root matchScore with the car toCompare. If the root car has a higher Score then return a 1, if not
        //return 0
        if(match.matchScore < goodMatch) {
            root.matchScore = goodMatch;
            return 1;
        }
        else
            return 0;
    }



    //Functions to make the car class also a root for a binary search tree

    //Sets the right child to the argument passed in and returns
    public void setRight(Car toAdd)
    {
        this.right = toAdd;
    }


    //Sets the left child to the argument passed in and returns
    public void setLeft(Car toAdd)
    {
        this.left = toAdd;
    }


    //Gets the contents of the left child and returns it
    public Car getLeft()
    {
        return this.left;
    }


    //Gets the contents of the right child and returns it
    public Car getRight()
    {
        return this.right;
    }
}
