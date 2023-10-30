/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 29 11:57:52 GMT 2023
 */

package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.lang3.SystemUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SystemUtils_ESTest extends SystemUtils_ESTest_scaffolding {

  //Test case number: 0
  /*
   * 16 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false
   * Goal 2. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - true
   * Goal 3. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I16 Branch 11 IFLE L1600 - true
   * Goal 4. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false in context: 
   * Goal 5. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - true in context: 
   * Goal 6. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I16 Branch 11 IFLE L1600 - true in context: 
   * Goal 7. [Output]: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:False
   * Goal 8. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1597
   * Goal 9. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1600
   * Goal 10. [METHOD] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 11. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 12. Weak Mutation 854: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator = null -> != null
   * Goal 13. Weak Mutation 901: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator != null -> = null
   * Goal 14. Weak Mutation 995: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 0 -> 1
   * Goal 15. Weak Mutation 997: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceComparisonOperator <= -> ==
   * Goal 16. Weak Mutation 1096: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      boolean boolean0 = SystemUtils.isOSMatch("HP-UXLinux", "*(Uyn-KZX!S$aZV", "4%f4P2sP}pmqlY", "*(Uyn-KZX!S$aZV");
      assertFalse(boolean0);
  }

  //Test case number: 1
  /*
   * 12 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false
   * Goal 2. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - false
   * Goal 3. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false in context: 
   * Goal 4. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - false in context: 
   * Goal 5. [Output]: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:False
   * Goal 6. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1597
   * Goal 7. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1598
   * Goal 8. [METHOD] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 9. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 10. Weak Mutation 854: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator = null -> != null
   * Goal 11. Weak Mutation 901: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator != null -> = null
   * Goal 12. Weak Mutation 902: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1598 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      boolean boolean0 = SystemUtils.isOSMatch("SolarisLinux", (String) null, (String) null, "Linu");
      assertFalse(boolean0);
  }

  //Test case number: 2
  /*
   * 10 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: I5 Branch 6 IFLT L1534 - true
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: I5 Branch 6 IFLT L1534 - true in context: org.apache.commons.lang3.SystemUtils:isJavaVersionAtLeast(F)Z
   * Goal 3. [Output]: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:False
   * Goal 4. org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: Line 1534
   * Goal 5. [METHOD] org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z
   * Goal 6. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z
   * Goal 7. Weak Mutation 653: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - InsertUnaryOp Negation
   * Goal 8. Weak Mutation 655: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - InsertUnaryOp Negation of requiredVersion
   * Goal 9. Weak Mutation 656: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - ReplaceComparisonOperator < -> -2
   * Goal 10. Weak Mutation 660: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      boolean boolean0 = SystemUtils.isJavaVersionAtLeast((float) 180);
      assertFalse(boolean0);
  }

  //Test case number: 3
  /*
   * 11 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false
   * Goal 2. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - true
   * Goal 3. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false in context: 
   * Goal 4. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - true in context: 
   * Goal 5. isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z_java.lang.NullPointerException_IMPLICIT
   * Goal 6. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1597
   * Goal 7. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1600
   * Goal 8. [METHOD] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 9. Weak Mutation 854: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator = null -> != null
   * Goal 10. Weak Mutation 901: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator != null -> = null
   * Goal 11. Weak Mutation 995: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      // Undeclared exception!
      try { 
        SystemUtils.isOSMatch("9s{&", "AIX", (String) null, (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  //Test case number: 4
  /*
   * 8 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 8 IFNONNULL L1574 - true
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 8 IFNONNULL L1574 - true in context: 
   * Goal 3. isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z_java.lang.NullPointerException_IMPLICIT
   * Goal 4. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1574
   * Goal 5. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1577
   * Goal 6. [METHOD] org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 7. Weak Mutation 717: org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z:1574 - ReplaceComparisonOperator != null -> = null
   * Goal 8. Weak Mutation 807: org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z:1577 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      // Undeclared exception!
      try { 
        SystemUtils.isJavaVersionMatch("SolarisLinux", (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  //Test case number: 5
  /*
   * 9 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 13 IFNONNULL L1616 - false
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 13 IFNONNULL L1616 - false in context: 
   * Goal 3. [Output]: org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z:False
   * Goal 4. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1616
   * Goal 5. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1617
   * Goal 6. [METHOD] org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 7. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 8. Weak Mutation 1141: org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z:1616 - ReplaceComparisonOperator != null -> = null
   * Goal 9. Weak Mutation 1142: org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z:1617 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      boolean boolean0 = SystemUtils.isOSNameMatch((String) null, "25.302-b08");
      assertFalse(boolean0);
  }

  //Test case number: 6
  /*
   * 10 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: I5 Branch 6 IFLT L1534 - false
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: I5 Branch 6 IFLT L1534 - false in context: org.apache.commons.lang3.SystemUtils:isJavaVersionAtLeast(F)Z
   * Goal 3. [Output]: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:True
   * Goal 4. org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: Line 1534
   * Goal 5. [METHOD] org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z
   * Goal 6. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z
   * Goal 7. Weak Mutation 653: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - InsertUnaryOp Negation
   * Goal 8. Weak Mutation 655: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - InsertUnaryOp Negation of requiredVersion
   * Goal 9. Weak Mutation 658: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - ReplaceComparisonOperator < -> <=
   * Goal 10. Weak Mutation 659: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - ReplaceConstant - 1 -> 0
   */

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      boolean boolean0 = SystemUtils.isJavaVersionAtLeast(1.8F);
      assertTrue(boolean0);
  }

  //Test case number: 7
  /*
   * 8 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 13 IFNONNULL L1616 - true
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 13 IFNONNULL L1616 - true in context: 
   * Goal 3. isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z_java.lang.NullPointerException_IMPLICIT
   * Goal 4. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1616
   * Goal 5. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1619
   * Goal 6. [METHOD] org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 7. Weak Mutation 1141: org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z:1616 - ReplaceComparisonOperator != null -> = null
   * Goal 8. Weak Mutation 1231: org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z:1619 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      // Undeclared exception!
      try { 
        SystemUtils.isOSNameMatch("Oracle Corporation", (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  //Test case number: 8
  /*
   * 9 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 8 IFNONNULL L1574 - true
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 8 IFNONNULL L1574 - true in context: 
   * Goal 3. [Output]: org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z:True
   * Goal 4. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1574
   * Goal 5. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1577
   * Goal 6. [METHOD] org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 7. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 8. Weak Mutation 717: org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z:1574 - ReplaceComparisonOperator != null -> = null
   * Goal 9. Weak Mutation 807: org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z:1577 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      boolean boolean0 = SystemUtils.isJavaVersionMatch("IrixLinux", "IrixLinux");
      assertTrue(boolean0);
  }

  //Test case number: 9
  /*
   * 10 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: I5 Branch 6 IFLT L1534 - false
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: I5 Branch 6 IFLT L1534 - false in context: org.apache.commons.lang3.SystemUtils:isJavaVersionAtLeast(F)Z
   * Goal 3. [Output]: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:True
   * Goal 4. org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z: Line 1534
   * Goal 5. [METHOD] org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z
   * Goal 6. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z
   * Goal 7. Weak Mutation 653: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - InsertUnaryOp Negation
   * Goal 8. Weak Mutation 655: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - InsertUnaryOp Negation of requiredVersion
   * Goal 9. Weak Mutation 657: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - ReplaceComparisonOperator < -> !=
   * Goal 10. Weak Mutation 659: org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(F)Z:1534 - ReplaceConstant - 1 -> 0
   */

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      boolean boolean0 = SystemUtils.isJavaVersionAtLeast((-1502.084F));
      assertTrue(boolean0);
  }

  //Test case number: 10
  /*
   * 2 covered goals:
   * Goal 1. [METHOD] org.apache.commons.lang3.SystemUtils.<init>()V
   * Goal 2. [METHODNOEX] org.apache.commons.lang3.SystemUtils.<init>()V
   */

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      SystemUtils systemUtils0 = new SystemUtils();
      assertFalse(SystemUtils.IS_OS_IRIX);
  }

  //Test case number: 11
  /*
   * 9 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - true
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - true in context: 
   * Goal 3. [Output]: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:False
   * Goal 4. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1597
   * Goal 5. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1598
   * Goal 6. [METHOD] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 7. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 8. Weak Mutation 854: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator = null -> != null
   * Goal 9. Weak Mutation 902: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1598 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      boolean boolean0 = SystemUtils.isOSMatch((String) null, "~iu\u0004p", (String) null, "~iu\u0004p");
      assertFalse(boolean0);
  }

  //Test case number: 12
  /*
   * 9 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 13 IFNONNULL L1616 - true
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 13 IFNONNULL L1616 - true in context: 
   * Goal 3. [Output]: org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z:True
   * Goal 4. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1616
   * Goal 5. org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1619
   * Goal 6. [METHOD] org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 7. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 8. Weak Mutation 1141: org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z:1616 - ReplaceComparisonOperator != null -> = null
   * Goal 9. Weak Mutation 1231: org.apache.commons.lang3.SystemUtils.isOSNameMatch(Ljava/lang/String;Ljava/lang/String;)Z:1619 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      boolean boolean0 = SystemUtils.isOSNameMatch("\t\"s:9E>--", "\t\"s:9E>--");
      assertTrue(boolean0);
  }

  //Test case number: 13
  /*
   * 9 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 8 IFNONNULL L1574 - false
   * Goal 2. Branch org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 8 IFNONNULL L1574 - false in context: 
   * Goal 3. [Output]: org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z:False
   * Goal 4. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1574
   * Goal 5. org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z: Line 1575
   * Goal 6. [METHOD] org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 7. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 8. Weak Mutation 717: org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z:1574 - ReplaceComparisonOperator != null -> = null
   * Goal 9. Weak Mutation 718: org.apache.commons.lang3.SystemUtils.isJavaVersionMatch(Ljava/lang/String;Ljava/lang/String;)Z:1575 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      boolean boolean0 = SystemUtils.isJavaVersionMatch((String) null, "~iu\u0004p");
      assertFalse(boolean0);
  }

  //Test case number: 14
  /*
   * 20 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false
   * Goal 2. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - true
   * Goal 3. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I16 Branch 11 IFLE L1600 - false
   * Goal 4. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I21 Branch 12 IFLE L1600 - true
   * Goal 5. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false in context: 
   * Goal 6. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - true in context: 
   * Goal 7. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I16 Branch 11 IFLE L1600 - false in context: 
   * Goal 8. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I21 Branch 12 IFLE L1600 - true in context: 
   * Goal 9. [Output]: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:False
   * Goal 10. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1597
   * Goal 11. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1600
   * Goal 12. [METHOD] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 13. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 14. Weak Mutation 854: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator = null -> != null
   * Goal 15. Weak Mutation 901: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator != null -> = null
   * Goal 16. Weak Mutation 995: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 0 -> 1
   * Goal 17. Weak Mutation 996: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceComparisonOperator <= -> -1
   * Goal 18. Weak Mutation 1091: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 0 -> 1
   * Goal 19. Weak Mutation 1093: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceComparisonOperator <= -> ==
   * Goal 20. Weak Mutation 1096: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      boolean boolean0 = SystemUtils.isOSMatch("y", "zZ-Y1d,JO^SiyuseJ.r\"gion", "y", "y");
      assertFalse(boolean0);
  }

  //Test case number: 15
  /*
   * 20 covered goals:
   * Goal 1. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false
   * Goal 2. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - true
   * Goal 3. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I16 Branch 11 IFLE L1600 - false
   * Goal 4. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I21 Branch 12 IFLE L1600 - false
   * Goal 5. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I3 Branch 9 IFNULL L1597 - false in context: 
   * Goal 6. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I5 Branch 10 IFNONNULL L1597 - true in context: 
   * Goal 7. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I21 Branch 12 IFLE L1600 - false in context: 
   * Goal 8. Branch org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: I16 Branch 11 IFLE L1600 - false in context: 
   * Goal 9. [Output]: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:True
   * Goal 10. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1597
   * Goal 11. org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z: Line 1600
   * Goal 12. [METHOD] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 13. [METHODNOEX] org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
   * Goal 14. Weak Mutation 854: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator = null -> != null
   * Goal 15. Weak Mutation 901: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1597 - ReplaceComparisonOperator != null -> = null
   * Goal 16. Weak Mutation 995: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 0 -> 1
   * Goal 17. Weak Mutation 996: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceComparisonOperator <= -> -1
   * Goal 18. Weak Mutation 1091: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 0 -> 1
   * Goal 19. Weak Mutation 1092: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceComparisonOperator <= -> -1
   * Goal 20. Weak Mutation 1095: org.apache.commons.lang3.SystemUtils.isOSMatch(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z:1600 - ReplaceConstant - 1 -> 0
   */

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      boolean boolean0 = SystemUtils.isOSMatch("N7i", "N7i", "N7i", "N7i");
      assertTrue(boolean0);
  }
}