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


package com.xrci.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Process 
{
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		String str = new String();
		String temp;
		Set<String> strSet = new HashSet<>();
		Map<String, Character> itemMap = new HashMap<String, Character>();
		
		List<HashSet<String>> assocs = new ArrayList<>();
		List<String> modAssocs = new ArrayList<>();
		
		int counter = 0;
		while((str = br.readLine()) != null)
		{
			counter++;
			temp = str.substring(str.indexOf("[") + 1, str.indexOf("]")).replaceAll("=t", "").replaceAll("total=high", "").replaceAll(" ", "").trim();
			String[] temps = temp.split(",");

			
			HashSet<String> hs = new HashSet<>();
			for(String s : temps)
			{
				if(s.length() == 0)
					continue;
				
				
				hs.add(s);
				strSet.add(s);
			}
			assocs.add(hs);
			
			System.out.println(temp);
		}
		
		int i = 0;
		for(String string : strSet)
		{
			char ch = (char) (i + 97);
			itemMap.put(string, ch);
			i++;
		}
		
		for(HashSet<String> hs : assocs)
		{
			char[] chs = new char[hs.size()];
			int ind = 0;
			for(String ants : hs)
			{
				chs[ind++] = itemMap.get(ants);
			}
			modAssocs.add(new String(chs));
		}
		
		//System.out.println(strSet.size());
		//System.out.println(counter);
		br.close();
	}

}
