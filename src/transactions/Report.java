package transactions;

import java.util.ArrayList;

public class Report
{
    private ArrayList<Transaction> transactions;
    private TransactionInformation information;

    public Report(ArrayList<Transaction> transactions)
    {
        this.transactions = transactions;
        information = new TransactionInformation(transactions);
    }

    public String getInformation()
    {
        return information.toString();
    }


    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            FileParser fileParser = new FileParser(args[0]);
            ArrayList<Transaction> transactions = fileParser.parseFile();
            Filter filter = new Filter(transactions);
            transactions = filter.filterByTicker("IBM");
            filter = new Filter(transactions);
            transactions = filter.filterByMonth(10);
            Report miriamReport = new Report(transactions);

            System.out.println(miriamReport.getInformation());
        }
    }
}
