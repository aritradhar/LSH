package com.xrci.dataSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataProcess {
	
	public static void main(String[] args) throws IOException
	{
		String charSet = "abcdefghjklmnopqrstuvwxyz";
		Set<String> elements = new HashSet<>();
		Map<String, Character> elementMap = new HashMap<>();
		
		FileWriter fw = new FileWriter("acc_char.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("acc.txt"));
		String st = null;
		
		while((st = br.readLine()) != null)
		{
			String firstElement = st.split("#")[0].trim();
			String pre = firstElement.split(" ==> ")[0];
			if(pre.contains(" "))
			{
				String[] elPre = pre.split(" ");
				for(String el : elPre)
				{
					elements.add(el);
					if(!elementMap.containsKey(el))
					{
						char charInd = charSet.charAt(elementMap.size());
						elementMap.put(el, charInd);
						fw.append(charInd);
					}
					else
						fw.append(elementMap.get(el));
				}
			}
			else
			{
				elements.add(pre);
				if(!elementMap.containsKey(pre))
				{
					char charInd = charSet.charAt(elementMap.size());
					elementMap.put(pre, charInd);
					fw.append(charInd);
				}
				else
					fw.append(elementMap.get(pre));
				
			}
			
			fw.append("->");
			
			String ant = firstElement.split(" ==> ")[1];
			if(ant.contains(" "))
			{
				String[] elAnt = ant.split(" ");
				for(String el : elAnt)
				{
					elements.add(el);
					
					if(!elementMap.containsKey(el))
					{
						char charInd = charSet.charAt(elementMap.size());
						elementMap.put(el, charInd);
						fw.append(charInd);
					}	
					else
						fw.append(elementMap.get(el));
					
				}
			}
			else
			{
				elements.add(ant);
				
				if(!elementMap.containsKey(ant))
				{
					char charInd = charSet.charAt(elementMap.size());
					elementMap.put(ant, charInd);
					fw.append(charInd);
				}
				else
					fw.append(elementMap.get(ant));
			}
			
			fw.append("\n");
			System.out.println(pre + " : " + ant);
		}
		
		br.close();
		fw.close();
		System.out.println(elements.size());
	}

}
