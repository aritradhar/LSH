//*************************************************************************************
//*********************************************************************************** *
//author Aritra Dhar 																* *
//Research Engineer																  	* *
//Xerox Research Center India													    * *
//Bangalore, India																    * *
//--------------------------------------------------------------------------------- * * 
///////////////////////////////////////////////// 									* *
//The program will do the following:::: // 											* *
///////////////////////////////////////////////// 									* *
//version 1.0 																		* *
//*********************************************************************************** *
//*************************************************************************************


package com.xrci.hash;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Table {

	HashMap<Long, ArrayList<String>> Table;
	H h;

	public Table(int k, int maxlen) throws UnsupportedEncodingException
	{
		this.Table = new HashMap<>();
		this.h = new H(k); 
	}

	public int size()
	{
		return this.Table.size();
	}

	public void insert(String P) throws UnsupportedEncodingException
	{
		long hash = H.convert(h.process(P));

		ArrayList<String> str = null;
		if(this.Table.containsKey(hash))
		{
			str = this.Table.get(hash);
			str.add(P);
		}
		else
		{
			str = new ArrayList<>();
			str.add(P);
			this.Table.put(hash, str);
		}	
	}

	public ArrayList<String> query(String query)
	{
		long hash = H.convert(h.process(query));
		if(!this.Table.containsKey(hash))
		{
			System.err.println(query + " not exists");
			return null;
		}

		return this.Table.get(hash);
	}

	public static void main(String[] args) throws UnsupportedEncodingException 
	{
		String charSet = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		int rep_factor = 5;
		Table []t = new Table[rep_factor];
		int databaseSize = 1000000;
		int hash_len = 32;
		int len = 3;

		char[][] c = new char[databaseSize][len];
		for(int i = 0; i < databaseSize; i++)
		{
			c[i] = new char[len];
			for(int j = 0; j < len; j ++)
				c[i][j] = charSet.charAt(rand.nextInt(26));
		}
		
		
		long tot = 0;
		for(int rep = 0; rep < rep_factor; rep++)
		{
			t[rep] = new Table(hash_len, len);
			for(int i = 0; i < databaseSize; i++)
				t[rep].insert(new String(c[i]));

			System.out.println(t[rep].size());
			long st_q = System.currentTimeMillis();
			System.out.println(t[rep].query("abc"));
			long en_q = System.currentTimeMillis();;
			System.out.println("Query time : " + (en_q - st_q) + " ms");
			tot += (en_q - st_q);
		}
		
		System.out.println("Total runtime : " + tot + " ms");
	}
	
}


/*for(int i = 0; i < 10000; i++)
{
	char[] c = new char[len];
	for(int j = 0; j < len; j ++)
		c[j] = charSet.charAt(rand.nextInt(26));

	t[rep].insert(new String(c));
}*/