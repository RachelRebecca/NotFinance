package transactions;

import java.util.ArrayList;

public class TransactionInformation
{
    private ArrayList<Transaction> transactions;
    private int numberOfTransactions;
    private double totalCostOfTransactions;

    public TransactionInformation(ArrayList<Transaction> tList)
    {
        transactions = tList;
        calculateNumTransactions();
        calculateTotalCost();
    }

    public int getNumberOfTransactions()
    {
        return numberOfTransactions;
    }

    public double getTotalCostOfTransactions()
    {
        return totalCostOfTransactions;
    }

    private void calculateNumTransactions()
    {
        numberOfTransactions = transactions.size();
    }

    private void calculateTotalCost()
    {
        totalCostOfTransactions = 0;
        for (Transaction transaction : transactions)
        {
            totalCostOfTransactions += (transaction.price() * transaction.quantity());
        }
    }

    @Override
    public String toString()
    {
        return "Number of transactions: " + numberOfTransactions +
                "\nTotal cost: " + totalCostOfTransactions;
    }
}
