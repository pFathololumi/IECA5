package main.java.net.internetengineering.domain.dealing.types;

import main.java.net.internetengineering.domain.dealing.BuyingOffer;
import main.java.net.internetengineering.domain.dealing.SellingOffer;
import main.java.net.internetengineering.logger.MyLogger;

import java.util.List;


public interface ITypeExecutor {
	
	public void sellingExecute(MyLogger logger, SellingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers, String symbol);
	public void buyingExecute(MyLogger logger, BuyingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers,String symbol);
	
}
