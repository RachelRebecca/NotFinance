package transactions;

import java.util.Date;


public record Transaction(Date date, String ticker, double quantity, double price)
{
}

