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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class H {
	
	public static String CHAR_SET = "abcdefghijklmnopqrstuvwxyz";
	String input;
	ArrayList<Double[]> A;
	Double[] vector;
	int k = 0;
	
	public H(int k)
	{
		this.k = k;
		preprocess();
	}
	
	public H(String input, int k)
	{
		this.k = k;
		this.input = input;
		preprocess();
		convert();
	}
	
	public int[] process(String input)
	{
		this.input = input;
		convert();
		return mult();
	}
	
	private void preprocess()
	{
		A = new ArrayList<Double[]>();
		
		Random rand = new Random();
		
		for(int i = 0; i < k; i++)
		{
			Double[] temp = new Double[27];
			for(int j = 0; j < 27; j++)
				temp[j] = rand.nextGaussian();
			A.add(temp);
		}
	}
	
	//construct the vector from the input string
	private void convert()
	{
		this.vector = new Double[27];
		Arrays.fill(this.vector, 0.0);
		
		for(int i = 0; i < this.input.length(); i++)
		{
			int index = this.input.charAt(i) - 97;
			this.vector[index] = (double) 1;
		}
		
		/*int tot_ones = 0;
		
		for(int i = 0; i < this.input.length(); i++)
			if(this.vector[i] == (double) 1)
				tot_ones++;*/
		
		for(int i = 0; i < this.input.length(); i++)
		{
			this.vector[i] = (double) this.vector[i] / Math.sqrt((double)26);
			//System.out.println(this.vector[i]);
		}
		
		double lIndex = 0;
		for(double val : this.vector)
			lIndex += Math.pow(val, 2);
		lIndex = Math.sqrt((double)1) - Math.sqrt(lIndex);
		this.vector[26] = lIndex;
		//System.out.println(lIndex);
	}
	
	private int[] mult()
	{
		int[] out = new int[k];
		
		for(int i = 0 ; i < k; i++)
		{
			Double[] A_matrix = this.A.get(i);
			double o = vectorMultCaller(A_matrix, vector);
			if(o < 0)
				out[i] = 0;
			else
				out[i] = 1;
		}
		
		return out;
	}
	
	private Double vectorMultCaller(Double[] d1, Double[] d2)
	{
		double out = 0;
		Double[] D = this.vectorMult(d1, d2);
		for(double d : D)
			out += d;
		
		return out;
	}
	private Double[] vectorMult(Double[] d1, Double[] d2)
	{
		Double[] out = new Double[27];
		
		for(int i = 0; i < 27; i++)
			out[i] = d1[i] * d2[i];
		
		return out;
	}
	public static long convert(int[] arr)
	{
		long out = 0x0;
		for(int i = 0; i < arr.length; i++)
		{
			if(!(arr[i] !=0 || arr[i] != 1))
				throw new RuntimeException("Error array contain");
			
			out |= arr[i] << i;
			
		}
		return out;
	}
	
	public static void main(String[] args) 
	{
		H h = new H(20);
		System.out.println(convert(h.process("abcd")));
		System.out.println(convert(h.process("abc")));
		System.out.println(convert(h.process("acb")));
		System.out.println(convert(h.process("xyz")));
	}
}
