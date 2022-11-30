package knapsack;

public class Item
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
}
