//Caameron Nakasone CS202 Program 5  All_Lots.java August 28, 2017
//All lots file that contains the class that will handle a binary search tree of lots. This class will be used to hold all
//of the lots of cars that will be used for this specific application. Will be used to search for a comparison to the car
//that the user has selected to search for


package com.company;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//All_Lots class that will contain the binary search tree of lots that will be used for Program 5. Contains all the cars that
//can be searched for in Program 5. Implements methods to work on the BST such as add, search, display, etc.
class All_Lots {

    protected Lots root;                //Root of BST

    //Default Constructor
    public All_Lots()
    {
        this.root = null;
    }

    //Functions to work with Binary Search Tree of Car Lots

    //Function to add a Lot object to the binary search tree. Compares the names of the lots to determine the
    //place where the Lot will be added. returns root
    public Lots add(Lots toAdd)
    {

        //If root is null to begin with add the object passed in as the root of the tree and return it
        //else call the recursive add function
        if(root == null)
        {
            this.root = toAdd;
            return this.root;
        }
        return add(this.root, toAdd);
    }


    //Recursive function for add
    public Lots add(Lots root, Lots toAdd)
    {

        //If root is null then add the object in to that spot of the tree. Else use the compareLot function to
        //determine which way to traverse down the tree and then call the recursive add function
        if(root == null)
        {
            root = toAdd;
            return root;
        }

        //Using the compareLot Method determine which way to traverse down the tree to add
        if(root.compareLot(toAdd) <= 0)
            root.right = add(root.getRight(), toAdd);
        else
            root.left =  add(root.getLeft(), toAdd);
        return root;

    }


    //Function that will display the contents of the binary search tree in alphabetical order
    //Uses inorder traversal and returns a 1 if something is displayed and a 0 if the tree is empty
    public int displayTree()
    {

        System.out.println("All Cars available to search for:");

        //If root is null then the tree is empty so return 0. If it is not null call the recursive function
        if(this.root == null)
        {
            System.out.println("LOT EMPTY");
            return 0;
        }
        else
            return displayTree(this.root);
    }


    //Recursive function for displayTree
    public int displayTree(Lots root)
    {

        //If root is null then return.
        if(root == null)
            return 1;

        //Use in order traversal to display the tree in alphabetical order. Return a 1 after each node is displayed
        int ret = displayTree(root.getLeft());
        root.displayTree();
        ret += displayTree(root.getRight());
        return ret;
    }


    //Function to read in all cars from an external file. The function will first read in the name of a lot and create that lot
    //Then read in each car belonging to that lot adding it to that lots binary search tree. It will continue to do this until
    //the data file is empty. Returns a 1 if a car is read in or a 0 if data file is empty
    public void readInCars() throws IOException
    {
        String tempResponse;                        //String variable to read in contents from external file
        String split [];                            //Array of strings to hold for stringSplit method
        File inFile = new File("Cars.txt");
        Scanner inputFile = new Scanner(inFile);

        //The code will read in each line from the file. It will then split that entire String using the split function
        //using "," as the character to split it. The first string will be the name of the lot, and every string after
        //that will be a car to be added to that specific lot.
        while(inputFile.hasNextLine())
        {
            tempResponse = inputFile.nextLine();
            split = tempResponse.split(",");
            int sizeOfArray = split.length;
            //Create a new a lot with the first string in split, which should be the name of the lot and add it to the tree
            Lots tempLot = new Lots(split[0]);              //Temporary Lot that will be added to All_Lots object

            //For loop to go through the remaining Strings that were created using split. Each String will be split again
            //using the "|" character as the token to split. This will provide the name, price, and features of the car.
            //It will then be added used to create a new car object and add it to the lots search tree of cars
            for(int i = 1; i < sizeOfArray; ++i)
            {
                String splitCar [];                         //Array of strings to hold details of car from split function
                int num = 0;                                //Int to go through contents of splitCar
                splitCar = split[i].split("@");
                Car tempCar = new Car(splitCar[num], splitCar[++num]);
                tempCar.selectFeatures(splitCar[++num], splitCar[++num], splitCar[++num]);
                tempLot.add(tempCar);
            }

            this.add(tempLot);
        }
    }


    //Function to compare the car object passed in with every car in all of the lots in the binary search tree.
    //It will take in the Car object to search for, and return the car object that most matches with the car passed in
    public Car compareAll(Car toCompare, Car match)
    {

        //If root is null, then there is no cars to be compared too so return an empty car.
        if(this.root == null)
        {
            System.out.println("No Cars to be compared too");
            return match;
        }

        //Call the recursive function to traverse through every lot comparing the search car to each one
        return compareAll(toCompare, match, this.root);

    }


    //Recursive Function for compareAll. Goes through each Lot in the binary search tree calling the Lots compare function
    //with the car to search for. Returns the car that is matches the most.
    public Car compareAll(Car toCompare, Car match, Lots root)
    {

        //If root is null, return the car that is already a match
        if(root == null)
            return match;

        //Call the compare method from the Lots class.
        match = root.compare(toCompare, match);

        //Call the recursive function for each lot.
        match = compareAll(toCompare, match, root.getLeft());
        match = compareAll(toCompare, match, root.getRight());

        //Return the matched car
        return match;

    }

}

