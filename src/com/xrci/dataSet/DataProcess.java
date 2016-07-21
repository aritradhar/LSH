package com.xrci.dataSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DataProcess {
	
	public static void main(String[] args) throws IOException
	{
		Set<String> elements = new HashSet<>();
		BufferedReader br = new BufferedReader(new FileReader("retail.txt"));
		String st = null;
		
		while((st = br.readLine()) != null)
		{
			String firstElement = st.split("#")[0].trim();
			String pre = firstElement.split(" ==> ")[0];
			if(pre.contains(" "))
			{
				String[] elPre = pre.split(" ");
				for(String el : elPre)
					elements.add(el);
			}
			else
				elements.add(pre);
			
			String ant = firstElement.split(" ==> ")[1];
			if(ant.contains(" "))
			{
				String[] elAnt = ant.split(" ");
				for(String el : elAnt)
					elements.add(el);
			}
			else
				elements.add(ant);
			
			System.out.println(pre + " : " + ant);
		}
		
		br.close();
		
		System.out.println(elements.size());
	}

}
