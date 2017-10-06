//Caameron Nakasone CS202 Program 5  Lots.java August 28, 2017
//Lots file, that contains the a class that will handle a binary search tree of cars. This class will be used to hold
//all cars in a certain lot to be compared to the car that the user wants to search for.

package com.company;


//Lots class that will contain the name of the lot and a binary search tree of all the cars that are in that lot.
//Contains methods that will allow us to add, compare, and display the tree. In addition has a a right and left data
//member so that it can act as a node to for the All_lots class. The binary search tree of cars will be ordered by the
//name of the cars
class Lots {

    //Data Members
    protected String name;                  //Name of Lot
    protected Car root;                     //Root of the BST

    //Data members to work as a binary search tree
    protected Lots left;
    protected Lots right;


    //Default constructor
    public Lots()
    {
        this.name = null;
        this.root = null;
        this.left = null;
        this.right = null;
    }


    //Constructor that takes a string as an argument and sets the name of the lot to that string
    //Sets every other data member to null
    public Lots(String nameOfLot)
    {
        this.name = nameOfLot;
        this.root = null;
        this.left = null;
        this.right = null;
    }



    //Functions to make the car class also a root for a binary search tree
    //Sets the right child to the argument passed in and returns
    public void setRight(Lots toAdd)
    {
        this.right = toAdd;
    }


    //Sets the left child to the argument passed in and returns
    public void setLeft(Lots toAdd)
    {
        this.left = toAdd;
    }


    //Gets the contents of the left child and returns it
    public Lots getLeft()
    {
        return this.left;
    }


    //Gets the contents of the right child and returns it
    public Lots getRight()
    {
        return this.right;
    }


    //Function compare the name of the Lot to another Lot passed in. uses the String compareTo function and will return
    //a 0 if the lots names are equal, greater than 0 if the argument passed in is alphabetically greater or less than 0
    //if the argument passed in alphabetically lesser
    public int compareLot(Lots toCompare)
    {
        return this.name.toUpperCase().compareTo(toCompare.name.toUpperCase());
    }


    //Functions to work with Binary Search Tree of Cars.txt

    //Function to add a car object to the binary search tree. Compares the names of the cars to determine the
    //place where the car will be added. returns root
    public Car add(Car toAdd)
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
    public Car add(Car root, Car toAdd)
    {

        //If root is null then add the object in to that spot of the tree. Else use the compareCar function to
        //determine which way to traverse down the tree and then call the recursive add function
        if(root == null)
        {
            root = toAdd;
            return root;
        }

        //Use compareCarName method to determine which way to traverse down the tree and add the car
        if(root.compareCarName(toAdd) <= 0)
            root.right = add(root.getRight(), toAdd);
        else
            root.left =  add(root.getLeft(), toAdd);
        return root;

    }


    //Function that will display the contents of the binary search tree in alphabetical order
    //Uses inorder traversal and returns a 1 if something is displayed and a 0 if the tree is empty
    public int displayTree()
    {

        System.out.println("Lot Name:" + this.name);
        System.out.println();

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
    public int displayTree(Car root)
    {

        //If root is null then return.
        if(root == null)
            return 1;

        //Use in order traversal to display the tree in alphabetical order. Return a 1 after each node is displayed
        int ret = displayTree(root.getLeft());
        //Display All features of the car
        root.displayCar();
        System.out.println();
        ret += displayTree(root.getRight());
        return ret;
    }


    //Function that will take a Car object as an argument and compare it to each of the cars in this specific lot
    //Returns the car that has the most in common with the searchCar.
    public Car compare(Car toCompare, Car match)
    {

        //If root is null to begin with then this specific lot is empty, so return the match car that was passed in
        if(this.root == null)
            return match;

        return compare(toCompare, match, this.root);
    }


    //Recursive function for the compare method. Will traverse through the entire lot of cars comparing each one
    //Returns the car that has the most in common with the searchCar
    public Car compare(Car toCompare, Car match, Car root)
    {

        //If root is null, return the match car
        if(root == null)
            return match;

        //Call the compareCar Method from the car class
        if(root.compareCar(toCompare, root, match) > 0)
            match = root;

        //Recursive call for each of the cars in the lot
        match = compare(toCompare, match, root.getLeft());
        match = compare(toCompare, match, root.getRight());

        return match;
    }

}
