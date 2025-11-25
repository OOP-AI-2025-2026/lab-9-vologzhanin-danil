package ua.opnu;

import java.util.*;
import java.util.stream.Collectors;

public class Task {

    // --- Task 1: removeShorterStrings ---
    public void removeShorterStrings(List<String> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        for (int i = 0; i < list.size() - 1; ) {
            String s1 = list.get(i);
            String s2 = list.get(i + 1);

            if (s1.length() <= s2.length()) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
        }
    }

    // --- Task 2: stutter ---
    public void stutter(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i += 2) {
            list.add(i + 1, list.get(i));
        }
    }

    // --- Task 3: switchPairs ---
    public void switchPairs(List<String> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        for (int i = 0; i < list.size() - 1; i += 2) {
            String temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
    }

    // --- Task 4: removeDuplicates ---
    public void removeDuplicates(List<String> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        for (int i = 0; i < list.size() - 1; ) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i + 1);
            } else {
                i++;
            }
        }
    }

    // --- Task 5: markLength4 ---
    public void markLength4(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++;
            }
        }
    }

    // --- Task 6: isPalindrome ---
    public boolean isPalindrome(Queue<Integer> q) {
        if (q == null || q.size() < 2) {
            return true;
        }
        Stack<Integer> s = new Stack<>();
        int size = q.size();
        boolean isPal = true;

        for (int i = 0; i < size; i++) {
            int element = q.remove();
            s.push(element);
            q.add(element);
        }

        for (int i = 0; i < size; i++) {
            int qVal = q.remove();
            int sVal = s.pop();

            if (qVal != sVal) {
                isPal = false;
            }
            q.add(qVal);
        }
        return isPal;
    }

    // --- Task 7: reorder ---
    public void reorder(Queue<Integer> q) {
        if (q == null || q.isEmpty()) {
            return;
        }

        Stack<Integer> s = new Stack<>();
        int size = q.size();
        int nonNegatives = 0;

        // 1. Від'ємні -> Стек, Невід'ємні -> Черга
        for (int i = 0; i < size; i++) {
            int val = q.remove();
            if (val >= 0) { // Невід'ємні
                q.add(val);
                nonNegatives++;
            } else { // Від'ємні
                s.push(val);
            }
        }
        // Q: [Невід'ємні], S: [Від'ємні в оберненому порядку]

        // 2. Зсуваємо невід'ємні числа в кінець, залишаючи порожнє місце для від'ємних
        for (int i = 0; i < nonNegatives; i++) {
            q.add(q.remove());
        }
        // Q: [Невід'ємні] в кінці

        // 3. Додаємо від'ємні зі стеку.
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
        // Q: [Невід'ємні] [Від'ємні в оберненому порядку]

        // 4. Обертаємо від'ємні, щоб відновити їх відносний порядок.
        int negatives = size - nonNegatives;
        for (int i = 0; i < negatives; i++) {
            s.push(q.remove());
        }
        // Q: [Невід'ємні], S: [Від'ємні]

        // 5. Повертаємо від'ємні на кінець.
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
        // Q: [Невід'ємні] [Від'ємні]
    }

    // --- Task 8: rearrange ---
    public void rearrange(Queue<Integer> q) {
        if (q == null || q.isEmpty()) {
            return;
        }

        Stack<Integer> s = new Stack<>();
        int initialSize = q.size();
        int evens = 0;

        // 1. Непарні -> Стек, Парні -> Черга
        for (int i = 0; i < initialSize; i++) {
            int val = q.remove();
            if (val % 2 == 0) { // Парні
                q.add(val);
                evens++;
            } else { // Непарні
                s.push(val);
            }
        }
        // Q: [Парні], S: [Непарні в оберненому порядку]

        // 2. Зсуваємо парні числа в кінець
        for (int i = 0; i < evens; i++) {
            q.add(q.remove());
        }
        // Q: [Парні] в кінці

        // 3. Додаємо непарні зі стеку.
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
        // Q: [Парні] [Непарні в оберненому порядку]

        // 4. Обертаємо непарні, щоб відновити їх відносний порядок.
        int odds = initialSize - evens;
        for (int i = 0; i < odds; i++) {
            s.push(q.remove());
        }
        // Q: [Парні], S: [Непарні]

        // 5. Повертаємо непарні на кінець.
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
        // Q: [Парні] [Непарні]
    }

    // --- Task 9: maxLength ---
    public int maxLength(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return 0;
        }
        return set.stream().mapToInt(String::length).max().orElse(0);
    }

    // --- Task 10: removeEvenLength ---
    public void removeEvenLength(Set<String> set) {
        if (set == null) {
            return;
        }
        set.removeIf(s -> s.length() % 2 == 0);
    }

    // --- Task 11: numInCommon ---
    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        if (list1 == null || list2 == null || list1.isEmpty() || list2.isEmpty()) {
            return 0;
        }
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        set1.retainAll(set2);
        return set1.size();
    }

    // --- Task 12: isUnique ---
    // Сигнатура, що відповідає TaskTest
    public boolean isUnique(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        Set<String> uniqueValues = new HashSet<>(map.values());
        return map.size() == uniqueValues.size();
    }

    // --- Task 13: intersect ---
    // Сигнатура, що відповідає TaskTest
    public Map<String, String> intersect(Map<String, String> map1, Map<String, String> map2) {
        Map<String, String> intersectionMap = new HashMap<>();

        for (Map.Entry<String, String> entry : map1.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (map2.containsKey(key) && map2.get(key).equals(value)) {
                intersectionMap.put(key, value);
            }
        }
        return intersectionMap;
    }

    // --- Task 14: reverse ---
    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> reversedMap = new HashMap<>();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String value = entry.getValue();
            Integer key = entry.getKey();

            // Якщо дублікат значення, зберігаємо більше числове значення ключа
            if (!reversedMap.containsKey(value) || key > reversedMap.get(value)) {
                reversedMap.put(value, key);
            }
        }
        return reversedMap;
    }

    // --- Task 15: maxOccurrences ---
    public static int maxOccurrences(List<String> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        Map<String, Integer> counts = new HashMap<>();
        for (String s : list) {
            counts.put(s, counts.getOrDefault(s, 0) + 1);
        }
        return counts.values().stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    // --- Task 16: hasDuplicates ---
    public static boolean hasDuplicates(Map<String, String> map) {
        if (map == null || map.isEmpty() || map.size() == 1) {
            return false;
        }

        // Використовуємо Set для перевірки унікальності значень
        Set<String> values = new HashSet<>();
        for (String value : map.values()) {
            if (values.contains(value)) {
                return true; // Знайдено дублікат
            }
            values.add(value);
        }
        return false;
    }
}