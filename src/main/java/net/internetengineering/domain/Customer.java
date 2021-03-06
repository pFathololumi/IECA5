package main.java.net.internetengineering.domain;

import main.java.net.internetengineering.domain.dealing.Instrument;
import main.java.net.internetengineering.domain.dealing.Offering;
import main.java.net.internetengineering.domain.dealing.TransactionType;

import java.util.ArrayList;
import java.util.List;


public class Customer {
	private String id;
	private String name;
	private String family;
	private Account customerAccount;
	private List<Instrument> instruments;
	
	public Customer(String id,String n,String f){
		this.id = id;
		this.name = n;
		this.family = f;
		this.customerAccount = new Account();
		this.instruments = new ArrayList<Instrument>();
	}

	public String getId() {
		return id;
	}

	public void executeTransaction(TransactionType type, Long amount){
		customerAccount.executeTransaction(type,amount);
	}
	
	public Long getMoney(){
		return this.customerAccount.getBalance();
	}
	
	public Boolean hasEnoughMoney(Long amount){
		return customerAccount.isEnoughMoney(amount);
	}
	
	public Boolean hasEnoughStock(String instrumentName,Offering offer){
		for(Instrument i : instruments){
			if(i.symbolIsMatched(instrumentName))
				if(i.HasQuantity(offer.getQuantity()))
					return true;	
		}
		return false;
	}

	public void updateInstruments(String type,Long count,String name){
		boolean flag = false;
		for(Instrument i : instruments){
			if(i.symbolIsMatched(name)){
				i.changeQuantity(type, count);
				flag = true;
				break;
			}
		}
		if(!flag){
			instruments.add(new Instrument(name, count));
		}
	}
}

