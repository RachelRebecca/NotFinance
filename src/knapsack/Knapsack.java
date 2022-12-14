package knapsack;

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

    public int wikiSolution(ArrayList<Item> potentialItems)
    {
        ArrayList<Item> sortedList = new ArrayList<>(potentialItems);
        sortedList.sort(Comparator.comparing(Item::getWeight));
        Collections.reverse(sortedList);

        int listSize = sortedList.size();
        int[][] itemsTable = new int[listSize + 1][maxWeight + 1];
        for (int row = 1; row <= listSize; row++)
        {
            for (int col = 1; col <= maxWeight; col++)
            {
                Item itemToLook = sortedList.get(row - 1);
                if (itemToLook.getWeight() <= col)
                {
                    /*itemsTable[row][col] = Math.max(itemsTable[row - 1][col],
                            itemsTable[row - 1][col - sortedList.get(row - 1).getWeight()] + sortedList.get(row - 1).getValue());
*/
                    if (itemsTable[row - 1][col] > itemsTable[row - 1][col - itemToLook.getWeight()] + itemToLook.getValue())
                    {
                        itemsTable[row][col] = itemsTable[row - 1][col];
                    }
                    else
                    {
                        removeItem(getItemWithWeightValue(col));
                        addItemIfPossible(itemToLook);
                        itemsTable[row][col] = itemsTable[row - 1][col - itemToLook.getWeight()] + itemToLook.getValue();
                    }
                }
                /*else
                {
                    ;
                    *//*itemsTable[row][col] = itemsTable[row - 1][col];
                    this.addItemIfPossible(sortedList.get(row - 1));*//*
                }*/



                /*TODO: READ THIS:
                 *
                 * if adding item takes weight over the limit:
                 *   value = value to the left (col-1)
                 * else:
                 *   value = max of with item or without item
                 *   with = [row-1][col-weight]+value
                 *   without = col-1
                 */


            }
        }


        return itemsTable[listSize][maxWeight];

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

