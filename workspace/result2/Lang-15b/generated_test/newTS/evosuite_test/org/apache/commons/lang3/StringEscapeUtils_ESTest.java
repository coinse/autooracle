/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 29 10:13:04 GMT 2023
 */

package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class StringEscapeUtils_ESTest extends StringEscapeUtils_ESTest_scaffolding {

  //Test case number: 0
  /*
   * 17 covered goals:
   * Goal 1. org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: root-Branch
   * Goal 2. Branch org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: root-Branch in context: org.apache.commons.lang3.StringEscapeUtils:escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 3. [Output]: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:EmptyString
   * Goal 4. org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: Line 433
   * Goal 5. [METHOD] org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 6. [METHODNOEX] org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 7. Weak Mutation 44: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_XML
   * Goal 8. Weak Mutation 45: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_HTML4
   * Goal 9. Weak Mutation 46: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_CSV
   * Goal 10. Weak Mutation 47: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_ECMASCRIPT
   * Goal 11. Weak Mutation 48: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_CSV
   * Goal 12. Weak Mutation 49: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_XML
   * Goal 13. Weak Mutation 50: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_HTML3
   * Goal 14. Weak Mutation 51: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_ECMASCRIPT
   * Goal 15. Weak Mutation 52: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_JAVA
   * Goal 16. Weak Mutation 53: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_HTML3
   * Goal 17. Weak Mutation 54: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_JAVA
   */

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      String string0 = StringEscapeUtils.escapeHtml4("");
      assertEquals("", string0);
  }

  //Test case number: 1
  /*
   * 2 covered goals:
   * Goal 1. [METHOD] org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.<init>()V
   * Goal 2. [METHODNOEX] org.apache.commons.lang3.StringEscapeUtils.CsvUnescaper.<init>()V
   */

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      StringEscapeUtils.CsvUnescaper stringEscapeUtils_CsvUnescaper0 = new StringEscapeUtils.CsvUnescaper();
  }

  //Test case number: 2
  /*
   * 17 covered goals:
   * Goal 1. org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: root-Branch
   * Goal 2. Branch org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: root-Branch in context: org.apache.commons.lang3.StringEscapeUtils:escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 3. [Output]: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:Null
   * Goal 4. org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: Line 433
   * Goal 5. [METHOD] org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 6. [METHODNOEX] org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 7. Weak Mutation 44: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_XML
   * Goal 8. Weak Mutation 45: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_HTML4
   * Goal 9. Weak Mutation 46: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_CSV
   * Goal 10. Weak Mutation 47: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_ECMASCRIPT
   * Goal 11. Weak Mutation 48: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_CSV
   * Goal 12. Weak Mutation 49: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_XML
   * Goal 13. Weak Mutation 50: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_HTML3
   * Goal 14. Weak Mutation 51: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_ECMASCRIPT
   * Goal 15. Weak Mutation 52: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_JAVA
   * Goal 16. Weak Mutation 53: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_HTML3
   * Goal 17. Weak Mutation 54: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_JAVA
   */

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      String string0 = StringEscapeUtils.escapeHtml4((String) null);
      assertNull(string0);
  }

  //Test case number: 3
  /*
   * 2 covered goals:
   * Goal 1. [METHOD] org.apache.commons.lang3.StringEscapeUtils.<init>()V
   * Goal 2. [METHODNOEX] org.apache.commons.lang3.StringEscapeUtils.<init>()V
   */

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      StringEscapeUtils stringEscapeUtils0 = new StringEscapeUtils();
  }

  //Test case number: 4
  /*
   * 17 covered goals:
   * Goal 1. org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: root-Branch
   * Goal 2. Branch org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: root-Branch in context: org.apache.commons.lang3.StringEscapeUtils:escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 3. [Output]: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:NonEmptyString
   * Goal 4. org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;: Line 433
   * Goal 5. [METHOD] org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 6. [METHODNOEX] org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;
   * Goal 7. Weak Mutation 44: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_XML
   * Goal 8. Weak Mutation 45: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_HTML4
   * Goal 9. Weak Mutation 46: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_CSV
   * Goal 10. Weak Mutation 47: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_ECMASCRIPT
   * Goal 11. Weak Mutation 48: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_CSV
   * Goal 12. Weak Mutation 49: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_XML
   * Goal 13. Weak Mutation 50: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_HTML3
   * Goal 14. Weak Mutation 51: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_ECMASCRIPT
   * Goal 15. Weak Mutation 52: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_JAVA
   * Goal 16. Weak Mutation 53: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> ESCAPE_HTML3
   * Goal 17. Weak Mutation 54: org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(Ljava/lang/String;)Ljava/lang/String;:433 - ReplaceVariable ESCAPE_HTML4 -> UNESCAPE_JAVA
   */

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      String string0 = StringEscapeUtils.escapeHtml4("1");
      assertEquals("1", string0);
  }

  //Test case number: 5
  /*
   * 2 covered goals:
   * Goal 1. [METHOD] org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.<init>()V
   * Goal 2. [METHODNOEX] org.apache.commons.lang3.StringEscapeUtils.CsvEscaper.<init>()V
   */

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      StringEscapeUtils.CsvEscaper stringEscapeUtils_CsvEscaper0 = new StringEscapeUtils.CsvEscaper();
  }
}