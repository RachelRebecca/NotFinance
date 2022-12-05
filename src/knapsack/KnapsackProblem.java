package knapsack;

import transactions.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class KnapsackProblem
{
    static final Random random = new Random();
    public static void main(String[] args)
    {
        int maxWeight = 10;
        ArrayList<Item> items = new ArrayList<>();

        if (args.length <= 0)
        {
            System.exit(0);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0])))
        {
            String firstLine = reader.readLine();
            int numItems = Integer.parseInt(firstLine.split(",")[0]);
            maxWeight = Integer.parseInt(firstLine.split(",")[1]);

            String line;
            for (int i = 0; i < numItems; i++)
            {
                line = reader.readLine();
                String[] splitLine = line.split(",");
                items.add(new Item(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1])));
            }
        } catch (Exception ignored)
        {
        }

        Knapsack knapsack = new Knapsack(maxWeight);
        ArrayList<Item> usedItems = knapsack.greedySolution(items);
        System.out.println(usedItems);
        System.out.println(items);
    }
}
