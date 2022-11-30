package transactions;

import java.io.*;
import java.util.Arrays;

/**
 * Given a csv file with fields <br>
 * date    formatted as mm/dd/yyyy <br>
 * ticker  e.g., IBM <br>
 * quantity e.g. -300 (sell 300 shares) <br>
 * price    (per share, e.g., 120.33) <br>
 *     produce a "report" showing total expense <br>
 */
public class Report1
{
    private static final int NUM_FIELDS = 4;
    private static int numTransactions = 0; // todo: should not be global

    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            double totalExpense = 0;

            try
            {
                //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));

                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    String[] fields = line.split(",");

                    totalExpense += getLineExpense(fields);
                }
            } catch (Exception exception)
            {
                exception.printStackTrace();
                System.out.println(exception.getMessage());
            }

            System.out.println("Total expense: " + totalExpense);
            System.out.println("Total transactions: " + numTransactions);
        } else
        {
            System.out.println("No file provided");
        }
    }

    private static double getLineExpense(String[] fields)
    {
        double retVal = 0;
        try
        {
            retVal = Double.parseDouble(fields[2]) * Double.parseDouble(fields[3]);
            numTransactions++;
        } catch (Exception exception)
        {
            System.out.println("We ignored this record: " + Arrays.toString(fields));
        }
        return retVal;
    }
}
