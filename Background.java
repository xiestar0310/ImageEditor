import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.WritableRaster;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * 
 * @author Star Xie
 * @version November 2013
 */
public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "Birb.jpg";

    //Declare needed objects and variables
    private ImageHolder image;
    private TopBar bar = new TopBar();

    //Declare file and its buttons
    private TextButton fileButton;
    private TextButton openFile;
    private TextButton savePNG;
    private TextButton saveJPG;

    //Declare edit and its buttons
    private TextButton editButton;
    private TextButton undoButton;
    private TextButton redoButton;

    //Declare image and its buttons
    private TextButton imgeButton;
    private TextButton hRevButton;
    private TextButton vRevButton;
    private TextButton rRevButton;

    //Delare filter and its buttons
    private TextButton filterButton;
    private TextButton negButton;
    private TextButton greyButton;
    private TextButton sepiaButton;

    //Declare lighting and its buttons
    private TextButton lightButton;
    private TextButton warmButton;
    private TextButton coolButton;
    private TextButton brightButton;
    private TextButton darkButton;

    //Declare color and its buttons
    private TextButton colorButton;
    private TextButton blueButton;
    private TextButton redButton;
    private TextButton greenButton;

    //Declare string to store names of file
    private String fileName;

    //Creates an arraylist to implement undo and redo functionality
    private List<GreenfootImage> imageList = new ArrayList<GreenfootImage>();
    //Declares integer to account for each added functionality
    private int pointer=0;

    //Declares various arrays for each dropdown menu
    private TextButton[] imageArray = new TextButton[3];
    private TextButton[] filterArray = new TextButton[3];
    private TextButton[] lightArray = new TextButton[4];
    private TextButton[] editArray = new TextButton[2];
    private TextButton[] fileArray = new TextButton[3];
    private TextButton[] colorArray = new TextButton[3];

    private boolean checkIfUndoed=false;
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 1200x750 cells with a cell size of 1x1 pixels.
        super(1200, 750, 1, false); //The boolean false means that objects can be dragged outside the world

        // Initialize image button
        image = new ImageHolder(STARTING_FILE);

        //Initialize file button and its dropdown options
        fileButton = new TextButton("File",false);
        openFile = new TextButton(" Open File ",true);
        savePNG = new TextButton(" Save as PNG ",true);
        saveJPG = new TextButton(" Save as JPG ",true);

        // Initialize edit button and its dropdown options
        editButton = new TextButton("Edit",false);
        undoButton = new TextButton("Undo ",true);
        redoButton = new TextButton("Redo ",true);

        //Initialize image button and its dropdown options
        imgeButton = new TextButton("Image",false);
        hRevButton = new TextButton(" Flip Horizontal ",true);
        vRevButton = new TextButton(" Flip Vertical ",true);
        rRevButton = new TextButton(" Flip 90 Degrees ",true);

        //Initialize filter button and its dropdown options
        filterButton = new TextButton("Filter ",false);
        negButton = new TextButton("Negative ",true);
        greyButton = new TextButton("GreyScale ",true);
        sepiaButton = new TextButton("Sepia ",true);

        //Initialize lighting button and its dropdown options
        lightButton = new TextButton("Lighting",false);
        warmButton = new TextButton("Warmer ",true);
        coolButton = new TextButton("Cooler ",true);
        brightButton = new TextButton("Brighten ",true);
        darkButton = new TextButton("Darken ",true);

        //Initialize color button and its dropdown options
        colorButton = new TextButton("Color ",false);
        redButton = new TextButton("Redify",true);
        greenButton = new TextButton("Greenify",true);
        blueButton = new TextButton("Blueify",true);

        // Add user image and top bar to world
        addObject (image, 530, 310);
        addObject (bar, 700, 14);

        //Add main permanent buttons to world
        addObject(fileButton, 18, 13);
        addObject(editButton, 55, 13);
        addObject (imgeButton, 103, 13);
        addObject (filterButton, 155, 13);
        addObject (lightButton, 217, 13);
        addObject(colorButton, 282, 13);

        //Instantiate array values with button order
        imageArray[0]=hRevButton;
        imageArray[1]=vRevButton;
        imageArray[2]=rRevButton;

        //Instantiate array values with button order
        filterArray[0]= negButton;
        filterArray[1]= greyButton;
        filterArray[2]= sepiaButton;

        //Instantiate array values with button order
        lightArray[0]= warmButton;
        lightArray[1]= coolButton;
        lightArray[2]= brightButton;
        lightArray[3]= darkButton;

        //Instantiate array values with button order
        editArray[0]= undoButton;
        editArray[1]= redoButton;

        //Instantiate array values with button order
        fileArray[0]= openFile;
        fileArray[1]= savePNG;
        fileArray[2]= saveJPG;

        //Instantiate array values with button order
        colorArray[0]=redButton;
        colorArray[1]=greenButton;
        colorArray[2]=blueButton;

        //Initialize hashmaps for each array to find the longest width accordingly

        TextButton.initializeHashMap(fileArray);
        TextButton.initializeHashMap(editArray);
        TextButton.initializeHashMap(imageArray);
        TextButton.initializeHashMap(filterArray);
        TextButton.initializeHashMap(lightArray);
        TextButton.initializeHashMap(colorArray);

        //Adds the original object to the array for undo and redo effects
        imageList.add(createGreenfootImageFromBI(image.getBufferedImage()));
    }

    /**
     * Act() method just checks for mouse input
     */
    public void act ()
    {
        //Checks if mouse has any new activity
        checkMouse();
    }

    /**
     * Check for user clicking on a button
     */
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        if (Greenfoot.mouseClicked(null))
        {
            //Checks if mouse has clicked on each button and performs functionality accordingly
            if (Greenfoot.mouseClicked(hRevButton)){
                //changes current image to image with special effect
                image.setImage(Processor.flipHorizontal(deepCopy(image.getBufferedImage())));
                //adds the image to the imageList for undo and redo functionality
                imageList.add(createGreenfootImageFromBI(image.getBufferedImage()));
                //Increments the pointer counter up for undo and redo functionality
                pointer++;
            }
            else if (Greenfoot.mouseClicked(openFile))
            {
                //Calls open file method
                openFile();
            }
            else if (Greenfoot.mouseClicked(savePNG))
            {
                //Calls save as PNG method
                saveImageAsPNG();
            }
            else if(Greenfoot.mouseClicked(saveJPG))
            {
                //Calls save as JPG method
                saveImageAsJPG();
            }
            /*For each of the following mouse checks, the image is changed with special effect, it is added to an arraylist, and a pointer
            modelling a counter increments*/
            else if(Greenfoot.mouseClicked(vRevButton)){
                image.setImage(Processor.flipVertical(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(rRevButton)){
                image.setImage((Processor.rotate90(deepCopy(image.getBufferedImage()))));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(negButton))
            {
                image.setImage(Processor.negativeImage(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(greyButton))
            {
                image.setImage(Processor.greyScale(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(sepiaButton))
            {
                image.setImage(Processor.sepia(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(warmButton))
            {
                image.setImage(Processor.warmer(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(coolButton))
            {
                image.setImage(Processor.cooler(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(brightButton))
            {
                image.setImage(Processor.brighten(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(darkButton))
            {
                image.setImage(Processor.darken(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(redButton))
            {
                image.setImage(Processor.redify(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(greenButton))
            {
                image.setImage(Processor.greenify(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(blueButton))
            {
                image.setImage(Processor.blueify(deepCopy(image.getBufferedImage())));
                imageList.add(createGreenfootImageFromBI(deepCopy(image.getBufferedImage())));
                pointer++;
            }
            else if(Greenfoot.mouseClicked(undoButton))
            {
                //Checks if there is anything to undo, otherwise set image to original image
                if(pointer > 0)
                {
                    //Decrements pointer count
                    pointer--;
                }
                else{
                    //Sets pointer to original image
                    pointer=0;
                }
                //Gets index of pointer in the arrayList
                GreenfootImage newImage = imageList.get(pointer);
                //Sets image as new image to implement undo functionality
                image.setImage(newImage);
            }
            else if(Greenfoot.mouseClicked(redoButton))
            {
                //Checks if there is anything to redo, otherwise set image to original image
                if(pointer<imageList.size()-1) 
                {
                    //Increments pointer count
                    pointer++;
                }
                //Checks if the upper bound is reached
                else if (pointer==imageList.size()-1) 
                {
                    //Sets pointer to the upper bound if it is reached
                    pointer=imageList.size()-1;
                }
                //Gets index of pointer in the arrayList
                GreenfootImage newImage = imageList.get(pointer);
                //Sets image as new image to implement undo functionality
                image.setImage(newImage);
            }
        }

        //For all code below, motion of mouse is checked, and a drop-down menu-like development is created
        //totalHeight is an integer that denotes the center of the primary buttons that do not vanish
        //Each drop down menu to the right of the current one is removed in to avoid problems of opening two drop down menus at once
        if(Greenfoot.mouseMoved(null))
        {
            if(Greenfoot.mouseMoved(fileButton)){ 
                int totalHeight=14;
                for(int i=0; i<fileArray.length; i++)
                {
                    addObject(fileArray[i],62,totalHeight+=13+fileArray[0].getLength()/2);
                }
                removeDropDown(editArray);
            }
            else if(Greenfoot.mouseMoved(editButton)){
                int totalHeight=14;
                for(int i=0; i<editArray.length; i++)
                {
                    addObject(editArray[i],67,totalHeight+=13+editArray[0].getLength()/2);
                }
                removeDropDown(imageArray);
            }
            else if(Greenfoot.mouseMoved(imgeButton)){
                int totalHeight=14;
                for(int i=0; i<imageArray.length; i++)
                {
                    addObject(imageArray[i],151,totalHeight+=13+imageArray[0].getLength()/2);
                }
                removeDropDown(filterArray);
            }
            else if(Greenfoot.mouseMoved(filterButton)){
                int totalHeight=14;
                for(int i=0; i<filterArray.length; i++)
                {
                    addObject(filterArray[i], 177, totalHeight+=13+filterArray[0].getLength()/2);
                }
                removeDropDown(lightArray);
            }
            else if(Greenfoot.mouseMoved(lightButton)){
                int totalHeight=14;
                for(int i=0; i<lightArray.length; i++)
                {
                    addObject(lightArray[i], 224, totalHeight+=13+lightArray[0].getLength()/2);
                }
                removeDropDown(colorArray);
            }
            else if(Greenfoot.mouseMoved(colorButton))
            {
                int totalHeight=14;
                for(int i=0; i<colorArray.length; i++)
                {
                    addObject(colorArray[i], 296, totalHeight+=13+lightArray[0].getLength()/2);
                }
            }

            //Each if statement checks each button to see if the mouse is still hovering over any of them
            //If not, the drop down menu is removed from the world
            if(!Greenfoot.mouseMoved(imgeButton)&&!Greenfoot.mouseMoved(imageArray[0])
            &&!Greenfoot.mouseMoved(imageArray[1])&&!Greenfoot.mouseMoved(imageArray[2]))
            {
                removeDropDown(imageArray);
            }
            if(!Greenfoot.mouseMoved(filterButton)&&!Greenfoot.mouseMoved(filterArray[0])
            &&!Greenfoot.mouseMoved(filterArray[1])&&!Greenfoot.mouseMoved(filterArray[2]))
            {
                removeDropDown(filterArray);
            }
            if(!Greenfoot.mouseMoved(lightButton)&&!Greenfoot.mouseMoved(lightArray[0])
            &&!Greenfoot.mouseMoved(lightArray[1])&&!Greenfoot.mouseMoved(lightArray[2])
            &&!Greenfoot.mouseMoved(lightArray[3]))
            {
                removeDropDown(lightArray);
            }
            if(!Greenfoot.mouseMoved(fileButton)&&!Greenfoot.mouseMoved(fileArray[0])
            &&!Greenfoot.mouseMoved(fileArray[1])&&!Greenfoot.mouseMoved(fileArray[2]))
            {
                removeDropDown(fileArray);
            }
            if(!Greenfoot.mouseMoved(editButton)&&!Greenfoot.mouseMoved(editArray[0])
            &&!Greenfoot.mouseMoved(editArray[1]))
            {
                removeDropDown(editArray);
            }
            if(!Greenfoot.mouseMoved(colorButton)&&!Greenfoot.mouseMoved(colorArray[0])
            &&!Greenfoot.mouseMoved(colorArray[1])&&!Greenfoot.mouseMoved(colorArray[2]))
            {
                removeDropDown(colorArray);
            }
        }
    }

    /**
     * Allows dropDown Menu to be removed accordingly 
     * 
     * @param arr       Takes a dropdown array of the TextButton class
     */
    private void removeDropDown(TextButton[] arr)
    {
        //Loops through array
        for(int i=0; i<arr.length; i++)
        {
            //Removes objects accordingly
            removeObject(arr[i]);
        }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a file name with extension");

        // If the file opening operation is successful, update the text in the open file button
        if (image.openFile (fileName))
        {
            //Displays string
            String display = " Open File ";
            //updates string display
            openFile.update (display);
        }

    }

    /**
     * Allows the user to save image as PNG.
     */
    private void saveImageAsPNG()
    {
        //Shows input dialog
        String fileName = JOptionPane.showInputDialog("Please input a file name to save as. (No extension)");

        //Tries to see if file name is blank
        try{
            if (fileName.equals ("")){
                //automatically saves image as picture
                fileName += "picture";
                JOptionPane.showMessageDialog (null, "No file name entered, file name set to: picture");
            }
        }
        catch(NullPointerException e)
        {
            //If doesn't work, show message box
            JOptionPane.showMessageDialog(null, "Save cancelled!");
            return;
        }

        //Tries save image as PNG
        try
        {
            //Adds png extension
            fileName += ".png";
            //Creates new file
            File f = new File (fileName);
            //Writes image accordingly and saves
            ImageIO.write(image.getImage().getAwtImage(), "png", f); 
        }
        catch(IOException e)
        {
            //If exception caught, show message dialog
            JOptionPane.showMessageDialog (null, "Save unsuccessful!");
        }
    }

    /**
     * Allows user to save image as JPG.
     */
    private void saveImageAsJPG(){
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a file name to save as. (No extension)");

        //Tries to see if file name is blank
        try
        {
            if (fileName.equals ("")){
                //automatically saves image as picture
                fileName += "picture";
                JOptionPane.showMessageDialog (null, "No file name entered, file name set to: picture");
            }
        }
        catch(NullPointerException e)
        {
            //If doesn't work, show message box
            JOptionPane.showMessageDialog(null, "Save cancelled!");
            return;
        }
        //Adds jpg extension
        fileName += ".jpg";
        //Create new file
        File f = new File (fileName);     

        //Assign buffered reader
        BufferedImage pngImage = image.getBufferedImage();
        try{
            BufferedImage newBufferedImage = new BufferedImage (pngImage.getWidth(), pngImage.getHeight(), pngImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(pngImage,0,0,java.awt.Color.WHITE,null);
            ImageIO.write(newBufferedImage, "jpg", f );
        }
        catch(IOException e) {
            //Exception caught, show message dialog
            JOptionPane.showMessageDialog (null, "Save unsuccessful!");
        }
    }

    /**
     * Takes in a BufferedImage and returns a GreenfootImage.
     * 
     * By Jordan Cohen
     *
     * @param newBi                 The BufferedImage to convert.
     * 
     * @return GreenfootImage       A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);
        return returnImage;
    }

    /**
     * Takes in a BufferedImage and returns an identical BufferedImage
     * 
     * By Jordan Cohen
     *
     * @param newBi The BufferedImage to convert.
     * 
     * @return GreenfootImage A BufferedImage built from the BufferedImage provided.
     */
    public static BufferedImage deepCopy(BufferedImage bi)
    {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);
    }
}

