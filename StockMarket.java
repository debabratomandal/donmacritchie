/**
 * 
 */
package stockmktpkg;
import java.util.Scanner;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
public class StockMarket {
	public static void main(String[] args) throws ArithmeticException {

		//Generic variables
		String stockSymbolType[] = {"TEA", "POP", "ALE", "GIN", "JOE" };
		int[] lastDiv = {0, 8, 23, 8, 13};
		int[] parVal = {100, 100, 60, 100, 250};
		Scanner stockSymbolInput = new Scanner(System.in);
		Scanner shareQuantity = new Scanner(System.in);
		Scanner buySellInd = new Scanner(System.in);
		Scanner stockPrice = new Scanner(System.in);

		//**to calculate Geometric Mean
		Scanner stockPriceTea = new Scanner(System.in);
		Scanner stockPricePop = new Scanner(System.in);
		Scanner stockPriceAle = new Scanner(System.in);
		Scanner stockPriceGin = new Scanner(System.in);
		Scanner stockPriceJoe = new Scanner(System.in);
		double geoMeanTemp = 1.0;

		//**to calculate Volume weighted stock price in last 15 mins
		int[] tradePrices = {10,20,30,40,50};
		int[] quantity = {50,100,200,250,270};
		double tradeSum = 0;
		double quanSum = 0;
		double quanProduct=0;
		double vwstockPrice = 0;

		String validSymbol = "";
		float divYield=0;
		float priceEarnings = 0;
		int oneDimPos=0;


		try {
			Date date= new Date();
			long stocktime = date.getTime();
			Timestamp stockTraded = new Timestamp(stocktime);

			System.out.println("Please Enter Stock Symbol: "); 
			String masterSymbol = stockSymbolInput.nextLine();

			//validates the stock symbol

			for (int i=0; i<stockSymbolType.length; i++) {

				if ((masterSymbol.toUpperCase().equals(stockSymbolType[i]))) {

					validSymbol = "true";
					oneDimPos = i;
					break;

				} 

			}

			if (!validSymbol.contentEquals("true")) {

				System.out.println("Invalid Stock Symbol. Please Try Again!");
			}

			//Dividend Yield
			else {

				System.out.println("Please Enter Stock Price: ");
				int price = stockPrice.nextInt();
				if (price==0) {

					System.out.println("Price cannot be zero. Please try again");

				}

				else {

					if(masterSymbol.toUpperCase().contentEquals("GIN")) {

						divYield = (float) ((((float)parVal[oneDimPos])/((float)price))*0.02);

					}

					else {

						divYield = ((float)lastDiv[oneDimPos])/price;

					}


				}
				System.out.println("Dividend Yield Is: " + divYield);

				//P/E Ratio

				if (divYield==0) {
					System.out.println("Price-To-Earnings is not applicable as dividend yield is zero.");	

				} else {

					priceEarnings = ((float)price)/divYield;
					System.out.println("Price-To-Earnings Is: " + priceEarnings);
				}


				//Record a trade
				System.out.println("Please Enter Quantity Of Shares: ");
				int sharequant= shareQuantity.nextInt();
				System.out.println("Please Enter Bought Or Sold (B/S Only): ");
				String bsInd = buySellInd.nextLine().toUpperCase();
				System.out.println("Trade for Stock Symbol " + masterSymbol.toUpperCase() + " completed at " + stockTraded + "." + " " + sharequant + " Number of "
						+ "shares bought/sold" + "(" + bsInd + ")" + "." + " Traded price Was : " +  price); 


				//GBCE All Share Index

				System.out.println("Please Enter Stock Price For:TEA ");
				double stTea= stockPriceTea.nextDouble();
				System.out.println("Please Enter Stock Price For:POP ");
				double stPop= stockPricePop.nextDouble();
				System.out.println("Please Enter Stock Price For:ALE ");
				double stAle= stockPriceAle.nextDouble();
				System.out.println("Please Enter Stock Price For:Gin ");
				double stGin= stockPriceGin.nextDouble();
				System.out.println("Please Enter Stock Price For:JOE ");
				double stJoe= stockPriceJoe.nextDouble();
				double n = 1/5;
				geoMeanTemp= geoMeanTemp*(stTea*stPop*stAle*stGin*stJoe);
				double geoMean = Math.pow(geoMeanTemp, n);
				System.out.println("GBCE All Share Index Is: " + geoMean);


				//Volume Weighted Stock Price
				for (int x=0; x<tradePrices.length; x++) {

					tradeSum =(double) tradeSum + ((double)tradePrices[x]);
					quanSum = (double) quanSum + ((double)quantity[x]);
					quanProduct = (double) quanProduct*((double)quantity[x]);

				}

				vwstockPrice = (double) ((double)tradeSum*quanProduct)/((double)quanSum);
				System.out.println("Volume Weighted Stock Price Is: " + vwstockPrice);		

			}

			stockSymbolInput.close();
			shareQuantity.close();
			buySellInd.close();
			stockPrice.close();
			stockPriceTea.close();
			stockPricePop.close();
			stockPriceAle.close();
			stockPriceGin.close();
			stockPriceJoe.close();
		}
		catch(Exception exp) {

			System.out.println(exp);

		}
	}



}
