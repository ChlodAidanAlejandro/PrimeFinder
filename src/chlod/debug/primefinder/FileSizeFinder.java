package chlod.debug.primefinder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSizeFinder implements Runnable {

	@Override
	public void run() {
		PrimeFinder.output("FileSizerThread started.");
		final long starttime = System.currentTimeMillis();
		long lastloop = System.currentTimeMillis();
		long primesize = PrimeFinder.primefile.length();
		long benchsize = PrimeFinder.benchfile.length();
		long lastprimes = PrimeFinder.primes;
		BufferedWriter benchwr;
		try {
			benchwr = new BufferedWriter(new FileWriter(PrimeFinder.benchfile));
			while (PrimeFinder.passes != PrimeFinder.limit && PrimeFinder.primefile.length() <= PrimeFinder.bytelimit) {
				if (System.currentTimeMillis() - lastloop >= 1000) {
					//(correct * 100.0f) / questionNum
					double primesfiles = (Double.parseDouble(String.valueOf(PrimeFinder.primefile.length()))*100.0D) / Double.parseDouble(String.valueOf(PrimeFinder.bytelimit));
					double primesdone = (Double.parseDouble(String.valueOf(PrimeFinder.passes))*100.0D) / Double.parseDouble(String.valueOf(PrimeFinder.limit));
					PrimeFinder.output("PRIME FILE SIZE: " + PrimeFinder.primefile.length() + "B, " + String.format("%.2f", primesfiles) + "% byte limit reached. " + (PrimeFinder.bytelimit-PrimeFinder.primefile.length()) + "B left. " + 
					"PASSED NUMBERS: " + PrimeFinder.passes + ", " + String.format("%.2f", primesdone) + "% number limit reached. " + (PrimeFinder.limit-PrimeFinder.passes) + " left.");
					
					//file writing
					benchwr.append(PrimeFinder.passes + " numbers scanned"); benchwr.newLine();
					benchwr.append(PrimeFinder.primes + " found. " + (PrimeFinder.primes-lastprimes) + " primes found this second."); benchwr.newLine();
					benchwr.append(System.currentTimeMillis() - lastloop + "ms since last loop"); benchwr.newLine();
					benchwr.append(System.currentTimeMillis() - starttime + "ms since bench start"); benchwr.newLine();
					benchwr.newLine();
					benchwr.append("Benchmark file size: " + PrimeFinder.benchfile.length() + "B (" + (PrimeFinder.benchfile.length() - benchsize) + " B this cycle)"); benchwr.newLine();
					benchwr.append("Primes file size: " + PrimeFinder.primefile.length() + "B (" + (PrimeFinder.primefile.length() - primesize) + " B this cycle)"); benchwr.newLine();
					benchwr.newLine();
					benchwr.flush();
		//			//console
		//			output(passes + " numbers scanned");
		//			output(primes + " found. " + (primes-lastprimes) + " primes found this second.");
		//			output(System.currentTimeMillis() - lastloop + "ms since last cycle"); 
		//			output(System.currentTimeMillis() - starttime + "ms since bench start"); 
		//			output("");
		//			output("Benchmark file size: " + benchfile.length() + "B (" + (benchfile.length() - benchsize) + " B this cycle)"); 
		//			output("Primes file size: " + primefile.length() + "B (" + (primefile.length() - primesize) + " B this cycle)"); 
		//			output("");
					//re value
					lastprimes = PrimeFinder.primes;
					primesize = PrimeFinder.primefile.length();
					benchsize = PrimeFinder.benchfile.length();
					lastloop = System.currentTimeMillis();
				}
			}
			
		} catch (IOException e) {
			PrimeFinder.output("Error!");
			e.printStackTrace();
		}
	}

	
	
}
