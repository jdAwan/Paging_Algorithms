### **Paging Algorithms**
Different eviction algorithms use different criteria to choose the page to be evicted. We consider the following eviction algorithms. To illustrate, we assume the cache contains 3 pages with initial ID content 20, 30, 10 and the sequence of requests is 20, 5, 30, 10, 5, 20.
(i) **No eviction.** This algorithm does not evict any pages, i.e., the cache stays as the initial content. Then the hit (h) and miss (m) sequence will be hmhhmh since 20 is a hit, 5 miss, 30 hit, 10 hit, 5 miss, 20 hit. There are 4 h and 2 m.

(ii) **Evict LRU** This algorithm evicts the pages in the LRU (least recently used) principle. This means that the algorithm evicts the page whose most recent request was earliest. We assume that the initial cache content is added one by one with 20 first, then 30, then 10. The hit and miss sequence will then be hmmmhm with 2h4m.

To execute paging algorithms run PagingApp.java file


### List Accessing Problems

We consider two accessing/reorganising algorithms. To illustrate, we assume the file cabinet initially contains 3 files with IDs 20 30 10 and the sequence of requests is 20 30 5 30 5 20.
**• Append if miss:** This algorithm does not reorganise the file cabinet and only appends a file at the end if it was not originally in the cabinet. Therefore, there will be 5 hits, the file cabinet will become 20 30 10 5 at the end, and the number of comparisons (cost) for the 6 requests is respectively 1 2 3 2 4 1. 

**• Frequency count:**  This algorithm rearranges the files in non-increasing order of frequency of access. This means that the algorithm keeps a count of how many times a file has been requested. When a file is requested, its counter gets increased by one and it needs to be moved to the correct position. If there are other files with the same frequency, the newly requested file should be put behind those with the same frequency. We assume that the files initially in the cabinet has frequency of 1 to start with. A newly inserted file also has a frequency of 1.
In this case, there will be 5 hits. The file cabinet will become 30 20 5 10 at the end. The number of comparisons (cost) for the 6 requests is respectively 1 2 3 2 4 2. 

To run these algorithms run cabApp.java file
