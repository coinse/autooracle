/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 29 11:48:03 GMT 2023
 */

package org.apache.commons.lang3.text.translate;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class EntityArrays_ESTest extends EntityArrays_ESTest_scaffolding {

  //Test case number: 0
  /*
   * 26 covered goals:
   * Goal 1. Branch org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: I15 Branch 1 IF_ICMPGE L373 - false in context: org.apache.commons.lang3.text.translate.EntityArrays:invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 2. invert([[Ljava/lang/String;)[[Ljava/lang/String;_java.lang.ArrayIndexOutOfBoundsException_IMPLICIT
   * Goal 3. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 372
   * Goal 4. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 373
   * Goal 5. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 374
   * Goal 6. [METHOD] org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 7. Weak Mutation 0: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 0
   * Goal 8. Weak Mutation 1: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 1
   * Goal 9. Weak Mutation 2: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> -1
   * Goal 10. Weak Mutation 3: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 3
   * Goal 11. Weak Mutation 4: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceConstant - 0 -> 1
   * Goal 12. Weak Mutation 5: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp Negation of i
   * Goal 13. Weak Mutation 6: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp IINC 1 i
   * Goal 14. Weak Mutation 7: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp IINC -1 i
   * Goal 15. Weak Mutation 8: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceVariable array -> newarray
   * Goal 16. Weak Mutation 9: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceComparisonOperator >= -> -1
   * Goal 17. Weak Mutation 12: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - ReplaceVariable newarray -> array
   * Goal 18. Weak Mutation 13: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp Negation of i
   * Goal 19. Weak Mutation 14: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp IINC 1 i
   * Goal 20. Weak Mutation 15: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp IINC -1 i
   * Goal 21. Weak Mutation 16: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - ReplaceConstant - 0 -> 1
   * Goal 22. Weak Mutation 17: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - ReplaceVariable array -> newarray
   * Goal 23. Weak Mutation 18: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp Negation of i
   * Goal 24. Weak Mutation 19: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp IINC 1 i
   * Goal 25. Weak Mutation 20: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp IINC -1 i
   * Goal 26. Weak Mutation 21: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - ReplaceConstant - 1 -> 0
   */

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      String[][] stringArray0 = new String[4][9];
      String[] stringArray1 = new String[1];
      stringArray0[0] = stringArray1;
      // Undeclared exception!
      try { 
        EntityArrays.invert(stringArray0);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 1
         //
         verifyException("org.apache.commons.lang3.text.translate.EntityArrays", e);
      }
  }

  //Test case number: 1
  /*
   * 2 covered goals:
   * Goal 1. [METHOD] org.apache.commons.lang3.text.translate.EntityArrays.<init>()V
   * Goal 2. [METHODNOEX] org.apache.commons.lang3.text.translate.EntityArrays.<init>()V
   */

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      EntityArrays entityArrays0 = new EntityArrays();
  }

  //Test case number: 2
  /*
   * 19 covered goals:
   * Goal 1. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: I15 Branch 1 IF_ICMPGE L373 - true
   * Goal 2. Branch org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: I15 Branch 1 IF_ICMPGE L373 - true in context: org.apache.commons.lang3.text.translate.EntityArrays:invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 3. [Output]: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:EmptyArray
   * Goal 4. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 372
   * Goal 5. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 373
   * Goal 6. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 377
   * Goal 7. [METHOD] org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 8. [METHODNOEX] org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 9. Weak Mutation 0: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 0
   * Goal 10. Weak Mutation 1: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 1
   * Goal 11. Weak Mutation 2: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> -1
   * Goal 12. Weak Mutation 3: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 3
   * Goal 13. Weak Mutation 4: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceConstant - 0 -> 1
   * Goal 14. Weak Mutation 5: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp Negation of i
   * Goal 15. Weak Mutation 6: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp IINC 1 i
   * Goal 16. Weak Mutation 7: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp IINC -1 i
   * Goal 17. Weak Mutation 8: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceVariable array -> newarray
   * Goal 18. Weak Mutation 10: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceComparisonOperator >= -> >
   * Goal 19. Weak Mutation 32: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:377 - ReplaceVariable newarray -> array
   */

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      String[][] stringArray0 = new String[0][8];
      String[][] stringArray1 = EntityArrays.invert(stringArray0);
      assertNotSame(stringArray1, stringArray0);
  }

  //Test case number: 3
  /*
   * 3 covered goals:
   * Goal 1. invert([[Ljava/lang/String;)[[Ljava/lang/String;_java.lang.NullPointerException_IMPLICIT
   * Goal 2. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 372
   * Goal 3. [METHOD] org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;
   */

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      // Undeclared exception!
      try { 
        EntityArrays.invert((String[][]) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.lang3.text.translate.EntityArrays", e);
      }
  }

  //Test case number: 4
  /*
   * 50 covered goals:
   * Goal 1. org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_ESCAPE()[[Ljava/lang/String;: root-Branch
   * Goal 2. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: I15 Branch 1 IF_ICMPGE L373 - true
   * Goal 3. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: I15 Branch 1 IF_ICMPGE L373 - false
   * Goal 4. Branch org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_ESCAPE()[[Ljava/lang/String;: root-Branch in context: org.apache.commons.lang3.text.translate.EntityArrays:ISO8859_1_ESCAPE()[[Ljava/lang/String;
   * Goal 5. Branch org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: I15 Branch 1 IF_ICMPGE L373 - true in context: org.apache.commons.lang3.text.translate.EntityArrays:invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 6. Branch org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: I15 Branch 1 IF_ICMPGE L373 - false in context: org.apache.commons.lang3.text.translate.EntityArrays:invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 7. [Output]: org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_ESCAPE()[[Ljava/lang/String;:NonEmptyArray
   * Goal 8. [Output]: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:NonEmptyArray
   * Goal 9. org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_ESCAPE()[[Ljava/lang/String;: Line 30
   * Goal 10. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 372
   * Goal 11. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 373
   * Goal 12. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 374
   * Goal 13. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 375
   * Goal 14. org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;: Line 377
   * Goal 15. [METHOD] org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_ESCAPE()[[Ljava/lang/String;
   * Goal 16. [METHOD] org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 17. [METHODNOEX] org.apache.commons.lang3.text.translate.EntityArrays.ISO8859_1_ESCAPE()[[Ljava/lang/String;
   * Goal 18. [METHODNOEX] org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;
   * Goal 19. Weak Mutation 0: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 0
   * Goal 20. Weak Mutation 1: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 1
   * Goal 21. Weak Mutation 2: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> -1
   * Goal 22. Weak Mutation 3: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:372 - ReplaceConstant - 2 -> 3
   * Goal 23. Weak Mutation 4: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceConstant - 0 -> 1
   * Goal 24. Weak Mutation 5: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp Negation of i
   * Goal 25. Weak Mutation 6: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp IINC 1 i
   * Goal 26. Weak Mutation 7: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - InsertUnaryOp IINC -1 i
   * Goal 27. Weak Mutation 8: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceVariable array -> newarray
   * Goal 28. Weak Mutation 9: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceComparisonOperator >= -> -1
   * Goal 29. Weak Mutation 10: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:373 - ReplaceComparisonOperator >= -> >
   * Goal 30. Weak Mutation 12: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - ReplaceVariable newarray -> array
   * Goal 31. Weak Mutation 13: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp Negation of i
   * Goal 32. Weak Mutation 14: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp IINC 1 i
   * Goal 33. Weak Mutation 15: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp IINC -1 i
   * Goal 34. Weak Mutation 16: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - ReplaceConstant - 0 -> 1
   * Goal 35. Weak Mutation 17: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - ReplaceVariable array -> newarray
   * Goal 36. Weak Mutation 18: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp Negation of i
   * Goal 37. Weak Mutation 19: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp IINC 1 i
   * Goal 38. Weak Mutation 20: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - InsertUnaryOp IINC -1 i
   * Goal 39. Weak Mutation 21: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:374 - ReplaceConstant - 1 -> 0
   * Goal 40. Weak Mutation 22: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - ReplaceVariable newarray -> array
   * Goal 41. Weak Mutation 23: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - InsertUnaryOp Negation of i
   * Goal 42. Weak Mutation 24: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - InsertUnaryOp IINC 1 i
   * Goal 43. Weak Mutation 25: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - InsertUnaryOp IINC -1 i
   * Goal 44. Weak Mutation 26: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - ReplaceConstant - 1 -> 0
   * Goal 45. Weak Mutation 27: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - ReplaceVariable array -> newarray
   * Goal 46. Weak Mutation 28: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - InsertUnaryOp Negation of i
   * Goal 47. Weak Mutation 29: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - InsertUnaryOp IINC 1 i
   * Goal 48. Weak Mutation 30: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - InsertUnaryOp IINC -1 i
   * Goal 49. Weak Mutation 31: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:375 - ReplaceConstant - 0 -> 1
   * Goal 50. Weak Mutation 32: org.apache.commons.lang3.text.translate.EntityArrays.invert([[Ljava/lang/String;)[[Ljava/lang/String;:377 - ReplaceVariable newarray -> array
   */

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      String[][] stringArray0 = EntityArrays.ISO8859_1_ESCAPE();
      String[][] stringArray1 = EntityArrays.invert(stringArray0);
      assertNotSame(stringArray1, stringArray0);
  }
}