package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import diet.ComboFood;
import diet.Food;

/**
 * JUnit tests for ComboFood class.
 */
public class ComboFoodTest2 {
	private static final double TOL = 0.01;
	private Food food1;
	private Food food2;
	private Food food3;
	private ComboFood combo;
	
	@Before
	public void setUp() throws Exception {
		food1 = new Food("Food 1", 200, 10, 50); // GL = gi*carbs/100 = 5
		food2 = new Food("Food 2", 300, 30, 10); // GL = gi*carbs/100 = 3
		food3 = new Food("Food 3", 100,  0,  0); // GL = 0
		combo = new ComboFood("TriFood", food1, food2, food3);
		// GL = 3 + 5 = 8 
		// carbs = 10 + 30 = 40
		// gi = 100 * GL / carbs = 20
	}

	@Test
	public void testInheritance() {
		assertTrue(combo instanceof Food);
	}
	
	@Test
	public void testGetName() {
		assertEquals("TriFood", combo.getName());
	}
	
	//@Test
	public void testUseSuperName() {
		assertEquals("TriFood", combo.toString());
	}
	
	@Test
	public void testCalories() {
		int calories = food1.getCalories() + food2.getCalories() + food3.getCalories();
		assertEquals( "Calories", calories, combo.getCalories() );
		// calories shouldn't change
		assertEquals( "Calories", calories, combo.getCalories() );
	}
	
	@Test
	public void testGL() {
		double gl = food1.getGL() + food2.getGL() + food3.getGL();
		assertEquals("GL", gl, combo.getGL(), TOL);
	}
	
	@Test
	public void testCarbs() {
		double carbs = food1.getCarbs() + food2.getCarbs() + food3.getCarbs();
		assertEquals("carbs", carbs, combo.getCarbs(), TOL);
	}
	
	@Test
	public void testGlycemicIndex() {
		double gl = food1.getGL() + food2.getGL() + food3.getGL();
		double carbs = food1.getCarbs() + food2.getCarbs() + food3.getCarbs();
		assertEquals("gi", 100.0*gl/carbs, combo.getGlycemicIndex(), TOL);
	}
	
	
	@Test
	public void testSingleFood() {
		ComboFood simple = new ComboFood("Simple", food2);
		assertEquals( food2.getCalories(), simple.getCalories());
		assertEquals( food2.getCarbs(), simple.getCarbs(), TOL);
		assertEquals( food2.getGL(), simple.getGL(), TOL);
		assertEquals( food2.getGlycemicIndex(), simple.getGlycemicIndex(), TOL);
		// shouldn't change
		assertEquals( food2.getGlycemicIndex(), simple.getGlycemicIndex(), TOL);
	}
	
	@Test
	public void TestAddFood() {
		ComboFood combo = new ComboFood("A La Carte", food1);
		combo.add(food2);
		int calories = food1.getCalories() + food2.getCalories();
		assertEquals(calories, combo.getCalories());
		combo.add(food3);
		calories += food3.getCalories();
		assertEquals(calories, combo.getCalories());
		Food food4 = new Food("Food4", 1000, 20.0, 12.5);
		combo.add(food4);
		calories += food4.getCalories();
		assertEquals(calories, combo.getCalories());
	}
	
	/** 
	 *  Test for static attributes. This shouldn't be needed. 
     *  No programmer would use static attributes in this context.
     */
	@Test
	public void testNotStatic() {
		ComboFood combo1 = new ComboFood("Combo1", food1, food2);
		ComboFood combo2 = new ComboFood("Combo2", food2, food3);
		ComboFood combo3 = new ComboFood("Combo3", food3);
		int calories1 = food1.getCalories() + food2.getCalories();
		assertEquals("calories for combo1", calories1, combo1.getCalories());
		int calories2 = food3.getCalories() + food2.getCalories();
		assertEquals("calories for combo2", calories2, combo2.getCalories());
		int calories3 = food3.getCalories();
		assertEquals("calories for combo3", calories3, combo3.getCalories());
	}

}
