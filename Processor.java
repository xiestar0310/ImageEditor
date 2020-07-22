import greenfoot.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Processor - the class that processes images.
 * <p>
 * This class manipulated Java BufferedImages, which are effectively 2d arrays of pixels. 
 * Each pixel is a single integer packed with 4 values inside it. Manipulations include, but are not
 * limited to changing pixel colours, rotations, and special filters. Additionally, manipulations
 * may be undone or redone as needed.
 * 
 * @author Star Xie
 * @version December 2019
 */
public class Processor  
{
    static ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>(); //Stores deep copies of the image every time a change is made

    /**
     * Example colour altering method by Mr. Cohen. This method will
     * increase the blue value while reducing the red and green values.
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi                The BufferedImage (passed by reference) to change.
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage blueify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (blue < 254)
                    blue += 2;
                if (red >= 50)
                    red--;
                if (green >= 50)
                    green--;

                //Packages up colors into corresponding pixels
                int newColour = packagePixel (red, green, blue, alpha);
                
                //sets buffered image into corrected color palate
                bi.setRGB (x, y, newColour);
                
            }
        }
        //returns the altered greenfootimage
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * Increases the red value while reducing the blue and green values.
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi                The BufferedImage (passed by reference) to change.
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage redify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (red < 254)
                    red += 2;
                if (blue >= 50)
                    blue--;
                if (green >= 50)
                    green--;

                //Packages up colors into corresponding pixels
                int newColour = packagePixel (red, green, blue, alpha);
                
                //sets buffered image into corrected color palate
                bi.setRGB (x, y, newColour);
            }
        }
        //returns the altered greenfootimage
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * Increases the green value while reducing the red and blue values.
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi                The BufferedImage (passed by reference) to change.
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage greenify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (green < 254)
                    green += 2;
                if (red >= 50)
                    red--;
                if (blue >= 50)
                    blue--;

                //Packages up colors into corresponding pixels
                int newColour = packagePixel (red, green, blue, alpha);
                
                //sets buffered image into corrected color palate
                bi.setRGB (x, y, newColour);
                
            }
        }
        //returns the altered greenfootimage
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * Color altering method to make image negative. 
     * 
     * @param bi                The BufferedImage (passed by reference) to change.
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage negativeImage (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic negative (inversing all R, G, B values)
                if (blue < 256)
                    blue = 255-blue;
                if (red < 256)
                    red = 255-red;
                if (green < 256)
                    green = 255-green;

                //Packages up colors into corresponding pixels
                int newColour = packagePixel (red, green, blue, alpha);
                //Sets buffered image to altered image
                bi.setRGB (x, y, newColour);
            }
        }
        //Returns altered greenfoot image
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * Color altering method to make image warmer. Method increases the red while 
     * reducing the green and blue values.
     * 
     * @param bi                The BufferedImage (passed by reference) to change.
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage warmer(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls the method in bufferedimage that returns R, G, B and alpha values
                int rgb = bi.getRGB(x, y);

                // Calls the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                //Make picture warmer
                if (red <= 255){
                    red += 6;
                }
                if (green <= 255){
                    green += 6;
                }

                //Checks for boundaries and corrects them accordingly
                if (red > 255){
                    red = 255;
                }
                if (green > 255){
                    green = 255;
                }

                //Packages up pixels according to altered color combinations
                int newColour = packagePixel (red, green, blue, alpha);
                //Sets bufferedimage as altered pixels
                bi.setRGB (x, y, newColour);
            }
        }
        //Returns altered greenfoot image
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * Color altering method to make image colder. Method increases the blue while 
     * decreasing green and red values.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage cooler (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                //Icreases blue value to make picture cooler
                if (blue <= 255){
                    blue += 3;
                }

                //Checks for upper bounds and sets them accordingly
                if (blue > 255 ){
                    blue = 255;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * Increases the brightness of the provided BufferedImage
     * 
     * @param bi                The BufferedImage that will be brightened
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage brighten(BufferedImage bi)
    {   
         // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                //Increases all R, G, B values to brighten the image while checking the upper bound
                if (red+10 < 225)
                {
                    red+=10;
                }
                if (green+10 < 225)
                {
                    green+=10;
                }
                if (blue+10 < 225)
                {
                    blue+=10;
                }

                //Packs newly altered R, G, B values into pixels
                int newColour = packagePixel (red, green, blue, alpha);
                //Sets BufferedImage with new pixels
                bi.setRGB (x, y, newColour);
            }
        } 
        //Returns altered GreenfootImage
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * Decreases the brightness of the provided BufferedImage
     * 
     * @param bi                The BufferedImage that will be darkened
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage darken(BufferedImage bi)
    {   
         // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        //loop through values
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                //Decreases all R, G, B values to brighten the image while checking the upper bound
                if (red-10 > 35)
                {
                    red-=10;
                }
                if (green-10 > 35)
                {
                    green-=10;
                }
                if (blue-10 > 35)
                {
                    blue-=10;
                }

                //Packs newly altered R, G, B values into pixels
                int newColour = packagePixel (red, green, blue, alpha);
                //Sets BufferedImage with newly created pixels
                bi.setRGB (x, y, newColour);
            }
        }    
        //Returns altered GreenfootImage
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * An effect to give the image a light brown - brown tone. 
     * 
     * @author Antonio
     * http://stackoverflow.com/questions/5132015/how-to-convert-image-to-sepia-in-java
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     */
    public static GreenfootImage sepia (BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R, G, B and alpha values
                int rgb = bi.getRGB(x, y);

                // Calls the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int sepiaDepth = 20; //20 works well and was recommended. 0 is black and white.
                int sepiaIntensity = 30; //From 0-255, 30 produces nice results

                int gry = (red + green + blue) / 3;
                red = green = blue = gry;
                red = red + (sepiaDepth * 2);
                green = green + sepiaDepth;

                if (red>255){
                    red=255;
                }
                if (green>255){
                    green=255;
                }
                if (blue>255){
                    blue=255;
                }

                // Darken blue color to increase sepia effect
                blue-= sepiaIntensity;

                // Normalize if out of bounds
                if (blue<0){
                    blue=0;
                }
                if (blue>255){
                    blue=255;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        return createGreenfootImageFromBI(bi);
    }
    
    /**
     * Sets the image in a grey-monotone-like filter. Takes away all color replacing it with shades
     * 
     * @param bi                The BufferedImage that will be darkened
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage greyScale (BufferedImage bi)
    {
         // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and av vvlpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                int average = (red+green+blue)/3;
                // make the pic BLUE-er
                if (blue < 256)
                    blue=average;
                if (red < 256)
                    red=average;
                if (green < 256)
                    green=average;
                    
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
        return createGreenfootImageFromBI(bi);
    }

    /**
     * Flips original image horizontally
     * 
     * @param bi                The BufferedImage that will be darkened
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage flipHorizontal (BufferedImage bi)
    {
         // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        
        //Reverses x and y indicies to change X values rather than Y values
        for (int y = 0; y < ySize; y++)
        {
            for(int x = 0; x < xSize; x++)
            {
                //Gets current R, G, B value of current pixel
                int rgb = bi.getRGB(x, y);
                //Reverses X coordinate to flip image accordingly
                newBi.setRGB(xSize-x-1, y, rgb);
            }
        }
        //Returns altered GreenfootImage
        return createGreenfootImageFromBI(newBi);
    }

    /**
     * Flips original image vertically
     * 
     * @param bi                The BufferedImage that will be darkened
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage flipVertical(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        //Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                //Gets current R, G, B value of current pixel
                int rgb = bi.getRGB(x, y);
                //Reverses X coordinate to flip image accordingly
                newBi.setRGB(x, ySize-y-1, rgb);
            }
        }
        //Returns altered GreenfootImage
        return createGreenfootImageFromBI(newBi);
    }

    /**
     * Rotates image clockwise 90 degrees accordingly
     * 
     * @param bi                The BufferedImage that will be darkened
     * 
     * @return GreenfootImage   The new altered image in the form of a GreenfootImage  
     */
    public static GreenfootImage rotate90(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize=bi.getWidth();
        int ySize=bi.getHeight();

        //Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (ySize, xSize, 3);
        
        //Get new X and Y values for rotated image
        int newX = newBi.getWidth();
        int newY = newBi.getHeight();
        for (int x = 0; x < newX; x++)
        {
            for (int y = 0; y < newY; y++)
            {
                //Gets specific R, G, B value, reversing the X value accordingly
                int rgb = bi.getRGB(y, newX-x-1);
                //Sets BufferedImage pixels accordingly
                newBi.setRGB(x, y, rgb);
            }
        }
        return createGreenfootImageFromBI(newBi);
    }

    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     *                  
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * By Jordan Cohen
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }
    
    /**
     * Creates a GreenfootImage from a BufferedImage using various interior greenfoot mechanisms
     * 
     * By Jordan Cohen
     * 
     * @param bi                The BufferedImage that will be darkened
     * 
     * @return GreenfootImage   The new image in the form of a GreenfootImage 
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);
        return returnImage;
    }
}
