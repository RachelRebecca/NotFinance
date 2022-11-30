package transactions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Given a csv file with fields <br>
 * date    formatted as mm/dd/yyyy <br>
 * ticker  e.g., IBM <br>
 * quantity e.g. -300 (sell 300 shares) <br>
 * price    (per share, e.g., 120.33) <br>
 */

public class Report2
{
    private static class FinanceFile
    {
        private double totalExpense;
        private int totalTransactions;
        private final String fileName;

        private FinanceFile(String fileName)
        {
            this.fileName = fileName;
            this.totalExpense = 0;
            this.totalTransactions = 0;
            this.calculateTotalExpenseAndTransactions();
        }

        public double getTotalExpense()
        {
            return this.totalExpense;
        }

        public int getTotalTransactions()
        {
            return this.totalTransactions;
        }

        public void calculateTotalExpenseAndTransactions()
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(this.fileName)))
            {
                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    String[] fields = line.split(",");

                    this.totalExpense += getLineExpense(fields);
                }
            } catch (Exception exception)
            {
//                exception.printStackTrace();
                System.out.println(exception.getMessage());
            }
        }

        private double getLineExpense(String[] fields)
        {
            double retVal = 0;
            try
            {
                retVal = Double.parseDouble(fields[2]) * Double.parseDouble(fields[3]);
                this.totalTransactions++;
            } catch (Exception exception)
            {
                System.out.println("We ignored this record: " + Arrays.toString(fields));
            }
            return retVal;
        }
    }
}
