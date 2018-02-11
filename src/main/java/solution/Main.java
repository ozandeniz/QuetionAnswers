package solution;

import questions.AnagramQuestion;
import questions.TopologicalSort;
import questions.script.VulnerabilityScript;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.ResourceUtil.loadResources;

public class Main {
    public static void main(String... args) throws IOException {
        List<VulnerabilityScript> vulnerabilityScripts = loadResources("vulnerabilityScripts.json", VulnerabilityScript.class);
        Map<Integer, VulnerabilityScript> vulnerabilityScriptMap = new HashMap<>();
        for (VulnerabilityScript vulnerabilityScript : vulnerabilityScripts) {
            vulnerabilityScriptMap.putIfAbsent(vulnerabilityScript.getScriptId(), vulnerabilityScript);
        }

        List<Integer> order = new TopologicalSort(vulnerabilityScriptMap).sort();
        System.out.println(order);

        boolean isAnagram = AnagramQuestion.isAnagram("arches", "chaser");
        System.out.println(isAnagram);
    }
}
