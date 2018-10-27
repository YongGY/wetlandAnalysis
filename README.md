# Wetland Analysis

You have a farm of 400m by 600m where coordinates of the field are from (0, 0) to (399, 599). A portion of the farm is wetland, and all the wetland is in the form of rectangles. Due to these rectangles of wetland, the remaining area of arable land is in no particular shape. An area of arable land is defined as the largest area of land that is not covered by any of the rectangles of wetland.

Read input from STDIN. Print output to STDOUT.

#### Input
You are given a set of rectangles that contain the wetland. These rectangles are defined in a string, which consists of four integers separated by single spaces, with no additional spaces in the string. The first two integers are the coordinates of the bottom left corner in the given rectangle, and the last two integers are the
coordinates of the top right corner.

#### Output
Output all the arable land area in square meters, sorted from smallest area to greatest, separated by a space.

#### Example I/O
 | Input | Output           |          
 | ------------- |:-------------:|      
 | {“0 292 399 307”} | 116800  116800 |             
 | {“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”} | 22816 192608       |  

#### Deployment
Project can be run in any Java 1.8 compatible IDE.  Download zip file or clone onto local machine, open in IDE and run. 


#### Testing
TestJUnit.java contains basic JUnit tests to test given test cases plus a few additional cases including tests for parse and verify the format, quantity, and range of the data. 


#### Built With
* [Maven](https://maven.apache.org/) - Dependency Management

 #### Conceptualization:
 1. The idea regards the coordinates of the farm as each cell.
 2. mark coordinates to find arable lands
