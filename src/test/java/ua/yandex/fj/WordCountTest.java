/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.fj;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maksym Yatsura
 */
public class WordCountTest {

    @Test
    public void testGetResult1() {
        
        String[] words = {"b", "b", "b", "a", "a", "b", "a", "c"};
        WordCount wordCount = new WordCount(words);
        Map<String, Integer> actualRes = wordCount.getResult();
    
        Map<String, Integer> expRes = new HashMap<String, Integer>();
        expRes.put("a", 3);
        expRes.put("b", 4);
        expRes.put("c", 1);
        assertEquals(actualRes, expRes);
    }
    
    @Test
    public void testGetResult2() {
        
        String[] words = new String[400];
        for(int i = 0; i < 100; i++) {
            words [4*i] = "Java";
            words [4*i + 1] = "is";
            words [4*i + 2] = "the";
            words [4*i + 3] = "best";
        }
        WordCount wordCount = new WordCount(words);
        Map<String, Integer> actualRes = wordCount.getResult();
    
        Map<String, Integer> expRes = new HashMap<String, Integer>();
        expRes.put("Java", 100);
        expRes.put("is", 100);
        expRes.put("the", 100);
        expRes.put("best", 100);
        assertEquals(actualRes, expRes);
    }
    
    @Test
    public void testGetResultEmpty() {
        
        String[] words = new String[0];
        WordCount wordCount = new WordCount(words);
        Map<String, Integer> actualRes = wordCount.getResult();
    
        Map<String, Integer> expRes = new HashMap<String, Integer>();
        assertEquals(actualRes, expRes);
    }
    
}
