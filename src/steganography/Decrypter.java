package steganography;

import java.awt.*;
import java.io.File;

public class Decrypter
{
    private final static int JUMP_BY_X = 17;
    private final static int JUMP_BY_Y = 31;

    /**
     * encrypt a message into an image file
     *
     * @param args: args[0] - image file
     *              args[1] - key
     *              args[2] - text length
     */
    public static void main(String[] args)
    {
        if (args.length < 3)
        {
            System.out.println("Not enough arguments supplied");
            System.exit(0);
        }
        BufImg srcImage = new BufImg(new File(args[0]));
        char[] key = args[1].toCharArray();

        try
        {
            String decryptedMessage = decrypt(srcImage, key, Integer.parseInt(args[2]));
            System.out.println(decryptedMessage);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    private static String decrypt(BufImg srcImage, char[] key, int textLength)
    {
        int keyLength = key.length;

        StringBuilder builder = new StringBuilder();
        try
        {
            int keyIndex = 0;
            int pixelXIndex = JUMP_BY_X;
            int pixelYIndex = JUMP_BY_Y;
            int tri = 0;

            int width = srcImage.getBi().getWidth(); // columns
            int height = srcImage.getBi().getHeight(); // rows

            for (int textIndex = 0; textIndex < textLength; textIndex++)
            {
                Color color;
                int val = 0;
                switch (tri++ % 3)
                {
                    case 0:
                        color = new Color(srcImage.getBi().getRGB(pixelXIndex, pixelYIndex));
                        int red = color.getRed();
                        val = red ^ key[keyIndex++];
                        break;
                    case 1:
                        color = new Color(srcImage.getBi().getRGB(pixelXIndex, pixelYIndex));
                        int green = color.getGreen();
                        val = green ^ key[keyIndex++];
                        break;
                    case 2:
                        color = new Color(srcImage.getBi().getRGB(pixelXIndex, pixelYIndex));
                        int blue = color.getBlue();
                        val = blue ^ key[keyIndex++];
                        break;
                }

                builder.append((char)val);
                pixelXIndex += JUMP_BY_X;
                pixelXIndex %= width;
                pixelYIndex += JUMP_BY_Y;
                pixelYIndex %= height;
                keyIndex = (keyIndex >= keyLength) ? 0 : keyIndex;
            }
        } catch (Exception exc)
        {
            System.out.println(exc.getMessage());
        }
        return builder.toString();
    }
}