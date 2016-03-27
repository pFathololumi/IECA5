package main.java.net.internetengineering.domain.dealing.types;

import main.java.net.internetengineering.domain.dealing.BuyingOffer;
import main.java.net.internetengineering.domain.dealing.Instrument;
import main.java.net.internetengineering.domain.dealing.SellingOffer;

import java.io.PrintWriter;
import java.util.List;

public class GTC implements ITypeExecutor {

	@Override
	public void sellingExecute(PrintWriter out, SellingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers, String symbol) {
		sellingOffers.add(offer);
		Instrument.sortOfferingListByPrice(sellingOffers);
		if (buyingOffers.isEmpty())
			out.println("Order is queued");
		else
			Instrument.matchingOffers(out, true,sellingOffers,buyingOffers,symbol);
	}

	@Override
	public void buyingExecute(PrintWriter out, BuyingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers,String symbol) {
		buyingOffers.add(offer);
		Instrument.sortOfferingListByPrice(buyingOffers);
		if (sellingOffers.isEmpty())
			out.println("Order is queued");
		else
			Instrument.matchingOffers(out, true,sellingOffers,buyingOffers,symbol);
	}

	
}
