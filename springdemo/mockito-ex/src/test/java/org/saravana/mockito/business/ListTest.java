package org.saravana.mockito.business;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

public class ListTest {

	@Test
	public void test_listsize() {
		ArrayList mockList=mock(ArrayList.class);
		when(mockList.size()).thenReturn(2);
		
		assertEquals(2, mockList.size());
	}
	@Test
	public void test_listsize_returnmultiplevalue() {
		ArrayList mockList=mock(ArrayList.class);
		when(mockList.size()).thenReturn(2).thenReturn(3);
		
		assertEquals(2, mockList.size());
		assertEquals(3, mockList.size());
	}
	@Test
	public void test_listget() {
		ArrayList mockList=mock(ArrayList.class);
		when(mockList.get(0)).thenReturn("firstvalue");
		
		assertEquals("firstvalue", mockList.get(0));
		assertEquals(null, mockList.get(1));//default behaviour for non stubbed methods
	}
	@Test
	public void test_listget_anyparametervalue() {
		ArrayList mockList=mock(ArrayList.class);
		//argument matcher
		when(mockList.get(anyInt())).thenReturn("firstvalue");
		
		assertEquals("firstvalue", mockList.get(0));
		assertEquals("firstvalue", mockList.get(1));
		assertEquals("firstvalue", mockList.get(2));
		assertEquals("firstvalue", mockList.get(3));
		assertEquals("firstvalue", mockList.get(4));
	}
	
	@Test(expected=RuntimeException.class)
	public void test_list_throwexception() {
		ArrayList mockList=mock(ArrayList.class);
		//argument matcher
		when(mockList.get(anyInt())).thenThrow(new RuntimeException("something went wrong"));
		
		mockList.get(0);
		
	}
}
