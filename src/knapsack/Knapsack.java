package knapsack;

import java.sql.Array;
import java.util.*;

public class Knapsack
{
    private final int maxWeight;
    private final ArrayList<Item> items;
    private double currWeight;
    private final int DEFAULT_WEIGHT = 10;

    public Knapsack(int maxWeight)
    {
        this.maxWeight = maxWeight > 0 ? maxWeight : DEFAULT_WEIGHT;
        this.items = new ArrayList<>();
        this.currWeight = 0.0;
    }

    public ArrayList<Item> greedySolution(ArrayList<Item> potentialItems)
    {
        ArrayList<Item> sortedByRatios = new ArrayList<>(potentialItems);
        sortedByRatios.sort(Comparator.naturalOrder());
        Collections.reverse(sortedByRatios);

        for (Item item : sortedByRatios)
        {
            addItemIfPossible(item);
        }

        return items;
    }

    public ArrayList<Item> wikiSolution(ArrayList<Item> potentialItems)
    {
        // todo: not working. need to debug to understand. items returned have a weight > maxWeight

        ArrayList<Item> sortedPotentialItemList = new ArrayList<>(potentialItems);
        sortedPotentialItemList.sort(Comparator.comparing(Item::getWeight));
        Collections.reverse(sortedPotentialItemList);

        int potentialListSize = sortedPotentialItemList.size();
        int[] rowAbove = new int[maxWeight + 1];
        int[] currentRow = new int[maxWeight + 1];

        ArrayList<Item>[] rowAboveItems = new ArrayList[maxWeight + 1];

        for (int i = 0; i <= maxWeight; i++) {
            rowAboveItems[i] = new ArrayList<>();
        }
        ArrayList<Item>[] currentRowItems = new ArrayList[maxWeight + 1];

        for (int i = 0; i <= maxWeight; i++) {
            currentRowItems[i] = new ArrayList<>();
        }

        for (int row = 1; row <= potentialListSize; row++)
        {
            Item newItem = sortedPotentialItemList.get(row - 1);
            int potentialNewWeight = newItem.getWeight();
            int potentialNewValue = newItem.getValue();

            for (int col = 1; col <= maxWeight; col++)
            {
                if (potentialNewWeight <= col)
                {
                    int maxValueWithItem = rowAbove[col - potentialNewWeight] + potentialNewValue;
                    if (maxValueWithItem > rowAbove[col])
                    {
                        currentRow[col] = maxValueWithItem;
                        currentRowItems[col].addAll(rowAboveItems[col - potentialNewWeight]);
                        currentRowItems[col].add(newItem);
                    } else {
                        currentRow[col] = rowAbove[col];
                        currentRowItems[col] = rowAboveItems[col];
                    }
                } else {
                    currentRow[col] = rowAbove[col];
                    currentRowItems[col] = rowAboveItems[col];
                }
            }

            System.arraycopy(currentRow, 0, rowAbove, 0, currentRow.length);
            currentRow = new int[maxWeight + 1];

            System.arraycopy(currentRowItems, 0, rowAboveItems, 0, currentRowItems.length);
            for (int i = 0; i <= maxWeight; i++) {
                currentRowItems[i] = new ArrayList<>();
            }
        }
        ArrayList<Item> toAdd = new ArrayList<>(rowAboveItems[maxWeight]);
        for (Item i : toAdd)
        {
            addItemIfPossible(i);
        }
        return toAdd;
    }

    private Item getItemWithWeightValue(int col)
    {
        for (int ii = 0; ii < items.size(); ii++)
        {
            if (items.get(ii).getWeight() == col)
            {
                return items.get(ii);
            }
        }
        //System.out.println("booga booga!");
        return null;
    }

    public double getMaxWeight()
    {
        return maxWeight;
    }

    public double getCurrWeight()
    {
        return currWeight;
    }

    private boolean addItemIfPossible(Item item)
    {
        boolean added = false;
        if (canAdd(item) && !items.contains(item))
        {
            added = true;
            items.add(item);
            currWeight += item.getWeight();
        }
        return added;
    }

    private boolean removeItem(Item item)
    {
        boolean exists = false;
        if (item != null)
        {
            exists = items.contains(item);
            items.remove(item);
            if (exists)
            {
                currWeight -= item.getWeight();
            }
        }
        return exists;
    }

    private boolean canAdd(Item item)
    {
        return item.getWeight() + getCurrWeight() <= getMaxWeight();
    }

    public double getValue()
    {
        double knapsackValue = 0.0;
        for (Item item : items)
        {
            knapsackValue += item.getValue();
        }
        return knapsackValue;
    }

    @Override
    public String toString()
    {
        return items.toString();
    }


}

