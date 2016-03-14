package ir.ramtung.polling;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PollRepository {

	private Map<String, Poll> storage;
	private int pollCount;
	
	private static PollRepository theRepository = new PollRepository();
	public static PollRepository getRepository() {
		return theRepository;
	}
	
	PollRepository() {
		storage = new HashMap<String, Poll>();
		pollCount = 0;
	}
	
	public void store(Poll poll) {
		poll.setCode(Integer.toString(pollCount + 1));
		pollCount++;
		storage.put(poll.getCode(), poll);
	}

	public Poll findByCode(String code) {
		return storage.get(code);
	}
	
	public Collection<Poll> findAll() {
		return storage.values();
	}
}
