package transactions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileParser
{
    private final String fileName;
    private static final int NUM_FIELDS = 4;

    public FileParser(String fileName)
    {
        this.fileName = fileName;
    }

    public ArrayList<Transaction> parseFile()
    {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                Transaction transaction = getLineTransaction(line);
                if (transaction != null)
                {
                    transactions.add(transaction);
                }
            }
        } catch (Exception ignored)
        {
        }

        return transactions;
    }

    private Transaction getLineTransaction(String line)
    {
        String[] fields = line.split(",");
        if (areValid(fields))
        {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try
            {
                return new Transaction(format.parse(fields[0]),
                        fields[1],
                        Double.parseDouble(fields[2]),
                        Double.parseDouble(fields[3]));
            } catch (Exception e)
            {
                return null;
            }
        } else
        {
            return null;
        }
    }

    private boolean areValid(String[] fields)
    {
        boolean allAok = false;
        boolean hasCorrectNumber = fields.length == NUM_FIELDS;
        if (hasCorrectNumber)
        {
            // dd/mm/yyyy
            boolean hasValidDate = isValidFormat(fields[0]);
            if (hasValidDate)
            {
                try
                {
                    double dummy = Double.parseDouble(fields[2]);
                    dummy = Double.parseDouble(fields[3]);
                    allAok = true;
                } catch (Exception ignored)
                {
                }
            }
        }
        return allAok;
    }

    private boolean isValidFormat(String field)
    {
        String regex = "^(1[0-2]|0[1-9])/(3[01]"
                + "|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) field);
        return matcher.matches();
    }
}
