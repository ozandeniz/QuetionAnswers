package questions;

import questions.script.VulnerabilityScript;

import java.util.*;


public class TopologicalSort {
    /*
        We assume that the graph is directed acyclic graph.
        If the graph is not directed acyclic graph, there will be infinite loops in the algorithm.

        The algorithm is adapted from the wikipedia;
        https://en.wikipedia.org/wiki/Topological_sorting

        The complexity is O(V+E) V:Vertices E:Edges
    */

    private Map<Integer, VulnerabilityScript> vulnerabilityScriptMap;
    private Set<Integer> permanentMarkedScripts;
    private Set<Integer> temporaryMarkedScripts;

    public TopologicalSort(Map<Integer, VulnerabilityScript> vulnerabilityScriptMap) {
        this.vulnerabilityScriptMap = vulnerabilityScriptMap;
        this.permanentMarkedScripts = new HashSet<>();
        this.temporaryMarkedScripts = new HashSet<>();
    }

    private boolean isMarked(Integer scriptId) {
        return permanentMarkedScripts.contains(scriptId) || temporaryMarkedScripts.contains(scriptId);
    }

    private void dfs(Integer scriptId, List<Integer> sortedElements) {
        temporaryMarkedScripts.add(scriptId);
        VulnerabilityScript vulnerabilityScript = vulnerabilityScriptMap.get(scriptId);
        for (Integer dependency : vulnerabilityScript.getDependencies()) {
            if (isMarked(dependency)) {
                dfs(dependency, sortedElements);
            }
        }
        permanentMarkedScripts.add(scriptId);
        sortedElements.add(scriptId);
    }

    public List<Integer> sort() {
        List<Integer> sortedElements = new ArrayList<>();
        for (Integer scriptId : vulnerabilityScriptMap.keySet()) {
            if (!isMarked(scriptId)) {
                dfs(scriptId, sortedElements);
            }
        }
        return sortedElements;
    }
}