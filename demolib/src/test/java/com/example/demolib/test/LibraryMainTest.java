package com.example.demolib.test;

import com.example.demolib.LibraryMain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LibraryMainTest {
    @Test
    public void testAdd1(){
        assertEquals(4, LibraryMain.add(2, 2));
    }
}
