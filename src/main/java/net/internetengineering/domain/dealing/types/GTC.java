package main.java.net.internetengineering.domain.dealing.types;

import main.java.net.internetengineering.domain.dealing.BuyingOffer;
import main.java.net.internetengineering.domain.dealing.Instrument;
import main.java.net.internetengineering.domain.dealing.SellingOffer;
import main.java.net.internetengineering.logger.MyLogger;

import java.util.List;

public class GTC implements ITypeExecutor {

	@Override
	public void sellingExecute(MyLogger logger, SellingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers, String symbol) {
		sellingOffers.add(offer);
		Instrument.sortOfferingListByPrice(sellingOffers);
		if (buyingOffers.isEmpty())
			logger.info("Order is queued");
		else
			Instrument.matchingOffers(logger, true,sellingOffers,buyingOffers,symbol,this.getClass().getName());
	}

	@Override
	public void buyingExecute(MyLogger logger, BuyingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers, String symbol) {
		buyingOffers.add(offer);
		Instrument.sortOfferingListByPrice(buyingOffers);
		if (sellingOffers.isEmpty())
			logger.info("Order is queued");
		else
			Instrument.matchingOffers(logger, true,sellingOffers,buyingOffers,symbol,this.getClass().getName());
	}

	
}
