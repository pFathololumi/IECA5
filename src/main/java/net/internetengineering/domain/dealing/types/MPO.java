package main.java.net.internetengineering.domain.dealing.types;

import main.java.net.internetengineering.domain.dealing.BuyingOffer;
import main.java.net.internetengineering.domain.dealing.Instrument;
import main.java.net.internetengineering.domain.dealing.SellingOffer;
import main.java.net.internetengineering.logger.MyLogger;

import java.util.List;


public class MPO implements ITypeExecutor {

	@Override
	public void sellingExecute(MyLogger logger, SellingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers, String symbol) {
		Long count = offer.getQuantity();
    	for (int i = 0; i < buyingOffers.size(); i++) {
			count -= buyingOffers.get(i).getQuantity();
		}
    	if(count > 0){
    		logger.info("Order is declined");
    		return;
    	}
		offer.setPrice(0L);
		sellingOffers.add(offer);
    	Instrument.sortOfferingListByPrice(sellingOffers);
    	Instrument.matchingOffers(logger,true,sellingOffers,buyingOffers,symbol,this.getClass().getName());
	}

	@Override
	public void buyingExecute(MyLogger logger, BuyingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers,String symbol) {
		Long count = offer.getQuantity();
    	for (int i = 0; i < sellingOffers.size(); i++) {
			count -= sellingOffers.get(i).getQuantity();
		}
    	if(count > 0){
    		logger.info("Order is declined");
    		return;
    	}
		offer.setPrice(Long.MAX_VALUE);
    	buyingOffers.add(offer);
    	Instrument.sortOfferingListByPrice(buyingOffers);
    	Instrument.matchingOffers(logger,false,sellingOffers,buyingOffers,symbol,this.getClass().getName());
	}

}
