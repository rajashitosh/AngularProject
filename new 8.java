1.What is Singleton in java and create your own singleton class countering all breakable conditions? 2. What is Auto Configuration? 3. @Primary vs @Qualifier 4. What is idempotent? 5. What is class loader? Types of class loader 6. Heap memory and stack memory 7. What is @Configuration and @bean? 8. Why is string immutable in java? 9. Write your own immutable class. 10. How Hashmap works internally? 11. What is Garbage Collector and how it works? 12. Checked vs unchecked exceptions 13. Throw vs Throws 14. What is Aggregation and Composition? 15. I want to add element inside a collection where duplicates won't be allowed and insertion order also be preserved. What should I use? 16. Limit vs Skip 17. Stream vs Parallel stream 18. Lock vs Synchronisation 20. Spring bean scopes 22. Monolithic vs Microservices 23. How Eureka works? 24. Why static and defaults methods are added inside interfaces?


write a code to filter out loans with incomplete status using java 8 features.
ListcompleteLoan= loanslist.stream().filter(e->e.loanstatus=="incomplete";).collect(Collectors.toList());

JVM Memory
├── Heap
│   ├── Young Generation
│   ├── Old Generation
├── Stack (Thread-specific)
├── PC Registers (Thread-specific)
├── Native Method Stack (Thread-specific)
└── Method Area (Metaspace)
    ├── Static variables
    ├── Class metadata
    ├── Constant pool
    └── Method bytecode
	
	
import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singleton implements Serializable, Cloneable {
    // 1. Private static instance, volatile to prevent instruction reordering.
    private static volatile Singleton instance;

    // 2. Private constructor to prevent instantiation.
    private Singleton() {
        // Prevent instantiation via reflection.
        if (instance != null) {
            throw new IllegalStateException("Instance already created!");
        }
    }

    // 3. Public static method to provide access to the instance.
    public static Singleton getInstance() {
        if (instance == null) { // First check (no locking).
            synchronized (Singleton.class) {
                if (instance == null) { // Second check (with locking).
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // 4. Prevent cloning of the instance.
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton cannot be cloned");
    }

    // 5. Prevent deserialization from creating a new instance.
    private Object readResolve() throws ObjectStreamException {
        return instance; // Return the existing instance.
    }
}

 @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)); // Cache expires after 10 minutes
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }

package com.myapp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.myapp.main", "com.myapp.services"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package com.myapp.main;

import com.myapp.services.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = MyService.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

1. Instantiation
   ↓
2. Dependency Injection
   ↓
3. Aware Interfaces (Optional)
   ↓
4. Initialization (Custom Logic)
   ↓
5. Ready-to-Use
   ↓
6. Post-Processing (Optional)
   ↓
7. Destruction (Custom Logic)



Array and String
Find the maximum subarray sum (Kadane’s Algorithm)

Problem: Given an array, find the contiguous subarray with the largest sum.
Example: Input: [-2,1,-3,4,-1,2,1,-5,4], Output: 6.

public int maxSubArray(int[] nums) {
    int maxSoFar = nums[0];
    int currentMax = nums[0];

    for (int i = 1; i < nums.length; i++) {
        currentMax = Math.max(nums[i], currentMax + nums[i]);
        maxSoFar = Math.max(maxSoFar, currentMax);
    }
    return maxSoFar;
}

=============================================================================

Two Sum Problem

Problem: Find two numbers in an array that sum up to a given target.
Example: Input: [2, 7, 11, 15], Target: 9, Output: [0, 1].
import java.util.HashMap;

public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    throw new IllegalArgumentException("No solution found");
}

=============================================================================


Reverse a String

Problem: Reverse the characters in a string.
Example: Input: "hello", Output: "olleh".
Find the Longest Palindromic Substring

Problem: Given a string, find the longest substring that is a palindrome.
Example: Input: "babad", Output: "bab" or "aba".

---------------------------------------------------------------------------------------------------------
Linked List
Detect a Cycle in a Linked List

Problem: Use Floyd’s Cycle Detection Algorithm to check if a cycle exists.
Example: Input: 1 -> 2 -> 3 -> 4 -> 2, Output: true.
Reverse a Linked List

Problem: Reverse the pointers in a singly linked list.
Example: Input: 1 -> 2 -> 3 -> null, Output: 3 -> 2 -> 1 -> null.
Merge Two Sorted Lists

