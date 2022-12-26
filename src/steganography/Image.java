package steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image
{
    private int[] pixels;
    private int width;
    private int height;

    public Image(File imageFile)
    {
        try
        {
            BufferedImage image = ImageIO.read(imageFile);
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            int z = 0;
            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    pixels[z++] = image.getRGB(x, y);
                }
            }
        } catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public int[] getPixels()
    {
        return this.pixels;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }
}

