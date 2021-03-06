package main.java.net.internetengineering.domain.dealing;

import main.java.net.internetengineering.domain.Customer;
import main.java.net.internetengineering.domain.Transaction;
import main.java.net.internetengineering.domain.dealing.types.GTC;
import main.java.net.internetengineering.domain.dealing.types.ITypeExecutor;
import main.java.net.internetengineering.exception.DataIllegalException;
import main.java.net.internetengineering.logger.CSVFileWriter;
import main.java.net.internetengineering.logger.MyLogger;
import main.java.net.internetengineering.server.StockMarket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by Hamed Ara on 2/18/2016.
 */
public class Instrument {
    private String symbol;
    private Long quantity;
    private List<SellingOffer> sellingOffers;
    private List<BuyingOffer> buyingOffers;

	public Instrument(String symbol,Long quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.sellingOffers = new ArrayList<SellingOffer>();
        this.buyingOffers = new ArrayList<BuyingOffer>();
    }

	public String getSymbol() {

		return symbol;
	}

	public Long getQuantity() {
		return quantity;
	}

	public List<SellingOffer> getSellingOffers() {
		return sellingOffers;
	}

	public List<BuyingOffer> getBuyingOffers() {
		return buyingOffers;
	}

	public void initialOfferLists(){
		initialBuyingOfferList();
		initialSellingOfferList();
	}

	private void initialBuyingOfferList(){
		BuyingOffer b = new BuyingOffer(440L,55L,"KQL","45");
		buyingOffers.add(b);
		b=new BuyingOffer(44L,56L,"ZDF","85");
		buyingOffers.add(b);
	}

	private void initialSellingOfferList(){
		SellingOffer s = new SellingOffer(220L,130L,"GTC","12");
		sellingOffers.add(s);
		s = new SellingOffer(22L,13L,"MTF","11");
		sellingOffers.add(s);
	}

    public void executeSellingByType(MyLogger logger, SellingOffer offer) throws DataIllegalException {
		try {
			System.out.println( GTC.class.getName());
			Class clazz = Class.forName("main.java.net.internetengineering.domain.dealing.types."+offer.getType());
			Object obj= clazz.newInstance();
			if(obj instanceof ITypeExecutor){
				((ITypeExecutor)obj).sellingExecute(logger,offer,sellingOffers,buyingOffers,symbol);
			}
		}catch (ClassNotFoundException ex){
			throw new DataIllegalException("Invalid type");
		}catch (IllegalAccessException ex){
			throw new DataIllegalException("Invalid type");
		}catch (InstantiationException ex){
			throw new DataIllegalException("Invalid type");
		}
        
    }

	public void executeBuyingByType(MyLogger logger, BuyingOffer offer) throws DataIllegalException {
		try {
			Class clazz = Class.forName("domain.dealing.types."+offer.getType());
			Object obj= clazz.newInstance();
			if(obj instanceof ITypeExecutor){
				((ITypeExecutor)obj).buyingExecute(logger,offer,sellingOffers,buyingOffers,symbol);
			}
		}catch (ClassNotFoundException ex){
			throw new DataIllegalException("Invalid type");
		}catch (IllegalAccessException ex){
			throw new DataIllegalException("Invalid type");
		}catch (InstantiationException ex){
			throw new DataIllegalException("Invalid type");
		}

    }


	public static void matchingOffers(MyLogger logger,Boolean basedOnBuyerPrice,
			List<SellingOffer> sellingOffers,List<BuyingOffer>buyingOffers,String symbol,String type){

    	SellingOffer sellingOffer = sellingOffers.get(0);
    	BuyingOffer buyingOffer = buyingOffers.get(0);

    	if(sellingOffer.getPrice() > buyingOffer.getPrice()){
    		logger.info("Order is queued");
    		return;
    	}

    	while(true){
	    	if(sellingOffer.getPrice() <= buyingOffer.getPrice()){
	    		Long buyPrice = basedOnBuyerPrice? buyingOffer.getPrice():sellingOffer.getPrice();
	    		Long buyQuantity = (long) 0 ;
	    		if(buyingOffer.getQuantity() < sellingOffer.getQuantity()){
	    			buyQuantity = buyingOffer.getQuantity();
	    			buyingOffers.remove(0);
	    			sellingOffer.setQuantity("delete", buyQuantity);
	    			sellingOffers.set(0, sellingOffer);
					if(sellingOffer.getQuantity()==0L){
						sellingOffers.remove(0);
					}
	    		}
	    		else{
	    			buyQuantity = sellingOffer.getQuantity();
	    			sellingOffers.remove(0);
	    			buyingOffer.setQuantity("delete", buyQuantity);
	    			buyingOffers.set(0, buyingOffer);
					if(buyingOffer.getQuantity()==0L){
						buyingOffers.remove(0);
					}
	    		}
	    		StockMarket.changeCustomerProperty(sellingOffer, buyingOffer, buyPrice, buyQuantity, symbol);
				Customer seller = StockMarket.getInstance().getCustomer(sellingOffer.getID());
				Customer buyer = StockMarket.getInstance().getCustomer(buyingOffer.getID());
				Transaction t = new Transaction(buyer.getId(),seller.getId(),symbol,type,String.valueOf(buyQuantity),String.valueOf(buyer.getMoney()),
						String.valueOf(seller.getMoney()));
				CSVFileWriter.writeCsvFile(t);
	    		logger.info(sellingOffer.getID()+" sold "+buyQuantity+" shares of "+symbol+" @"+buyPrice+" to "+buyingOffer.getID());
	    	}else
				break;
	    	if(!sellingOffers.isEmpty()&&!buyingOffers.isEmpty()) {
				sellingOffer = sellingOffers.get(0);
				buyingOffer = buyingOffers.get(0);
			}else
				break;
    	}
    }

    public static void sortOfferingListByPrice(List<? extends Offering> offers){
        Collections.sort(offers, new Comparator<Offering>() {
            @Override
            public int compare(Offering o1, Offering o2) {
                return o1.getPrice()>o2.getPrice()?1:-1;
            }
        });
//		return offers;
    }


    public Boolean symbolIsMatched(String symbol){
        return symbol.equals(symbol);
    }
    
    public Boolean HasQuantity(Long count){
    	if(count <= this.quantity)
    		return true;
    	return false;
    }
    
    public void changeQuantity(String type,Long count){
    	if(type.equals("add"))
    		this.quantity += count;
    	else if(type.equals("delete") && HasQuantity(count))
    		this.quantity -= count;
    }
    
}
