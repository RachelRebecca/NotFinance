package steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BufImg
{
    private BufferedImage bi = null;

    public BufImg(File imageFile)
    {
        try
        {
            bi = ImageIO.read(imageFile);
         } catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public BufferedImage getBi()
    {
        return bi;
    }
}