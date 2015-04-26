package seaclouds.planner;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import seaclouds.utils.toscamodel.*;

import java.util.*;

/**
 * Created by pq on 26/04/2015.
 */
public class Optimizer {
    public final int wantedSolutions = 2;
    private double computeScore(IToscaEnvironment aam, Map<String, INodeType> solution){
        return 0.0;
    }

    class Solution implements Comparable<Solution> {
        public final Map<String,INodeType> value;
        public final double score;
        public Solution(IToscaEnvironment env, Map<String,INodeType> value) {
            this.value = value;
            this.score = computeScore(env, value);
        }

        @Override
        public int compareTo(Solution o) {
            if(this.score == o.score)
                return 0;
            return this.score>o.score?1:-1;
        }
    }

    private  IToscaEnvironment createADP(IToscaEnvironment aam, Map<String,INodeType> bindings){
        IToscaEnvironment adp = Tosca.newEnvironment();
        for (Map.Entry<String, INodeType> binding : bindings.entrySet()) {
            INodeTemplate node = (INodeTemplate) aam.getNamedEntity(binding.getKey());
            INamedEntity type = (INamedEntity) binding.getValue();
            type = adp.importWithSupertypes(type);
            INodeTemplate newNode = adp.newTemplate((INodeType) type);
            for (Map.Entry<String, IProperty> property : node.declaredProperties().entrySet()) {
                newNode = newNode.addProperty(property.getKey(),property.getValue().type(),property.getValue().defaultValue());
            }
            for (Map.Entry<String, IValue> attribute : node.declaredAttributes().entrySet()) {
                newNode.declaredAttributes().put(attribute.getKey(),attribute.getValue());
            }

        }
        for (INodeTemplate template : aam.getNodeTemplatesOfType((INodeType) aam.getNamedEntity("tosca.nodes.Logic"))) {
            adp.importWithSupertypes((INamedEntity)template.baseType());
        }
        for (INodeTemplate template : aam.getNodeTemplatesOfType((INodeType) aam.getNamedEntity("tosca.nodes.Deploy"))) {
            adp.importWithSupertypes((INamedEntity)template);
        }
        return adp;
    }

    public IToscaEnvironment optimizeLocal(IToscaEnvironment aam, Map<String,List<INodeType>> matches) {
        //start with random choices
        Map<String,INodeType> tentativeSolution = new HashMap<>();
        for(String k :matches.keySet()) {
            List<INodeType> m = matches.get(k);
            Random r = new Random();
            INodeType choice = m.get(r.nextInt(m.size()));
            tentativeSolution.put(k,choice);
        }
        //try local variations which may improve the score
        double score = computeScore(aam, tentativeSolution);
        boolean  solutionImproved;
        do {
            solutionImproved = false;
            for(String k : tentativeSolution.keySet()) {
                for(INodeType n: matches.get(k)) {
                    Map newTentative = new HashMap<>(tentativeSolution);
                    newTentative.put(k, n);
                    double newScore = computeScore(aam, newTentative);
                    if(newScore > score) {

                        tentativeSolution = newTentative;
                        score = newScore;
                        solutionImproved = true;
                    }

                }
            }
        } while (solutionImproved);

        return createADP(aam,tentativeSolution);
    }

    public List<IToscaEnvironment> optimizeFullSearch(IToscaEnvironment aam, Map<String,List<INodeType>> matches) {
        PriorityQueue<Solution> solutions = new PriorityQueue<>();
        Map<String,INodeType> tentativeSolution = new HashMap<>();
        Iterator<INodeType> cursor[] = new Iterator[matches.size()];
        String cursorName[] = new String[matches.size()];
        int level = 0;
        for(Map.Entry<String,List<INodeType>> entry :matches.entrySet()) {
            cursorName[level] = entry.getKey();
            cursor[level] = entry.getValue().iterator();
            tentativeSolution.put(cursorName[level],cursor[level].next());
        }
        while (level < cursor.length) {
            solutions.offer(new Solution(aam,tentativeSolution));
            while(solutions.size() > wantedSolutions)
                solutions.poll();
            level = 0;
            while(level < cursor.length && !cursor[level].hasNext()) {
                cursor[level] = matches.get(cursorName[level]).iterator();
                tentativeSolution.put(cursorName[level],cursor[level].next());
                level ++;
            }
            if(cursor[level].hasNext())
                tentativeSolution.put(cursorName[level],cursor[level].next());
        }
        List<IToscaEnvironment> ret = new ArrayList<>();
        for (Solution solution : solutions) {
            ret.add(createADP(aam,solution.value));
        }
        return ret;
    }

    public List<IToscaEnvironment> optimizeFullSearchB(IToscaEnvironment aam, Map<String,List<INodeType>> matches) {
        List<String> labels = new ArrayList<>();
        List<Set<INodeType>> values = new ArrayList<>();
        for (Map.Entry<String, List<INodeType>> matchable : matches.entrySet()) {
            labels.add(matchable.getKey());
            values.add(new HashSet<>(matchable.getValue()));
        }
        Set<List<INodeType>> allSolutions = Sets.cartesianProduct(values);

        PriorityQueue<Solution> solutions = new PriorityQueue<>();
        for (List<INodeType> solution : allSolutions) {
            Iterator<INodeType> it1 = solution.iterator();
            Iterator<String> it2 = labels.iterator();
            HashMap<String,INodeType> s = new HashMap<>();
            while(it1.hasNext()&& it2.hasNext())
                s.put(it2.next(), it1.next());
            solutions.offer(new Solution(aam,s));
            while(solutions.size() > wantedSolutions)
                solutions.poll();
        }

        List<IToscaEnvironment> ret = new ArrayList<>();

        for (Solution solution : solutions) {
            ret.add(createADP(aam,solution.value));
        }
        return ret;
    }

}
