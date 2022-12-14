package knapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class KnapsackProblem
{
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
                items.add(new Item(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1])));
            }
        } catch (Exception exception)
        {
            System.out.println("This happened: " + exception.getMessage());
        }

        Knapsack knapsack = new Knapsack(maxWeight);
        /*ArrayList<Item> usedItems = knapsack.greedySolution(items);
        System.out.println(usedItems);
        System.out.println(items);
        System.out.println("current  value in knapsack: " + knapsack.getValue());
        System.out.println("maximum weight in knapsack: " + knapsack.getMaxWeight());
        System.out.println("current weight in knapsack: " + knapsack.getCurrWeight());*/

//        System.out.println(items);
        System.out.println("items in knapsack: \n" + knapsack.wikiSolution(items)
                .toString()
                .replace("[","")
                .replace("]",""));
        System.out.println("maximum weight in knapsack: " + knapsack.getMaxWeight());
        System.out.println("current  value in knapsack: " + knapsack.getValue());
        System.out.println("current weight in knapsack: " + knapsack.getCurrWeight());
//        System.out.println("current items in the knapsack: " + knapsack);

    }
}
