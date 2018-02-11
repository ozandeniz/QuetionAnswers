package questions;

import java.util.HashMap;
import java.util.Map;

public interface AnagramQuestion {
    static boolean isAnagram(String lhs, String rhs) {
        if (lhs.length() != rhs.length()) {
            return false;
        }
        Map<Character, Integer> lhsMap = new HashMap<>();
        Map<Character, Integer> rhsMap = new HashMap<>();

        for (int i = 0; i < lhs.length(); i++) {
            int count = lhsMap.getOrDefault(lhs.charAt(i), 0);
            count++;
            lhsMap.put(lhs.charAt(i), count);

            count = rhsMap.getOrDefault(rhs.charAt(i), 0);
            count++;
            rhsMap.put(rhs.charAt(i), count);
        }

        return lhsMap.equals(rhsMap);
    }
}
