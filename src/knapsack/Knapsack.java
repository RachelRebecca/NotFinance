package knapsack;

import java.util.ArrayList;

public class Knapsack
{
    private double maxWeight;
    private ArrayList<Item> items;
    
    public Knapsack(double maxWeight)
    {
        if (maxWeight > 0)
        {
            this.maxWeight = maxWeight;
            this.items = new ArrayList<>();
        }
    }
    
    public double getMaxWeight()
    {
        return maxWeight;
    }
    
    public boolean addItem(Item item)
    {
        boolean added = false;
        if (item.getWeight() + getKnapsackWeight() <= maxWeight)
        {
            added = true;
            items.add(item);
        }
        return added;
    }

    public boolean removeItem(Item item)
    {
        boolean exists = items.contains(item);
        items.remove(item);
        return exists;
    }
    
    public double getKnapsackWeight()
    {
        double weights = 0.0;
        for (Item item : items)
        {
            weights += item.getWeight();
        }
        return weights;
    }
}

