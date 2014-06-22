package joazlazer.plugins.nebulazrun.util;

import java.util.ArrayList;
import java.util.Queue;

public class BasicQueue<T> extends ArrayList<T> implements Queue<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public T element() {
		if(size() > 0) {
			return peek();
		}
		else return null;
	}

	@Override
	public boolean offer(T arg0) {
		try {
			if(arg0 == null) throw new IllegalArgumentException();
			add(arg0);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public T peek() {
		return size() > 0 ? get(0) : null;
	}

	@Override
	public T poll() {
		if(peek() == null) return null;
		T item = peek();
		this.remove(item);
		return item;
	}

	@Override
	public T remove() {
		if(size() > 0) {
			return poll();
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public boolean enqueue(T arg0) {
		return offer(arg0);
	}
	
	public T dequeue() {
		return poll();
	}
}
