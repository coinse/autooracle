/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 29 13:16:09 GMT 2023
 */

package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang3.StringUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class StringUtils_ESTest extends StringUtils_ESTest_scaffolding {

  //Test case number: 0
  /*
   * 2 covered goals:
   * Goal 1. [METHOD] org.apache.commons.lang3.StringUtils.<init>()V
   * Goal 2. [METHODNOEX] org.apache.commons.lang3.StringUtils.<init>()V
   */

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      StringUtils stringUtils0 = new StringUtils();
      assertEquals((-1), StringUtils.INDEX_NOT_FOUND);
  }

  //Test case number: 1
  /*
   * 10 covered goals:
   * Goal 1. org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z: I3 Branch 1 IFNULL L191 - false
   * Goal 2. org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z: I6 Branch 2 IFNE L191 - false
   * Goal 3. Branch org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z: I6 Branch 2 IFNE L191 - false in context: org.apache.commons.lang3.StringUtils:isEmpty(Ljava/lang/CharSequence;)Z
   * Goal 4. Branch org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z: I3 Branch 1 IFNULL L191 - false in context: org.apache.commons.lang3.StringUtils:isEmpty(Ljava/lang/CharSequence;)Z
   * Goal 5. [Output]: org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z:True
   * Goal 6. org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z: Line 191
   * Goal 7. [METHOD] org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z
   * Goal 8. [METHODNOEX] org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z
   * Goal 9. Weak Mutation 0: org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z:191 - ReplaceComparisonOperator = null -> != null
   * Goal 10. Weak Mutation 1: org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z:191 - ReplaceComparisonOperator != -> -1
   */

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      boolean boolean0 = StringUtils.isEmpty("");
      assertTrue(boolean0);
  }

  //Test case number: 2
  /*
   * 7 covered goals:
   * Goal 1. org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z: I3 Branch 1 IFNULL L191 - true
   * Goal 2. Branch org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z: I3 Branch 1 IFNULL L191 - true in context: org.apache.commons.lang3.StringUtils:isEmpty(Ljava/lang/CharSequence;)Z
   * Goal 3. [Output]: org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z:True
   * Goal 4. org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z: Line 191
   * Goal 5. [METHOD] org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z
   * Goal 6. [METHODNOEX] org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z
   * Goal 7. Weak Mutation 0: org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/CharSequence;)Z:191 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      boolean boolean0 = StringUtils.isEmpty((CharSequence) null);
      assertTrue(boolean0);
  }

  //Test case number: 3
  /*
   * 8 covered goals:
   * Goal 1. org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: I3 Branch 4 IFNULL L229 - true
   * Goal 2. Branch org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: I3 Branch 4 IFNULL L229 - true in context: org.apache.commons.lang3.StringUtils:isBlank(Ljava/lang/CharSequence;)Z
   * Goal 3. [Output]: org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z:True
   * Goal 4. org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: Line 229
   * Goal 5. org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: Line 230
   * Goal 6. [METHOD] org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z
   * Goal 7. [METHODNOEX] org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z
   * Goal 8. Weak Mutation 9: org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z:229 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      boolean boolean0 = StringUtils.isBlank((CharSequence) null);
      assertTrue(boolean0);
  }

  //Test case number: 4
  /*
   * 11 covered goals:
   * Goal 1. org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: I3 Branch 4 IFNULL L229 - false
   * Goal 2. org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: I9 Branch 5 IFNE L229 - false
   * Goal 3. Branch org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: I3 Branch 4 IFNULL L229 - false in context: org.apache.commons.lang3.StringUtils:isBlank(Ljava/lang/CharSequence;)Z
   * Goal 4. Branch org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: I9 Branch 5 IFNE L229 - false in context: org.apache.commons.lang3.StringUtils:isBlank(Ljava/lang/CharSequence;)Z
   * Goal 5. [Output]: org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z:True
   * Goal 6. org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: Line 229
   * Goal 7. org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z: Line 230
   * Goal 8. [METHOD] org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z
   * Goal 9. [METHODNOEX] org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z
   * Goal 10. Weak Mutation 9: org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z:229 - ReplaceComparisonOperator = null -> != null
   * Goal 11. Weak Mutation 10: org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z:229 - ReplaceComparisonOperator != -> -1
   */

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      boolean boolean0 = StringUtils.isBlank("");
      assertTrue(boolean0);
  }
}