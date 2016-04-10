package ua.yandex.fj;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import ua.yandex.interfaces.Calculator;

/**
 *
 * @author Maksym Yatsura
 */

public class WordCount implements Calculator {
 
    private final CountTask countTask;
    
    public WordCount(String[] words) {
        countTask = new CountTask(words);
    }
    
    @Override
    public Map<String, Integer> getResult() {
        ForkJoinPool fjPool = new ForkJoinPool();
        Map<String, Integer> res = fjPool.invoke(countTask);
        return res;
    }
    
    private static class CountTask 
        extends RecursiveTask<Map<String, Integer> > { 
        private static int MIN_SIZE = 4;
        private final String[] words;   
        private final int begin;
        private final int end;

        private CountTask(String[] words, int begin, int end) {
            this.words = words;
            this.begin = begin;
            this.end = end;
        }

        public CountTask(String[] words) {
            this.words = words;
            this.begin = 0;
            this.end = words.length - 1;
        }

        @Override
        protected Map<String, Integer> compute() {
            if (end - begin + 1 < MIN_SIZE) {
                return count();
            }
            return parallelCount();
        }

        private Map<String, Integer> count() {
            Map<String, Integer> res = new HashMap<>();

            for(int i = begin; i <= end; i++) {
                if(res.containsKey(words[i])) {
                    res.put(words[i], res.get(words[i]) + 1);
                } else {
                    res.put(words[i], 1);
                }
            }

            return res;
        }

        private Map<String, Integer> parallelCount() {
            Map<String, Integer> res;

            int med = (begin + end) / 2;
            CountTask left = new CountTask(words, begin, med);
            left.fork();
            CountTask right = new CountTask(words, med+1, end);
            right.fork();

            res = mergeMaps(left.join(), right.join()); 
            return res;
        }

        private Map<String, Integer> mergeMaps(Map<String, Integer> left, 
                Map<String, Integer> right) {

            Map<String, Integer> res = new HashMap<>(left);
            for (Map.Entry<String, Integer> e : right.entrySet()) {
                if(res.containsKey(e.getKey())) {
                    res.put(e.getKey(), res.get(e.getKey()) + e.getValue());
                } else {
                    res.put(e.getKey(), e.getValue());
                }    
            }

            return res;
        }
    }
    
}
