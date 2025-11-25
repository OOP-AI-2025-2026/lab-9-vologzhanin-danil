package ua.opnu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

public class Task {

    // =================================================================
    // МЕТОДИ ДЛЯ LIST (Завдання 1 - 5) - ЗАЛИШЕНІ БЕЗ ЗМІН
    // =================================================================

    /**
     * Завдання 1. Видаляє рядок з меншою довжиною з кожної пари рядків.
     * Якщо довжини рівні, видаляє перший рядок з пари.
     */
    public static void removeShorterStrings(List<String> list) {
        for (int i = 0; i < list.size() - 1; i += 1) {
            String str1 = list.get(i);
            String str2 = list.get(i + 1);

            if (str1.length() <= str2.length()) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
        }
    }

    /**
     * Завдання 2. Дублює кожен рядок у списку.
     */
    public static void stutter(List<String> list) {
        int originalSize = list.size();
        for (int i = 0; i < originalSize; i++) {
            String element = list.get(i * 2);
            list.add(i * 2 + 1, element);
        }
    }

    /**
     * Завдання 3. Попарно змінює елементи місцями.
     */
    public static void switchPairs(List<String> list) {
        for (int i = 0; i < list.size() - 1; i += 2) {
            String temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
    }

    /**
     * Завдання 4. Видаляє дублікати з відсортованого списку.
     */
    public static void removeDuplicates(List<String> list) {
        for (int i = 1; i < list.size();) {
            String current = list.get(i);
            String previous = list.get(i - 1);

            if (current.equals(previous)) {
                list.remove(i);
            } else {
                i++;
            }
        }
    }

    /**
     * Завдання 5. Додає "****" перед кожним рядком із 4 символів.
     */
    public static void markLength4(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++;
            }
        }
    }

    // =================================================================
    // МЕТОДИ ДЛЯ QUEUE (Завдання 6 - 8) - ЗАЛИШЕНІ БЕЗ ЗМІН
    // =================================================================

    /**
     * Завдання 6. Перевіряє, чи є черга паліндромом.
     */
    public static boolean isPalindrome(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return true;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        List<Integer> tempQueueList = new ArrayList<>();

        while (!queue.isEmpty()) {
            int element = queue.poll();
            tempQueueList.add(element);
            stack.push(element);
        }

        boolean isPalindrome = true;

        for (int element : tempQueueList) {
            queue.offer(element);
            if (element != stack.pop()) {
                isPalindrome = false;
            }
        }

        return isPalindrome;
    }

    /**
     * Завдання 7. Пересортовує чергу за зростанням (з урахуванням знака).
     */
    public static void reorder(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }

        ArrayDeque<Integer> positives = new ArrayDeque<>();
        ArrayDeque<Integer> negatives = new ArrayDeque<>();

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int element = queue.poll();
            if (element < 0) {
                negatives.offer(element);
            } else {
                positives.offer(element);
            }
        }

        queue.addAll(negatives);
        queue.addAll(positives);
    }

    /**
     * Завдання 8. Перевпорядковує чергу: парні спереду, непарні ззаду.
     */
    public static void rearrange(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }

        ArrayDeque<Integer> odds = new ArrayDeque<>();
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            int element = queue.poll();
            if (element % 2 == 0) {
                queue.offer(element);
            } else {
                odds.offer(element);
            }
        }

        queue.addAll(odds);
    }

    // =================================================================
    // МЕТОДИ ДЛЯ SET (Завдання 9 - 11) - ЗАЛИШЕНІ БЕЗ ЗМІН
    // =================================================================

    /**
     * Завдання 9. Повертає довжину найдовшого рядка у множині.
     */
    public static int maxLength(Set<String> set) {
        if (set.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        for (String s : set) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }
        return maxLength;
    }

    /**
     * Завдання 10. Видаляє із множини усі рядки парної довжини.
     */
    public static void removeEvenLength(Set<String> set) {
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.length() % 2 == 0) {
                iterator.remove();
            }
        }
    }

    /**
     * Завдання 11. Повертає кількість унікальних цілих чисел, які зустрічаються в обох списках.
     */
    public static int numInCommon(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);

        set1.retainAll(set2);

        return set1.size();
    }

    // =================================================================
    // МЕТОДИ ДЛЯ MAP (Завдання 12 - 16) - ВИПРАВЛЕННЯ СИГНАТУР
    // =================================================================

    /**
     * Завдання 12. Перевіряє, чи є всі значення (values) унікальними.
     * СИГНАТУРА: Map<String, String> (для компіляції)
     */
    public static boolean isUnique(Map<String, String> map) {
        if (map.isEmpty()) {
            return true;
        }

        Set<String> uniqueValues = new HashSet<>(map.values());
        return map.size() == uniqueValues.size();
    }

    /**
     * Завдання 13. Повертає Map, що є перетином двох вхідних Map<String, String>.
     * СИГНАТУРА: Map<String, String> (для компіляції)
     */
    public static Map<String, String> intersect(Map<String, String> map1, Map<String, String> map2) {
        Map<String, String> intersectionMap = new HashMap<>();

        for (Entry<String, String> entry : map1.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (map2.containsKey(key) && map2.get(key).equals(value)) {
                intersectionMap.put(key, value);
            }
        }

        return intersectionMap;
    }

    /**
     * Завдання 14. Змінює ключі та значення місцями.
     * СИГНАТУРА ВИПРАВЛЕНА НА: Map<String, Integer>
     */
    public static Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> reversedMap = new HashMap<>();

        for (Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            reversedMap.put(value, key);
        }

        return reversedMap;
    }

    /**
     * Завдання 15. Повертає значення, яке зустрічається в Map найбільш рідко.
     */
    public static int rarest(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int value : map.values()) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        int minFrequency = Integer.MAX_VALUE;

        for (int count : frequencyMap.values()) {
            if (count < minFrequency) {
                minFrequency = count;
            }
        }

        int rarestValue = Integer.MAX_VALUE;

        for (int value : frequencyMap.keySet()) {
            if (frequencyMap.get(value) == minFrequency) {
                if (value < rarestValue) {
                    rarestValue = value;
                }
            }
        }

        return rarestValue;
    }

    /**
     * Завдання 16. Повертає, скільки разів зустрічається найпоширеніше число у списку.
     */
    public static int maxOccurrences(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : list) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        return Collections.max(frequencyMap.values());
    }

    // --- Метод main для локального тестування (за бажанням) ---
    public static void main(String[] args) {
        System.out.println("--- Лабораторна робота 9: Тестування ---");

        // 1. removeShorterStrings
        List<String> list1 = new ArrayList<>(Arrays.asList("four", "score", "and", "seven", "years", "ago"));
        removeShorterStrings(list1);
        System.out.println("1. removeShorterStrings: " + list1); // [score, seven, years]

        // 6. isPalindrome
        Queue<Integer> q6 = new ArrayDeque<>(Arrays.asList(3, 8, 17, 9, 17, 8, 3));
        System.out.println("6. isPalindrome (True): " + isPalindrome(q6));
        System.out.println("   Queue after: " + q6);

        // 12. isUnique
        Map<String, String> map12 = new HashMap<>();
        map12.put("Kendrick", "Perkins");
        map12.put("Hal", "Perkins");
        System.out.println("12. isUnique (False): " + isUnique(map12));

        // 16. maxOccurrences
        List<Integer> list16 = Arrays.asList(9, 7, 9, -1, 2, 9, 7, 2, 15, 15);
        System.out.println("16. maxOccurrences: " + maxOccurrences(list16)); // 3

        System.out.println("----------------------------------------");
    }
}