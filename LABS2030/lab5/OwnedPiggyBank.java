package eecs2030.lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.List;

/**
 * A class representing a piggy bank that has an owner. 
 * A piggy bank owns a collection (or possibly collections) of coins,
 * but does not own the coins themselves. In other words,
 * the piggy bank and its collection of coins form a composition.
 * 
 * <p>
 * Only the owner of the piggy bank is able to remove coins
 * from the piggy bank. The piggy bank does own its owner.
 * In other words, the piggy bank and its owner form an
 * aggregation.
 */

// Alp Baran Sirek
// 217329251

public class OwnedPiggyBank {
	/*
	 * YOU NEED A FIELD HERE TO HOLD THE COINS OF THIS PIGGY BANK
	 */

	List<Coin> coinsOfOwner;
	/**
	 * The owner of this piggy bank.
	 */
	private Owner owner;

	/**
	 * Initializes this piggy bank so that it has the specified owner
	 * and its collection of coins is empty.
	 * 
	 * @param owner
	 *            the owner of this piggy bank
	 */
	public OwnedPiggyBank(Owner owner) {
		this.owner = owner; 
		this.coinsOfOwner = new ArrayList<>();
	}

	/**
	 * Initializes this piggy bank by copying another piggy bank. This piggy
	 * bank will have the same owner and the same number and type of coins as
	 * the other piggy bank.
	 * 
	 * @param other
	 *            the piggy bank to copy
	 */
	public OwnedPiggyBank(OwnedPiggyBank other) {
		this.owner = other.getOwner();
		ArrayList<Coin> toBeCopied = new ArrayList<>(other.coinsOfOwner);
		this.coinsOfOwner = toBeCopied;
		
	}

	/**
	 * Returns the owner of this piggy bank.
	 * 
	 * <p>
	 * This method is present only for testing purposes. Returning
	 * the owner of this piggy bank allows any user to remove coins
	 * from the piggy bank (because any user can get the owner
	 * of this piggy bank)!
	 * 
	 * @return the owner of this piggy bank
	 */
	public Owner getOwner() {
		// ALREADY IMPLEMENTED; DO NOT MODIFY
		return this.owner;
	}
	
	/**
	 * Allows the current owner of this piggy bank to give this
	 * piggy bank to a new owner.
	 * 
	 * @param currentOwner the current owner of this piggy bank
	 * @param newOwner the new owner of this piggy bank
	 * @throws IllegalArgumentException if currentOwner is not the
	 * current owner of this piggy bank
	 */
	public void changeOwner(Owner currentOwner, Owner newOwner) {
			if(currentOwner.equals(this.owner)) {
				this.owner = newOwner;
			}else {
				throw new IllegalArgumentException();
			}
	}

	/**
	 * Adds the specified coins to this piggy bank.
	 * 
	 * @param coins
	 *            a list of coins to add to this piggy bank
	 */
	public void add(List<Coin> coins) {
		/*
		for(Coin coinInList: coins) {
			Coin newCoin = new Coin(coinInList);
			this.coinsOfOwner.add(newCoin);
			*/
		for(Coin coinInList: coins) {
			this.coinsOfOwner.add(coinInList);
			}
		//Collections.addAll(this.coinsOfOwner, coins);
		}

	/**
	 * Returns true if this piggy bank contains the specified coin, and false
	 * otherwise.
	 * 
	 * @param coin
	 *            a coin
	 * @return true if this piggy bank contains the specified coin, and false
	 *         otherwise
	 */
	public boolean contains(Coin coin) {
		for(Coin coinTest : this.coinsOfOwner) {
			if (coinTest == coin){
				return true;
			}
		}
		return false;
		
	}

	/**
	 * Allows the owner of this piggy bank to remove a coin equal to the value
	 * of the specified coin from this piggy bank.
	 * 
	 * <p>
	 * If the specified user is not equal to the owner of this piggy bank,
	 * then the coin is not removed from this piggy bank, and null is returned.
	 * 
	 * @param user
	 *            the person trying to remove the coin
	 * @param coin
	 *            a coin
	 * @return a coin equal to the value of the specified coin from this piggy
	 *         bank, or null if user is not the owner of this piggy bank
	 * @pre. the piggy bank contains a coin equal to the specified coin
	 */
	public Coin remove(Owner user, Coin coin) {
		
		if (user.equals(this.owner)) {
			for(Coin individualCoin : this.coinsOfOwner) {
				if(individualCoin == coin) {
					return coin;
				}
			}
		}
		return null;
	}

	/**
	 * Allows the owner of this piggy bank to remove
	 * the smallest number of coins whose total value in cents is equal
	 * to the specified value in cents from this piggy bank.
	 * 
	 * <p>
	 * Returns the empty list if the specified user is not equal to
	 * the owner of this piggy bank.
	 * 
	 * @param user
	 *            the person trying to remove coins from this piggy bank
	 * @param value
	 *            a value in cents
	 * @return the smallest number of coins whose total value in cents is equal
	 *         to the specified value in cents from this piggy bank 
	 * @pre. the piggy bank contains a group of coins whose total value is equal
	 *         to specified value
	 */
	public List<Coin> removeCoins(Owner user, int value) {
		ArrayList<Coin> toBeReturned = new ArrayList<>();
		if (user.equals(this.owner)) {
			Collections.sort(this.coinsOfOwner); // sort so that highest value is last index
			int i = this.coinsOfOwner.size() - 1; // get the last index of the highest value
			while(value != 0) { // while our value is higher than 0
				/*if we substract the highest value of our list from value, 
				* its is bigger equal zero, add that to the to be returned list
				* if not just decrement the index so that it goes to lower valued
				* coins.
				*/ 
				if(value - this.coinsOfOwner.get(i).getValue() >= 0) {
					value = value - this.coinsOfOwner.get(i).getValue();
					toBeReturned.add(this.coinsOfOwner.get(i));
					i--;
				}else{
					i--;
				}
			}
		}else {
			return new ArrayList<Coin>(); // return empty if user is not the owner
		}
			return toBeReturned;
	}

	/**
	 * Returns a deep copy of the coins in this piggy bank. The returned list
	 * has its coins in sorted order (from smallest value to largest value;
	 * i.e., pennies first, followed by nickels, dimes, quarters, loonies, and
	 * toonies).
	 * 
	 * @return a deep copy of the coins in this piggy bank
	 */
	public List<Coin> deepCopy() {
		ArrayList<Coin> copyCoins = new ArrayList<>();
		for(Coin coins: this.coinsOfOwner) {
			Coin newCoin = new Coin(coins);
			copyCoins.add(newCoin);
		}
		Collections.sort(copyCoins);
		return copyCoins;

	}
}
