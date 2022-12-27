package steganography;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Encrypter
{
    private final static int JUMP_BY_X = 17;
    private final static int JUMP_BY_Y = 31;

    /**
     * encrypt a message into an image file
     *
     * @param args: args[0] - image file
     *              args[1] - target file
     *              args[2] - message file (text)
     *              args[3] - key
     */
    public static void main(String[] args)
    {
        if (args.length < 4)
        {
            System.out.println("Not enough arguments supplied");
            System.exit(0);
        }
        BufImg srcImage = new BufImg(new File(args[0]));
        char[] key = args[3].toCharArray();

        try
        {
            encrypt(srcImage, args[1], args[2], key);
            System.out.println("Image created successfully! :)");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void encrypt(BufImg srcImage, String tgtImage, String encryptFile, char[] key)
    {
        int keyLength = key.length;

        try
        {
            byte[] text = Files.readAllBytes(Paths.get(encryptFile));
            int textLength = text.length;
            int keyIndex = 0;
            int pixelXIndex = JUMP_BY_X;
            int pixelYIndex = JUMP_BY_Y;
            int tri = 0;

            int width = srcImage.getBi().getWidth(); // columns
            int height = srcImage.getBi().getHeight(); // rows

            for (int textIndex = 0; textIndex < textLength; textIndex++)
            {
                int xored = (text[textIndex] ^ key[keyIndex++]);

                keyIndex = (keyIndex >= keyLength) ? 0 : keyIndex;

                Color pixel = new Color(srcImage.getBi().getRGB(pixelXIndex, pixelYIndex));

                switch (tri++ % 3)
                {
                    case 0:
                        srcImage.getBi().setRGB(pixelXIndex, pixelYIndex,
                                new Color(xored, pixel.getGreen(), pixel.getBlue()).getRGB());
                        break;
                    case 1:
                        srcImage.getBi().setRGB(pixelXIndex, pixelYIndex,
                                new Color(pixel.getRed(), xored, pixel.getBlue()).getRGB());
                        break;
                    case 2:
                        srcImage.getBi().setRGB(pixelXIndex, pixelYIndex,
                                new Color(pixel.getRed(), pixel.getGreen(), xored).getRGB());
                        break;
                }

                pixelXIndex += JUMP_BY_X;
                pixelXIndex %= width;
                pixelYIndex += JUMP_BY_Y;
                pixelYIndex %= height;

            }

            /*
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {

                    image.setRGB(x, y, srcImage.getBi().getRGB(x, y));
                }
            }
*/
            File file = new File(tgtImage);
            ImageIO.write(srcImage.getBi(), "jpg", file);
        } catch (Exception exc)
        {
            exc.printStackTrace();
            // System.out.println(exc.getMessage());
        }

    }
}
