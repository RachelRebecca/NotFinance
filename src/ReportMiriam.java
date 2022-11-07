import java.util.ArrayList;

public class ReportMiriam
{
    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            FileParser fileParser = new FileParser(args[0]);
            ArrayList<Transaction> transactions = fileParser.parseFile();
            for (Transaction transaction:transactions)
            {
                System.out.println(transaction);
            }
        }
    }
}
