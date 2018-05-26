package most_popular_words;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public static void main(String[] args) {
		String[] webList = { "http://www.onet.pl", "span.title", // list of portals and search criteria
				"https://www.wp.pl", "a[title]", "http://www.interia.pl", "a[title]" };
		ArrayList<String> firstStep = new ArrayList<>();
		for (int i = 0; i < webList.length; i = i + 2) { // scan each portal for titles
			Connection connect = Jsoup.connect(webList[i]);
			System.out.println("Connecting to: " + webList[i] + "...");
			try {
				Document document = connect.get();
				Elements links = document.select(webList[i + 1]);
				for (Element elem : links) {
					String rawText = elem.text(); // format text to leave only words
					String justWords = rawText.replaceAll("[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ\\s]", "");
					String toLowerCase = justWords.toLowerCase(); // to lower case
					firstStep.add(toLowerCase);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Saving all popular words to popular_words.txt...");
		Path firstFile = Paths.get("./popular_words.txt"); // save content to a file
		try {
			Files.write(firstFile, firstStep);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String secondStep = ""; // read first file to a string
		try {
			byte[] temp = Files.readAllBytes(firstFile);
			String removeEol = new String(temp);
			String eol = System.getProperty("line.separator"); // remove line separators and
			String eolRemoved = removeEol.replaceAll(eol, " "); // replace multiple white spaces with single spaces
			secondStep = eolRemoved.replaceAll("\\s+", " ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] words = secondStep.split(" "); // split string into separate words
		Map<String, Integer> frequency = new HashMap<String, Integer>(); // create HashMap of word frequency
		for (String wordIt : words) {
			if (wordIt.length() > 3) { // remove words shorter than 3 letters
				Integer count = frequency.get(wordIt);
				if (count == null)
					count = 0;
				frequency.put(wordIt, count + 1);
			}
		}
		String mostFrequentTen = ""; // extract 10 most frequent words from HashMap
		for (int i = 0; i < 10; i++) {
			Entry<String, Integer> maxEntry = null;
			for (Entry<String, Integer> entry : frequency.entrySet()) {
				if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
					maxEntry = entry;
				}
			}
			mostFrequentTen = mostFrequentTen + " " + maxEntry;
			frequency.remove(maxEntry.getKey());
		}
		System.out.println("Saving 10 most popular words to most_popular_words.txt...");
		Path secondFile = Paths.get("./most_popular_words.txt"); // save to the second file
		try {
			Files.write(secondFile, mostFrequentTen.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}