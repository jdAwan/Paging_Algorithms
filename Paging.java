//
// Coded by Prudence Wong 2021-12-29
// Updated 2023-02-25
//
// NOTE: You are allowed to add additional methods if you need.
//
// Name:
// Student ID:
//
// Time Complexity and explanation: You can use the following variables for easier reference.
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// noEvict():
//
// evictLRU():
//

import java.util.HashSet;

class Paging {


	// no eviction
	// Aim: 
	// do not evict any page
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108PagingOutput
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static PagingOutput noEvict(int[] cArray, int cSize, int[] rArray, int rSize) {
		PagingOutput output = new PagingOutput();

		output.cache = arrayToString(cArray, cSize);
		
        HashSet<Integer> cacheSet = new HashSet<>();
        for (int i = 0; i < cSize; i++) {
            cacheSet.add(cArray[i]);
        }

        for (int i = 0; i < rSize; i++) {
            if (cacheSet.contains(rArray[i])) {
                output.hitCount++;
                output.hitPattern += "h";
            } else {
                output.missCount++;
                output.hitPattern += "m";
            }
        }
		return output;
	}

	// evict LRU
	// Aim:
	// if a request is not in cache, evict the page that hasn't been used for the longest amount of time
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108PagingOutput
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
    static PagingOutput evictLRU(int[] cArray, int cSize, int[] rArray, int rSize){

        PagingOutput output = new PagingOutput();
        output.hitCount = 0;
        output.missCount = 0;
        output.hitPattern = "";

        // Create an array to keep track of the last time each page was requested
        int[] lastRequested = new int[cSize];
        for (int i = 0; i < cSize; i++) {
            lastRequested[i] = -1;
        }

        for (int i = 0; i < rSize; i++) {
            int requestedPage = rArray[i];

            // Check if the requested page is already in the cache
            boolean found = false;
            for (int j = 0; j < cSize; j++) {
                if (cArray[j] == requestedPage) {
                    found = true;
                    output.hitCount++;
                    output.hitPattern += "h";

                    // Update the lastRequested array for this page
                    lastRequested[j] = i;
                    break;
                }
            }

            // If the requested page is not in the cache, evict the LRU page and add the requested page
            if (!found) {
                output.missCount++;
                output.hitPattern += "m";

                // Find the page in the cache with the earliest last request time
                int lruPage = 0;
                int lruTime = lastRequested[0];
                for (int j = 1; j < cSize; j++) {
                    if (lastRequested[j] < lruTime) {
                        lruPage = j;
                        lruTime = lastRequested[j];
                    }
                }

                // Evict the LRU page and add the requested page
                cArray[lruPage] = requestedPage;
                lastRequested[lruPage] = i;
            }
        }

        output.cache = arrayToString(cArray, cSize);
        return output;
    }

	// DO NOT change this method
	// this will turn the cache into a String
	// Only to be used for output, do not use it to manipulate the cache
	static String arrayToString(int[] array, int size) {
		String outString="";
		
		for (int i=0; i<size; i++) {
			outString += array[i];
			outString += ",";
		}
		return outString;
	}

}

