package org.ellie.carscatalog.collections;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class Vector<T> implements RandomAccess, Cloneable, Serializable {

	private static final long serialVersionUID = -6643814296443127595L;
	protected T[] items;
	protected int arraySize;
	protected int maxCap;

	@SuppressWarnings("unchecked")
	public Vector(int initialCapacity) {

		super();

		if (initialCapacity < 0) {

			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}

		this.items = (T[]) new Object[initialCapacity];
		this.arraySize = 0;
		this.maxCap = initialCapacity;
	}

	public Vector() {
		this(10);
	}

	public Enumeration<T> elements() {

		return new Enumeration<T>() {

			private int i = 0;

			public boolean hasMoreElements() {

				//System.out.println("hasMoreElements: " + i + " " + arraySize);
				return i < arraySize;
			}

			public T nextElement() {

				if (i >= arraySize) {

					throw new NoSuchElementException();
				}
				return items[i++];
			}
		};
	}

	private void checkBoundInclusive(int index) {

		if (index > arraySize) {

			throw new ArrayIndexOutOfBoundsException(index + " > " + arraySize);
		}
	}
	
	private void checkBoundExclusive(int index) {
		
		if (index >= arraySize) {

			throw new ArrayIndexOutOfBoundsException(index + " >= " + arraySize);
		}
	}

	public void ensureCapacity(int minCapacity) {

		if (items.length >= minCapacity)
			return;

		int newCapacity;
		newCapacity = items.length * 2;

		T[] newArray = (T[]) new Object[Math.max(newCapacity, minCapacity)];

		System.arraycopy(items, 0, newArray, 0, arraySize);
		items = newArray;
	}
	
	public T elementAt(int index) {
		
		checkBoundExclusive(index);
		return items[index];
	}

	public void insertAt(T obj, int index) {

		checkBoundInclusive(index);

		if (arraySize == items.length) {

			ensureCapacity(arraySize + 1);
		}

		System.arraycopy(items, index, items, index + 1, arraySize - index);
		arraySize++;
		items[index] = obj;
	}
	
	public void append(T obj) {
		
		insertAt(obj, (arraySize > 0) ? arraySize - 1 : 0);
	}

	public T removeAt(int index) {

		checkBoundInclusive(index);

		T temp = items[index];

		arraySize--;
		if (index < arraySize)
			System.arraycopy(items, index + 1, items, index, arraySize - index);
		items[arraySize] = null;

		return temp;
	}

	public void clear() {
		
		if (arraySize == 0)
			return;

		Arrays.fill(items, 0, arraySize, null);
		arraySize = 0;
	}
}
