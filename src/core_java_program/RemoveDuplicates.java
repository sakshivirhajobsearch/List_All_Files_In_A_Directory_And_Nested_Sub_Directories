package core_java_program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {

	public static void main(String[] args) {

		// Read contents of file1
		String fileNameOne = "E:\\Project\\core_java_program\\src\\core_java_program\\01-yahoo-send-mail-id-read.txt";
		String stringLineOne = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReaderOne = new FileReader(fileNameOne);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReaderOne = new BufferedReader(fileReaderOne);
			while ((stringLineOne = bufferedReaderOne.readLine()) != null) {
				// System.out.println("stringLineOne: " + stringLineOne);
			}
			bufferedReaderOne.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Read contents of file2
		String fileNameTwo = "E:\\Project\\core_java_program\\src\\core_java_program\\02-yahoo-fail-mail-id-read.txt";
		String stringLineTwo = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReaderTwo = new FileReader(fileNameTwo);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReaderTwo = new BufferedReader(fileReaderTwo);
			while ((stringLineTwo = bufferedReaderTwo.readLine()) != null) {
				// System.out.println("stringLineTwo: " + stringLineTwo);
			}
			bufferedReaderTwo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 01-yahoo-send-mail-id-read.txt
		Set<String> set1 = readFile(fileNameOne);
		
		// 02-yahoo-fail-mail-id-read.txt
		Set<String> set2 = readFile(fileNameTwo);

		// Remove duplicates from each set
		// 01-yahoo-send-mail-id-read.txt
		Set<String> uniqueSet1 = new HashSet<>(set1);
		
		// 02-yahoo-fail-mail-id-read.txt
		Set<String> uniqueSet2 = new HashSet<>(set2);

		// Remove common elements from both sets
		// removes failed emails from sent list → gives successfully sent emails.
		uniqueSet1.removeAll(set2);
		
		// removes any strange/fake failures not in sent list → gives invalid/unknown failures (rare use-case).
		uniqueSet2.removeAll(set1);

		// Write the contents of uniqueSet1 to a new file
		System.out.println("uniqueSet1: " + uniqueSet1);
		writeToFile("E:\\Project\\core_java_program\\src\\core_java_program\\03-yahoo-success-mail-id-write.txt", uniqueSet1);

		// Write the contents of uniqueSet2 to a new file
		writeToFile("E:\\Project\\core_java_program\\src\\core_java_program\\04-yahoo-fail-mail-id-write.txt", uniqueSet2);
	}

	private static Set<String> readFile(String filename) {

		Set<String> set = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				set.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	private static void writeToFile(String filename, Set<String> set) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			for (String line : set) {
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
