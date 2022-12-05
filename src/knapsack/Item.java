package knapsack;

import java.sql.SQLOutput;
import java.util.Objects;

public class Item implements Comparable<Item>
{
    private double weight;
    private double value;

    public Item(double weight, double value)
    {
        if (weight > 0 && value > 0)
        {
            this.weight = weight;
            this.value = value;
        }
    }

    public double getWeight()
    {
        return weight;
    }

    public double getValue()
    {
        return value;
    }

    public double getRatio()
    {
        return value / weight;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Item item = (Item) other;
        return Double.compare(item.weight, weight) == 0 && Double.compare(item.value, value) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(weight, value);
    }

    @Override
    public int compareTo(Item o)
    {
        int retVal = 0;
        if (getRatio() > o.getRatio())
        {
            retVal = 1;
        }
        else if (getRatio() == o.getRatio())
        {
            retVal = compareByWeights(o);
        }
        else
        {
            retVal = -1;
        }
        return retVal;
    }

    private int compareByWeights(Item o)
    {
        int retVal = 0;
        if (getWeight() > o.getWeight())
        {
            retVal = 1;
        }
        else if (getWeight() < o.getWeight())
        {
            retVal = -1;
        }
        return retVal;
    }

    @Override
    public String toString()
    {
        return ("weight: " + getWeight() + ", value: " + getValue() + ", ratio: " + getRatio() + "\n");
    }
}
