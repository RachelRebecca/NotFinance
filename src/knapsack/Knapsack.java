package knapsack;

import java.util.*;

public class Knapsack
{
    private final double maxWeight;
    private final ArrayList<Item> items;
    private double currWeight;
    private final double DEFAULT_WEIGHT = 10.0;
    
    public Knapsack(double maxWeight)
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

        for(Item item : sortedByRatios)
        {
            boolean added = addItem(item);
            if (!added) break;
        }

        return items;
    }
    
    private double getMaxWeight()
    {
        return maxWeight;
    }

    private double getCurrWeight() {return currWeight;}

    private boolean addItem(Item item)
    {
        boolean added = false;
        if (canAdd(item))
        {
            added = true;
            items.add(item);
            currWeight += item.getWeight();
        }
        return added;
    }

    private boolean removeItem(Item item)
    {
        boolean exists = items.contains(item);
        items.remove(item);
        if (exists)
        {
            currWeight -= item.getWeight();
        }
        return exists;
    }

    private boolean canAdd(Item item)
    {
        return item.getWeight() + getCurrWeight() <= getMaxWeight();
    }
}

