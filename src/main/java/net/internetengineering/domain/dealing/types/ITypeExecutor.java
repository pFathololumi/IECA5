package main.java.net.internetengineering.domain.dealing.types;

import main.java.net.internetengineering.domain.dealing.BuyingOffer;
import main.java.net.internetengineering.domain.dealing.SellingOffer;

import java.io.PrintWriter;
import java.util.List;


public interface ITypeExecutor {
	
	public void sellingExecute(PrintWriter out, SellingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers, String symbol);
	public void buyingExecute(PrintWriter out, BuyingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers,String symbol);
	
}