Problem: Merge two sorted linked lists into one sorted linked list.
Example: Input: 1 -> 2 -> 4, 1 -> 3 -> 4, Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4.

----------------------------------------------------------------------------------------------------------
Binary Tree and Binary Search Tree
Inorder Traversal of a Binary Tree (Iterative and Recursive)

Problem: Return the inorder traversal of a binary tree.
Lowest Common Ancestor of a Binary Search Tree

Problem: Find the LCA of two nodes in a BST.
Level Order Traversal

Problem: Return the level-wise nodes of a binary tree as a list of lists.
Example: Input: [3,9,20,null,null,15,7], Output: [[3],[9,20],[15,7]].

------------------------------------------------------------------------------------------------------------
Dynamic Programming
Longest Common Subsequence

Problem: Find the length of the longest subsequence common to two strings.
Example: Input: text1 = "abcde", text2 = "ace", Output: 3.
Knapsack Problem

Problem: Maximize the value within a weight limit.
Example: Input: Weights = [1, 2, 3], Values = [6, 10, 12], Capacity: 5, Output: 22.
Climbing Stairs

Problem: Find the number of distinct ways to climb to the top of a staircase with n steps, where you can take 1 or 2 steps at a time.
Example: Input: 3, Output: 3.

--------------------------------------------------------------------------------------------------------------
Sorting and Searching
Binary Search

Problem: Search for a target value in a sorted array.
Example: Input: [1, 3, 5, 7, 9], Target: 5, Output: 2.
Merge Sort Algorithm

Problem: Implement merge sort to sort an array.
Find the Median of Two Sorted Arrays

Problem: Given two sorted arrays, find the median of the combined sorted array.
Example: Input: [1, 3], [2], Output: 2.0.

--------------------------------------------------------------------------------------------------------------
Hashing
Find the First Non-Repeating Character

Problem: Find the first character in a string that doesn't repeat.
Example: Input: "swiss", Output: "w".


		String str = "sswwiirrt";
		
		char[] strChar = str.toCharArray();
		
		Map<Character, Integer> countMap = new LinkedHashMap<>();
		
		int i = strChar.length - 1;
		while(i >= 0) {
			if(countMap.containsKey(strChar[i])) {
				countMap.put(strChar[i], countMap.get(strChar[i]) + 1);
			}
			else {
				countMap.put(strChar[i], 1);
			}
			--i;
		}
		
		String singleValue = "";
		for(Map.Entry<Character, Integer> entry : countMap.entrySet()) {
			if(entry.getValue() == 1)
				singleValue = entry.getKey().toString();
		}
		
		System.out.println(singleValue);

	

Group Anagrams

Problem: Group strings that are anagrams of each other.
Example: Input: ["eat", "tea", "tan", "ate", "nat", "bat"], Output: [["eat","tea","ate"],["tan","nat"],["bat"]].


just keep this do nothing
package com.tbh.nexarc.partner.product.util;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReadExcelUsingSaxParser {

	public List<Map<String, String>> readExcel(String path){

		try {
	      File file = new File(path); 
		   return new ExcelReaderHandler().readExcelFile(file);
		}
		catch(Exception e) {
			log.error("error",e);
		}
		return Collections.emptyList();
	}

}


