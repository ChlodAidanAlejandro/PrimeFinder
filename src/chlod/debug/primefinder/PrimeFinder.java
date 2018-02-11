package chlod.debug.primefinder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrimeFinder {
	
	public static File benchfile = new File("benchmark.txt");
	public static File primefile = new File("primes.txt");
	public static long primes = 0;
	public static long passes = 0;
	public static long current = 2;
	public static boolean debug = false;
	public static long limit = 0;
	public static long bytelimit = Long.MAX_VALUE;
	
	public static void main(String[] args) {
		if (args.length == 2) {
			try {
				limit = Long.parseLong(args[0]);
				bytelimit = Long.parseLong(args[1]);
			} catch (NumberFormatException e) {
				output("Invalid arguments!");
				output("java -jar <filename>.jar <long : integer limit> <long : file size limit>");
				System.exit(0);
			}
		} else {
			output("Wrong argument count!");
			output("java -jar <filename>.jar <long : integer limit> <long : file size limit>");
			System.exit(0);
		}
		try {
			BufferedWriter benchwr = new BufferedWriter(new FileWriter(benchfile));
			BufferedWriter primewr = new BufferedWriter(new FileWriter(primefile));
			if (benchfile.isDirectory()) crash("Benchmark file (benchmark.txt) is a directory! Delete this before restarting.");
			if (primefile.isDirectory()) crash("Character Map file (mapfile.txt) is a directory! Delete this before restarting.");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			benchwr.append("PRIME FINDER - run " + dateFormat.format(date)); benchwr.newLine();
			primewr.append("PRIME FINDER - run " + dateFormat.format(date)); primewr.newLine();
			benchwr.flush();
			primewr.flush();
			
			primes = 0;
			passes = 0;
			current = 2;
			debug = false;
			if (debug) {
				if (isPrime(Long.parseLong("102372"))) {
					output("PRIME: 102372");
				} else {
					output("NON-PRIME: 102372");
				}
				if (isPrime(Long.parseLong("102341"))) {
					output("PRIME: 102341");
				} else {
					output("NON-PRIME: 102341");
				}
				if (isPrime(Long.parseLong("102343"))) {
					output("PRIME: 102343");
				} else {
					output("NON-PRIME: 102343");
				}
				if (isPrime(Long.parseLong("102357"))) {
					output("PRIME: 102357");
				} else {
					output("NON-PRIME: 102357");
				}
				if (isPrime(Long.parseLong("102389"))) {
					output("PRIME: 102389");
				} else {
					output("NON-PRIME: 102389");
				}
				System.exit(0);
			}
			(new Thread(new FileSizeFinder())).start();
			while (passes != limit && primefile.length() <= bytelimit) {
				if (isPrime(current)) {
					primes++;
					primewr.append(current + "|"); primewr.flush();
				}
				current++;
				passes++;
			}
			output("LIMIT(S) REACHED! Passed " + passes + " numbers, used " + primefile.length() + "B storage.");
			benchwr.close();
			primewr.close();
		} catch (IOException e) {
			output("Error!");
			e.printStackTrace();
		}
		
	}
	
	public static void crash(String s) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("[" + dateFormat.format(date) + "] ERROR!");
		System.out.println("[" + dateFormat.format(date) + "] " + s);
		Exception e = new Exception(s);
		e.printStackTrace();
		System.exit(1);
	}
	
	public static boolean isPrime(long n) {
	    if (n%2==0) return false;
	    if (n%3==0) return false;
	    if (n%5==0) return false;
	    if (n%7==0) return false;
	    return true;
	}
	
	public static void output(String s) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("[" + dateFormat.format(date) + "] " + s);
	}
}
