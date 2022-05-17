/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 55301
 */
public class ArrayGeneratorTest {
    
    public ArrayGeneratorTest() {
    }
    
    @Test
    public void generateAraayLength10() {
        ArrayGenerator arrGen = new ArrayGenerator(10);
        int[] result = arrGen.getArray();
        int[] expected = {15, 8, 17, 20, 19, 32, 21, 44, 23, 56};
        assertArrayEquals(expected, result);
    }
    
     @Test
    public void testGenerateArrayCount0() {
        ArrayGenerator ag = new ArrayGenerator(0);
        int expectedSize = 0;
        int result = ag.getArray().length;
        assertEquals(result, expectedSize);
    }
    
       @Test
    public void testGenerateArrayCount100() {
        ArrayGenerator ag = new ArrayGenerator(100);
        int expectedSize = 100;
        int result = ag.getArray().length;
        assertEquals(result, expectedSize);
    }

}