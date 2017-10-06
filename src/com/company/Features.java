//Caameron Nakasone CS202 Program 5  Car.java August 28, 2017
//Feature file. This will be the abstract base class for the features. The color, size, and fuel class will all be derived
//from this base class.


package com.company;

import com.sun.org.apache.xalan.internal.utils.FeatureManager;

//Feature class that will every car will have 3 of. This class will be an abstract base class which will just hold data that
//glues together the derived classes from it. Features class will have 3 classes derived from it which will include Color, Size
//and Fuel Class. Each feature will have a next pointer so that it can be used as a Linear Linked List
abstract class Features {

    //Data Members
    protected String featureName;               //Name of the feature
    protected int priority;                     //How important this specific feature is. 2 being the highest and 0 lowest
    protected Features next;                    //Next reference to make a LLL


    //Default constructor
    Features()
    {
        this.featureName = null;
        this.priority = 0;
        this.next = null;
    }

    //Abstract methods that will be implemented by all of the derived classes
    abstract public int display();
    abstract public int displayFeature();
    abstract public int displayChoice();
    abstract public String getName();
    abstract public int compareFeature(Car toCompare);

    //SetNext function to set the next feature to a desired object
    public void setNext(Features toAdd)
    {
        this.next = toAdd;
    }

    //GetNext function to get the reference that next is pointing to and return it
    public Features getNext()
    {
        return this.next;
    }


}
