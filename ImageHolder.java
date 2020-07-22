import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;

/**
 * Simple class that serves to be an Actor to display the image.
 * 
 * (Revised 11/14 to avoid crashing if user cancels import operation).
 * (Edited by 12/19 by Star Xie for increased functionality and image dragging capabilities)
 * 
 * @author Star Xie
 * @version December 2019
 */
public class ImageHolder extends Actor
{
    //Declares greenfoot image to be displayed
    private GreenfootImage imageToDisplay; 
    
    //Declares integers representing the location of the moving mouse
    private int moveX;
    private int moveY;
    
    //Declares integers representing the initial X and Y location of the image
    private int initX;
    private int initY;
    /**
     * Construct an ImageHolder with a file name. If there is an error, 
     * show a blank GreenfootImage.
     * 
     * @param fileName  Name of image file to be displayed.
     */
    public ImageHolder (String fileName)
    {
        openFile (fileName);
    }

    /**
     * Attempt to open a file and assign it as this Actor's image
     * 
     * @param fileName  Name of the image file to open (must be in this directory)
     * 
     * @return boolean  True if operation successful, otherwise false
     */
    public boolean openFile (String fileName)
    {
        try {
            //Checks if there is a file name
            if (fileName != null)
            {
                //Sets image object to open image as needed
                imageToDisplay = new GreenfootImage (fileName);
                //Sets image in world to current display image
                setImage(imageToDisplay);
            }
            else
            {
                //Returns false boolean to show image has not been opened
                return false;
            }
        }
        catch (IllegalArgumentException e)
        {
            //Returns false boolean if nothing is caught
            return false;
        }
        //Returns true if nothing else is caught
        return true;
    }
    /**
     * Act- do whatever the act method wants to do
     */
    public void act()
    {
        //Checks if mouse is pressing image to move/drag it
        if (Greenfoot.mousePressed(this))
        {
            //Gets current mouse information
            MouseInfo mi = Greenfoot.getMouseInfo();
            
            //Instantiates current mouse position in terms of X and Y coordinates
            int moveX=mi.getX();
            int moveY=mi.getY();
            
            //Instantiates current image coordinates (center of image)
            int initX=this.getX();
            int initY=this.getY();
        }
        if (Greenfoot.mouseDragged(this))
        {
            //Gets current mouse information
            MouseInfo mi = Greenfoot.getMouseInfo();
            
            //Sets location of image to move according to the distance moved by the mouse
            this.setLocation(initX+mi.getX()-moveX, initY+mi.getY()-moveY);
            
            
            
            //Resets current position of the image itself
            int initX=this.getX();
            int initY=this.getY();
        }
    }
    /**
     * Allows access to my awtImage - the backing data underneath the GreenfootImage class.
     * 
     * @return BufferedImage returns the backing image for this Actor as an AwtImage
     */
    public BufferedImage getBufferedImage ()
    {
        //Returns the buffered image of a greenfoot image
        return this.getImage().getAwtImage();
    }

}
