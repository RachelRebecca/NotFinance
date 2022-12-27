package transactions;

import java.util.ArrayList;
import java.util.Calendar;

public class Filter
{
    private ArrayList<Transaction> transactions;

    public Filter(ArrayList<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    public ArrayList<Transaction> filterByTicker(String ticker)
    {
        ArrayList<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions)
        {
            if (transaction.ticker().equals(ticker))
            {
                filtered.add(transaction);
            }
        }
        return filtered;
    }

    public ArrayList<Transaction> filterByMonth(int month)
    {
        ArrayList<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(transaction.date());
            int transactionMonth = cal.get(Calendar.MONTH) + 1;
            if (month == transactionMonth)
            {
                filtered.add(transaction);
            }
        }
        return filtered;
    }
}
