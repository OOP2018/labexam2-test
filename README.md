## Unit Tests for Lab Exam 2

The `test` source directory (and `test` package) contain JUnit 4 tests for Problems 1, 2, and 5.  The classes are named based on what they test.

You can run each test class using JUnit or run `TestRunner` as a "main" Java class.

**Requires** JUnit 4 library and adding `test` as a source directory.

## Test for Console Calculator

This problem was evaluated by manual testing.  First I did two things:

1. fix any errors in student's OperatorFactory (otherwise Calculator can't be tested).
2. add a new operator using a static block in Calculator:
```java
public class Calculator {
    static {
        OperatorFactory.getInstance().put("%", (a,b)->a%b);
    }
```

Run the calculator and try some expressions such as:

<table>
<tr markdown="span">
<th>Input</th>  <th>Expected Result</th>
</tr>
<tr markdown="span">
<td>200 * 2.5 </td> <td> 500 <td>
</tr>
<tr markdown="span">
<td>200 + 2.5 </td> <td> 202.5 <td>
</tr>
<tr markdown="span">
<td> 2 ^ 20  </td> <td> 1048576.0 <td>
</tr>
<tr markdown="span">
<td> 0.5 ^ 3  </td> <td> 0.125 <td>
</tr>
<tr markdown="span">
<td> 25 ^ -0.5  </td> <td> 0.2 <td>
</tr>
<tr>
<td> 25 % 7  </td> <td> 4.0 <td>
</tr>
<tr>
<td> 25 @ 7  </td> <td> "Unknown operator: @" <td>
</tr>
</table>

Three point bonus: if calculator prints known operators at startup,
including operator(s) I added to OperatorFactory:
```shell
cmd> java calculator.Calculator

Available operators : % * + ^ 
Input "value op value" with space in between each.
```

## Test for FoodSelector (Recursion)

For Problem 4 I ran the `FoodApp` class and input sample values.
The results must be completely correct to get credit.

Here's an example of running Wisa's code (with comments added):

```shell
Total calories? 280
Maximum Glycemic Load (GL)? 20
Description         Calories  GL
Mama Shrimp Tom Yam    280   17.3
Total                  280   17.3   // EXACTLY achieves calorie limit

Total calories? 280
Maximum Glycemic Load (GL)? 10
Description         Calories  GL
Roasted peanuts 42g    238    1.1
Carrots 1 cup           40    4.7
Total                  278    5.8  // Can't choose Mama this time due to GL limit

Total calories? 300
Maximum Glycemic Load (GL)? 20
Description         Calories  GL
Carrots 1 cup           40    4.7
Yogurt (low fat) 110   120    5.8
Vitasoy Soy Milk 250   140    4.8
Total                  300   15.2  // EXACTLY achieves calorie limit

Total calories? 300
Maximum Glycemic Load (GL)? 15     // Make GL smaller so solution is different
Description         Calories  GL
Banana 1 medium         89    0.0
Yogurt (low fat) 110   120    5.8
Fried Egg               90    0.0
Total                  299    5.8

Total calories? 300
Maximum Glycemic Load (GL)? 5.5    // Make GL smaller so solution is different
Description         Calories  GL
Apple 1 medium          63    5.4
Banana 1 medium         89    0.0
Fried Egg               90    0.0
Total                  242    5.4
```

Solutions found by Wisa's code are same as my reference code, and verified by checking the foods database.

## About Glycemic Index and Glycemic Load

Glycemic Index (ดัชนีน้ำตาล) measures how much a food causes your blood sugar level (ปริมาณน้ำตาลในเลือด) to rise, compared to a reference food.  The reference food is table sugar (sucrose) or white bread, with a GI of 100. So, a food with GI of 40 raises blood sugar only 40% as much as eating the same amount of bread. 

The total effect on blood sugar depends on how much you eat.
Glycemic Load (GL or ปริมาณน้ำตาลในเลือด) measures this.

> GL = GI x grams-of-carbohydrate / 100

Only the grams of carbohydrates are counted, since that is the primary source of glucose in the diet.

### Example of GI and GL

An Apple has glycemic index (GI) of 40 and 15 grams of carbohydrate.
So the glycemic load is:
```
 GL = 40 x 15 / 100 = 6
```
Coca-cola has a GI of 63. 35 grams of carbs in a 330ml can. 
So, one 330ml can (1/3 liter) has a GL of:
```
 GL = 63 x 35 / 100 = 22.05
```