package com.tbh.nexarc.partner.product.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SheetHandler extends DefaultHandler
{
	protected Map<String, String> header = new LinkedHashMap<>();
	protected Map<String, String> headerValidationMap = new LinkedHashMap<>();
	protected Map<String, String> headerStore = new LinkedHashMap<>();
	int count = 0;
	protected Map<String, String> rowValues = new LinkedHashMap<>();
	protected Map<String, String> rowMap = new LinkedHashMap<>();
	protected List<Map<String, String>> sheetList = new LinkedList<>();
	protected List<String> headerList=new ArrayList<>();
	private SharedStringsTable sharedStringsTable;

	protected long rowNumber = 0;
	protected String cellId;
	private String contents;
	private boolean isCellValue;
	private boolean fromSST;

	protected static String getColumnId(String attribute) throws SAXException {
		for (int i = 0; i < attribute.length(); i++) {
			if (!Character.isAlphabetic(attribute.charAt(i))) {
				return attribute.substring(0, i);
			}
		}
		throw new SAXException("Invalid format " + attribute);
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// Clear contents cache
		contents = "";
		// element row represents Row
		switch (name) {
		case "row" : {
			String rowNumStr = attributes.getValue("r");
			rowNumber = Long.parseLong(rowNumStr);
		}
		// element c represents Cell
		case "c" : {
			cellId = getColumnId(attributes.getValue("r"));
			// attribute t represents the cell type
			String cellType = attributes.getValue("t");
			if (cellType != null && cellType.equals("s")) {
				// cell type s means value will be extracted from SharedStringsTable
				fromSST = true;
			}
		}
		// element v represents value of Cell
		case "v" : isCellValue = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		if (isCellValue) {
			contents += new String(ch, start, length);
		}
	}

	@Override
	public void endElement(String uri, String localName, String name) {
		if (isCellValue && fromSST) {
			int index = Integer.parseInt(contents);
			contents = new XSSFRichTextString(sharedStringsTable.getItems().get(index).getT()).toString();
           
			if(rowNumber>1)
				rowValues.put(headerStore.get(cellId), contents);
			else {
				
				rowValues.put(cellId, contents);
				headerValidationMap.put(StringUtils.isEmpty(contents)?"":contents.trim(), String.valueOf(++count));
			}
			cellId = null;
			isCellValue = false;
			fromSST = false;
		} else if (isCellValue) {
			rowValues.put(headerStore.get(cellId), contents);
			isCellValue = false;
		} else if (name.equals("row")) {
			header.clear();
			headerList.clear();
			if (rowNumber == 1) {
				headerStore.putAll(rowValues);
				header.putAll(rowValues);
				headerList.addAll(rowValues.values());
			}
			try {
				processRow();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rowValues.clear();
		}
	}

	protected boolean processSheet(String sheetName) {
		return true;
	}

	protected void startSheet() {
	}

	protected void endSheet() {
	}

	protected void processRow() throws ExecutionException, InterruptedException {
	}

	public List<Map<String, String>> readExcelFile(File file) throws Exception {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		try (OPCPackage opcPackage = OPCPackage.open(file)) {
			XSSFReader xssfReader = new XSSFReader(opcPackage);
			sharedStringsTable = xssfReader.getSharedStringsTable();

//			System.out.println("shared String table count"+sharedStringsTable.getUniqueCount());
//			System.out.println("shared String table"+sharedStringsTable.getSharedStringItems());

			XSSFReader.SheetIterator  sheetIterator = (XSSFReader.SheetIterator ) xssfReader.getSheetsData();

			while (sheetIterator.hasNext()) {
				try (InputStream sheet = sheetIterator.next()) {
					String sheetName = sheetIterator.getSheetName();
					if(!processSheet(sheetName)) {
						continue;
					}
					startSheet();
					saxParser.parse(sheet, this);
					endSheet();
				}

			}
		}
		return sheetList;
	}
}

package com.tbh.nexarc.partner.product.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelReaderHandler extends SheetHandler {

	  @Override
	  protected boolean processSheet(String sheetName) {
	    //Decide which sheets to read; Return true for all sheets
	    //return "Sheet 1".equals(sheetName);
//	    System.out.println("Processing start for sheet : " + sheetName);
	    return true;
	  }

	  @Override
	  protected void startSheet() {
	    //Any custom logic when a new sheet starts
//	    System.out.println("Sheet starts");
	  }

	  @Override
	  protected void endSheet() {
	    //Any custom logic when sheet ends
//	    System.out.println("Sheet ends");
	  }

	  @Override
	  protected void processRow() {
	    if(rowNumber == 1 && !header.isEmpty()) {
//	      System.out.println("The header values are at line no. " + rowNumber + " " +
//	          "are :" + header);
//	      System.out.println(headerList);
	      sheetList.add(headerValidationMap);
	    }
	    else if (rowNumber > 1 && !rowValues.isEmpty()) {
	       //Get specific values here
	      /*String a = rowValues.get("A");
	      String b = rowValues.get("B");*/

	      //Print whole row
//	      System.out.println("The row values are at line no. " + rowNumber + " are :" + rowValues);
	      Map<String, String> rowValuesStore = new LinkedHashMap<>();
	      rowValuesStore.putAll(rowValues);
	      sheetList.add(rowValuesStore);
	    }
	   
	  }
	}

