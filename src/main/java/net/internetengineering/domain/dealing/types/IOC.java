package main.java.net.internetengineering.domain.dealing.types;

import main.java.net.internetengineering.domain.Customer;
import main.java.net.internetengineering.domain.Transaction;
import main.java.net.internetengineering.domain.dealing.BuyingOffer;
import main.java.net.internetengineering.domain.dealing.SellingOffer;
import main.java.net.internetengineering.logger.CSVFileWriter;
import main.java.net.internetengineering.logger.MyLogger;
import main.java.net.internetengineering.server.StockMarket;

import java.util.List;


public class IOC implements ITypeExecutor {

	@Override
	public void sellingExecute(MyLogger logger, SellingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers, String symbol) {
		if(buyingOffers.isEmpty()){
			logger.info("Order is declined");
			return;
		}
		Long count = offer.getQuantity();
		for (int i = 0; i < buyingOffers.size(); i++) {
			if(buyingOffers.get(i).getPrice()<offer.getPrice())
				break;
			count -= buyingOffers.get(i).getQuantity();
		}
		if(count > 0){
			logger.info("Order is declined");
			return;
		}
		else{
			while(true){
				Long buyPrice = buyingOffers.get(0).getPrice();
				Long buyQuantity = 0L ;
				if(buyingOffers.get(0).getQuantity() <= offer.getQuantity()){
					buyQuantity = buyingOffers.get(0).getQuantity();
					StockMarket.changeCustomerProperty(offer, buyingOffers.get(0), buyPrice, buyQuantity, symbol);

					Customer seller = StockMarket.getInstance().getCustomer(offer.getID());
					Customer buyer = StockMarket.getInstance().getCustomer(buyingOffers.get(0).getID());
					Transaction t = new Transaction(buyer.getId(),seller.getId(),symbol,this.getClass().getName(),String.valueOf(buyQuantity),String.valueOf(buyer.getMoney()),
							String.valueOf(seller.getMoney()));
					CSVFileWriter.writeCsvFile(t);

					logger.info(offer.getID()+" sold "+buyQuantity+" shares of "+symbol+" @"+buyPrice+" to "+buyingOffers.get(0).getID()+"\n");
					buyingOffers.remove(0);
					offer.setQuantity("delete", buyQuantity);
					//sellingOffers.set(0, offer);
				}
				else{
					buyQuantity = offer.getQuantity();
					buyingOffers.get(0).setQuantity("delete", buyQuantity);
					//buyingOffers.set(0, buyingOffers.get(0));
					StockMarket.changeCustomerProperty(offer, buyingOffers.get(0), buyPrice, buyQuantity, symbol);

					Customer seller = StockMarket.getInstance().getCustomer(offer.getID());
					Customer buyer = StockMarket.getInstance().getCustomer(buyingOffers.get(0).getID());
					Transaction t = new Transaction(buyer.getId(),seller.getId(),symbol,this.getClass().getName(),String.valueOf(buyQuantity),String.valueOf(buyer.getMoney()),
							String.valueOf(seller.getMoney()));
					CSVFileWriter.writeCsvFile(t);

					logger.info(offer.getID()+" sold "+buyQuantity+" shares of "+symbol+" @"+buyPrice+" to "+buyingOffers.get(0).getID()+"\n");
					break;
				}


			}
		}
	}

	@Override
	public void buyingExecute(MyLogger logger, BuyingOffer offer, List<SellingOffer> sellingOffers, List<BuyingOffer> buyingOffers,String symbol) {
		if(sellingOffers.isEmpty()){
			logger.info("Order is declined");
			return;
		}
		Long count = offer.getQuantity();
		for (int i = 0; i < sellingOffers.size(); i++) {
			if(sellingOffers.get(i).getPrice()>offer.getPrice())
				break;
			count -= sellingOffers.get(i).getQuantity();
		}
		if(count > 0){
			logger.info("Order is declined");
			return;
		}
		else{
			//count = offer.getQuantity();
			while(true){
				if(offer.getPrice() > sellingOffers.get(0).getPrice()){
					Long buyPrice = offer.getPrice();
					Long buyQuantity = 0L ;
					if(offer.getQuantity() < sellingOffers.get(0).getQuantity()){
						buyQuantity = offer.getQuantity();
						sellingOffers.get(0).setQuantity("delete", buyQuantity);
						//sellingOffers.set(0, sellingOffers.get(0));
						StockMarket.changeCustomerProperty(sellingOffers.get(0), offer, buyPrice, buyQuantity, symbol);
						logger.info(sellingOffers.get(0).getID()+" sold "+buyQuantity+" shares of "+symbol+" @"+buyPrice+" to "+offer.getID()+"\n");
						break;
					}
					else{
						buyQuantity = sellingOffers.get(0).getQuantity();
						StockMarket.changeCustomerProperty(sellingOffers.get(0), offer, buyPrice, buyQuantity, symbol);
						logger.info(sellingOffers.get(0).getID()+" sold "+buyQuantity+" shares of "+symbol+" @"+buyPrice+" to "+offer.getID()+"\n");
						sellingOffers.remove(0);
						offer.setQuantity("delete", buyQuantity);
						//buyingOffers.set(0, offer);

					}

				}
			}
		}
	}
}
