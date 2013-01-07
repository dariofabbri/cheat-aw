package it.dariofabbri.cheataw;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cheat {

	public static void main(String[] args) {

		String passedChars = null;
		if(args.length != 1) {
			System.out.println("Wrong number of arguments, processing antani...");
			passedChars = "antani";
		}
		else {
			passedChars = args[0].trim().toLowerCase();
		}
		List<Character> available = new ArrayList<Character>();
		for(char c : passedChars.toCharArray())
			available.add(c);

		List<String> dict = new ArrayList<String>();
		try {
			InputStream is = Cheat.class.getClassLoader().getResourceAsStream("esp.txt");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = br.readLine()) != null) {
				dict.add(line);
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(String.format("Loaded %d dictionary words.", dict.size()));
		
		
		
		List<String> sequences = new ArrayList<String>();
		
		Cheat cheat = new Cheat();
		for(int i = available.size(); i > 0; --i) {
			List<String> result = new ArrayList<String>();
			cheat.generate(null, available, result, i);
			
			for(String s : result) {
				if(!sequences.contains(s))
					sequences.add(s);
			}
		}

		
		for(String s : sequences) {
			
			if(dict.contains(s))
				System.out.println(s);
		}
	}

	protected void generate(String current, List<Character> available, List<String> sequences, int k) {
		
		if(current != null && current.length() == k) {
			sequences.add(current);
			return;
		}
		
		for(Character c : available) {

			String s = new String();
			if(current != null) {
				s += current;
			}
			s += c;
			
			List<Character> lc = new ArrayList<Character>();
			lc.addAll(available);
			lc.remove(c);

			generate(s, lc, sequences, k);
		}
	}
}
