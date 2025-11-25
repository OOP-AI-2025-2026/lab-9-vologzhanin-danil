package ua.opnu;

import java.util.*;

public class Task {

    // -------------------------------------------------------------------------
    // Task 1-5: List operations
    // -------------------------------------------------------------------------

    /**
     * Task 1: Removes the shorter string from each pair.
     */
    public void removeShorterStrings(List<String> list) {
        if (list == null || list.size() < 2) return;

        for (int i = 0; i < list.size() - 1; i += 1) {
            String s1 = list.get(i);
            String s2 = list.get(i + 1);

            int indexToRemove;
            if (s1.length() <= s2.length()) {
                indexToRemove = i;
            } else {
                indexToRemove = i + 1;
            }

            list.remove(indexToRemove);
        }
    }

    /**
     * Task 2: Duplicates every string in the list.
     */
    public void stutter(List<String> list) {
        if (list == null) return;

        // Iterate backwards for safe insertion
        for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            list.add(i, s);
        }
    }

    /**
     * Task 3: Swaps elements in pairs.
     */
    public void switchPairs(List<String> list) {
        if (list == null || list.size() < 2) return;

        for (int i = 0; i < list.size() - 1; i += 2) {
            String temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
    }

    /**
     * Task 4: Removes duplicates from a sorted list.
     */
    public void removeDuplicates(List<String> list) {
        if (list == null || list.isEmpty()) return;

        // Iterate backwards for safe removal
        for (int i = list.size() - 1; i > 0; i--) {
            String current = list.get(i);
            String previous = list.get(i - 1);

            if (current.equals(previous)) {
                list.remove(i);
            }
        }
    }

    /**
     * Task 5: Inserts "****" before every 4-character string.
     */
    public void markLength4(List<String> list) {
        if (list == null) return;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++; // Skip the newly added element
            }
        }
    }

    // -------------------------------------------------------------------------
    // Task 6-8: Queue operations
    // -------------------------------------------------------------------------

    /**
     * Task 6: Checks if the queue is a palindrome, restoring the queue state.
     */
    public boolean isPalindrome(Queue<Integer> q) {
        if (q == null || q.isEmpty()) {
            return true;
        }

        Stack<Integer> s = new Stack<>();
        int size = q.size();
        boolean isPalindrome = true;

        // 1. Copy elements to Stack and return them to the Queue
        for (int i = 0; i < size; i++) {
            Integer val = q.remove();
            q.add(val);
            s.push(val);
        }

        // 2. Compare elements from front (Queue) and back (Stack)
        for (int i = 0; i < size; i++) {
            Integer qValue = q.remove();
            Integer sValue = s.pop();

            if (!qValue.equals(sValue)) {
                isPalindrome = false;
            }

            // Restore the element to the Queue
            q.add(qValue);
        }
        return isPalindrome;
    }

    /**
     * Task 7: Reorders queue: Non-negative (>= 0) -> Negative (< 0), preserving relative order.
     */
    public void reorder(Queue<Integer> q) {
        if (q == null || q.isEmpty()) {
            return;
        }

        Stack<Integer> s = new Stack<>();
        int size = q.size();
        int nonNegatives = 0;

        // 1. Separate: Negatives -> Stack, Non-Negatives -> Queue
        for (int i = 0; i < size; i++) {
            Integer val = q.remove();
            if (val >= 0) {
                q.add(val);
                nonNegatives++;
            } else {
                s.push(val);
            }
        }

        // 2. Add Negatives from Stack to the end of Q
        while (!s.isEmpty()) {
            q.add(s.pop());
        }

        // 3. Move Non-Negatives to the front
        int negatives = size - nonNegatives;
        for (int i = 0; i < nonNegatives; i++) {
            q.add(q.remove());
        }

        // 4. Invert Negatives using Stack (to restore relative order)
        for (int i = 0; i < negatives; i++) {
            s.push(q.remove());
        }

        // 5. Move Non-Negatives back to the front (where they belong)
        for (int i = 0; i < nonNegatives; i++) {
            q.add(q.remove());
        }

        // 6. Restore Negatives to the end
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }


    /**
     * Task 8: Reorders queue: Even -> Odd, preserving relative order.
     */
    public void rearrange(Queue<Integer> q) {
        if (q == null || q.isEmpty()) {
            return;
        }

        Stack<Integer> s = new Stack<>();
        int initialSize = q.size();
        int evens = 0;

        // 1. Separate: Odds -> Stack, Evens -> Queue
        for (int i = 0; i < initialSize; i++) {
            Integer val = q.remove();
            if (val % 2 == 0) { // Парні
                q.add(val);
                evens++;
            } else { // Непарні
                s.push(val);
            }
        }

        // 2. Add Odds from Stack to the end of Q
        while (!s.isEmpty()) {
            q.add(s.pop());
        }

        // 3. Move Evens to the front
        int odds = initialSize - evens;
        for (int i = 0; i < evens; i++) {
            q.add(q.remove());
        }

        // 4. Invert Odds using Stack (to restore relative order)
        for (int i = 0; i < odds; i++) {
            s.push(q.remove());
        }

        // 5. Move Evens back to the front
        for (int i = 0; i < evens; i++) {
            q.add(q.remove());
        }

        // 6. Restore Odds to the end
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }

    // -------------------------------------------------------------------------
    // Task 9-11: Set operations
    // -------------------------------------------------------------------------

    /**
     * Task 9: Returns the length of the longest string in the set.
     */
    public int maxLength(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return 0;
        }

        int maxLen = 0;
        for (String s : set) {
            if (s != null && s.length() > maxLen) {
                maxLen = s.length();
            }
        }
        return maxLen;
    }

    /**
     * Task 10: Removes all even-length strings from the set.
     */
    public Set<String> removeEvenLength(Set<String> set) {
        if (set == null) {
            return null;
        }

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s != null && s.length() % 2 == 0) {
                iterator.remove();
            }
        }
        return set;
    }

    /**
     * Task 11: Returns the count of unique integers common to both lists.
     */
    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        if (list1 == null || list2 == null) {
            return 0;
        }

        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> commonElements = new HashSet<>();

        for (Integer num : list2) {
            if (set1.contains(num)) {
                commonElements.add(num);
            }
        }

        return commonElements.size();
    }

    // -------------------------------------------------------------------------
    // Task 12-16: Map operations
    // -------------------------------------------------------------------------

    /**
     * Task 12: Checks if all values in the Map are unique.
     * FIX: Signature changed to Map<String, Integer> to match TaskTest.
     */
    public boolean isUnique(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }

        Set<Integer> values = new HashSet<>(map.values());

        return values.size() == map.size();
    }

    /**
     * Task 13: Returns the intersection of two maps (common Key-Value pairs).
     * FIX: Signature changed to Map<String, Integer> to match TaskTest.
     */
    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> intersection = new HashMap<>();

        if (map1 == null || map2 == null || map1.isEmpty() || map2.isEmpty()) {
            return intersection;
        }

        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value1 = entry.getValue();

            if (map2.containsKey(key)) {
                Integer value2 = map2.get(key);

                if (value1.equals(value2)) {
                    intersection.put(key, value1);
                }
            }
        }

        return intersection;
    }

    /**
     * Task 14: Reverses the Map (keys become values, values become keys).
     */
    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> reversedMap = new HashMap<>();

        if (map == null || map.isEmpty()) {
            return reversedMap;
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer originalKey = entry.getKey();
            String originalValue = entry.getValue();

            reversedMap.put(originalValue, originalKey);
        }

        return reversedMap;
    }

    /**
     * Task 15: Returns the value that appears most rarely.
     * If tied, returns the smallest value.
     */
    public Integer rarest(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        // 1. Calculate frequency (Value -> Frequency)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int value : map.values()) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        // 2. Find the minimum frequency
        int minFrequency = Integer.MAX_VALUE;
        for (int freq : frequencyMap.values()) {
            if (freq < minFrequency) {
                minFrequency = freq;
            }
        }

        // 3. Find the smallest value that has this minimum frequency
        int rarestValue = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int value = entry.getKey();
            int freq = entry.getValue();

            if (freq == minFrequency && value < rarestValue) {
                rarestValue = value;
            }
        }

        return rarestValue;
    }

    /**
     * Task 16: Returns the maximum number of occurrences for any single element.
     */
    public int maxOccurrences(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }

        // 1. Calculate frequency
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : list) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. Find maximum frequency
        int maxOccurrences = 0;
        for (int count : frequencyMap.values()) {
            if (count > maxOccurrences) {
                maxOccurrences = count;
            }
        }

        return maxOccurrences;
    }
}