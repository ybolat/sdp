package iterator;

import module.Store;

import java.util.Iterator;
import java.util.List;

public class StoreIterator implements Iterator<Store>{

	private final List<Store> store;
	int pos;
	
	public StoreIterator(List<Store> store){
		this.store = store;
	}

	@Override
	public boolean hasNext() {
		if(pos >= store.size() || store.get(pos) == null)
			return false;
		return true;
	}

	@Override
	public Store next() {
		return store.get(pos++);
	}
}
