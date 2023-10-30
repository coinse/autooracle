/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 29 09:27:15 GMT 2023
 */

package org.apache.commons.lang3.time;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.commons.lang3.time.FastDateFormat;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class FastDateFormat_ESTest extends FastDateFormat_ESTest_scaffolding {

  //Test case number: 0
  /*
   * 5 covered goals:
   * Goal 1. org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: root-Branch
   * Goal 2. Branch org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: root-Branch in context: org.apache.commons.lang3.time.FastDateFormat:getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   * Goal 3. getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;_java.lang.NullPointerException_EXPLICIT
   * Goal 4. org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: Line 165
   * Goal 5. [METHOD] org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   */

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone((-772), ",", 3, 0, 1, 3, 1, 0, 3, (-772));
      Locale locale0 = Locale.ENGLISH;
      // Undeclared exception!
      try { 
        FastDateFormat.getInstance((String) null, (TimeZone) simpleTimeZone0, locale0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // pattern must not be null
         //
         verifyException("org.apache.commons.lang3.time.FormatCache", e);
      }
  }

  //Test case number: 1
  /*
   * 11 covered goals:
   * Goal 1. org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: root-Branch
   * Goal 2. org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: root-Branch
   * Goal 3. Branch org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: root-Branch in context: org.apache.commons.lang3.time.FastDateFormat:getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   * Goal 4. Branch org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: root-Branch in context: org.apache.commons.lang3.time.FastDateFormat:parse(Ljava/lang/String;)Ljava/util/Date;
   * Goal 5. parse(Ljava/lang/String;)Ljava/util/Date;_java.lang.NullPointerException_IMPLICIT
   * Goal 6. [Output]: org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;:NonNull:org.apache.commons.lang3.time.FastDateFormat:getMaxLengthEstimate()I:Positive
   * Goal 7. org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: Line 165
   * Goal 8. org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: Line 472
   * Goal 9. [METHOD] org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   * Goal 10. [METHOD] org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;
   * Goal 11. [METHODNOEX] org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   */

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(0, "(The ", 0, 0, 2390, 0, (-1173), 0, 0, 0, (-1173), 0, 58);
      Locale locale0 = Locale.ENGLISH;
      FastDateFormat fastDateFormat0 = FastDateFormat.getInstance("D+|E+|F+|G+|H+|K+|M+|S+|W+|Z+|a+|d+|h+|k+|m+|s+|w+|y+|z+|''|'[^']++(''[^']*+)*+'|[^'A-Za-z]++", (TimeZone) simpleTimeZone0, locale0);
      // Undeclared exception!
      try { 
        fastDateFormat0.parse((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.lang3.time.FastDateParser", e);
      }
  }

  //Test case number: 2
  /*
   * 11 covered goals:
   * Goal 1. org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: root-Branch
   * Goal 2. org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: root-Branch
   * Goal 3. Branch org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: root-Branch in context: org.apache.commons.lang3.time.FastDateFormat:getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   * Goal 4. Branch org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: root-Branch in context: org.apache.commons.lang3.time.FastDateFormat:parse(Ljava/lang/String;)Ljava/util/Date;
   * Goal 5. parse(Ljava/lang/String;)Ljava/util/Date;_java.text.ParseException_DECLARED
   * Goal 6. [Output]: org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;:NonNull:org.apache.commons.lang3.time.FastDateFormat:getMaxLengthEstimate()I:Positive
   * Goal 7. org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: Line 165
   * Goal 8. org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: Line 472
   * Goal 9. [METHOD] org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   * Goal 10. [METHOD] org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;
   * Goal 11. [METHODNOEX] org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   */

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      Locale locale0 = new Locale("Mm", "Mm", "Mm");
      FastDateFormat fastDateFormat0 = FastDateFormat.getInstance("Mm", (TimeZone) null, locale0);
      try { 
        fastDateFormat0.parse("Mm");
        fail("Expecting exception: ParseException");
      
      } catch(ParseException e) {
         //
         // Unparseable date: \"Mm\" does not match (\\p{IsNd}{1}+)(\\p{IsNd}++)
         //
         verifyException("org.apache.commons.lang3.time.FastDateParser", e);
      }
  }

  //Test case number: 3
  /*
   * 9 covered goals:
   * Goal 1. org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: root-Branch
   * Goal 2. Branch org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: root-Branch in context: org.apache.commons.lang3.time.FastDateFormat:getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   * Goal 3. Branch org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: root-Branch in context: 
   * Goal 4. getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;_java.lang.IllegalArgumentException_EXPLICIT
   * Goal 5. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 368
   * Goal 6. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 369
   * Goal 7. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 370
   * Goal 8. org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;: Line 165
   * Goal 9. [METHOD] org.apache.commons.lang3.time.FastDateFormat.getInstance(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)Lorg/apache/commons/lang3/time/FastDateFormat;
   */

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone((-873), "Unparseable date: \"", (-873), 0, 0, (-873), (-1422), 0, 0, (-1996), (-432), 0, 415);
      Locale locale0 = Locale.TRADITIONAL_CHINESE;
      // Undeclared exception!
      try { 
        FastDateFormat.getInstance("", (TimeZone) simpleTimeZone0, locale0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Invalid pattern
         //
         verifyException("org.apache.commons.lang3.time.FastDateParser", e);
      }
  }

  //Test case number: 4
  /*
   * 5 covered goals:
   * Goal 1. Branch org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: root-Branch in context: 
   * Goal 2. <init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V_java.lang.IllegalArgumentException_EXPLICIT
   * Goal 3. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 368
   * Goal 4. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 369
   * Goal 5. [METHOD] org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V
   */

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      Locale locale0 = Locale.KOREA;
      FastDateFormat fastDateFormat0 = null;
      try {
        fastDateFormat0 = new FastDateFormat("gJ77b", (TimeZone) null, locale0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Illegal pattern component: g
         //
         verifyException("org.apache.commons.lang3.time.FastDatePrinter", e);
      }
  }

  //Test case number: 5
  /*
   * 14 covered goals:
   * Goal 1. org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: root-Branch
   * Goal 2. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: root-Branch
   * Goal 3. Branch org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: root-Branch in context: 
   * Goal 4. Branch org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: root-Branch in context: org.apache.commons.lang3.time.FastDateFormat:parse(Ljava/lang/String;)Ljava/util/Date;
   * Goal 5. [Output]: org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;:NonNull
   * Goal 6. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 368
   * Goal 7. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 369
   * Goal 8. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 370
   * Goal 9. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 371
   * Goal 10. org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;: Line 472
   * Goal 11. [METHOD] org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V
   * Goal 12. [METHOD] org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;
   * Goal 13. [METHODNOEX] org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V
   * Goal 14. [METHODNOEX] org.apache.commons.lang3.time.FastDateFormat.parse(Ljava/lang/String;)Ljava/util/Date;
   */

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone((-1022), "t'Xpq&|Y0bRHd:I|~");
      Locale locale0 = Locale.CHINA;
      FastDateFormat fastDateFormat0 = new FastDateFormat("]", simpleTimeZone0, locale0);
      Date date0 = fastDateFormat0.parse("]");
      assertNotNull(date0);
  }

  //Test case number: 6
  /*
   * 5 covered goals:
   * Goal 1. Branch org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: root-Branch in context: 
   * Goal 2. <init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V_java.lang.NullPointerException_IMPLICIT
   * Goal 3. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 368
   * Goal 4. org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V: Line 369
   * Goal 5. [METHOD] org.apache.commons.lang3.time.FastDateFormat.<init>(Ljava/lang/String;Ljava/util/TimeZone;Ljava/util/Locale;)V
   */

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      Locale locale0 = Locale.GERMANY;
      FastDateFormat fastDateFormat0 = null;
      try {
        fastDateFormat0 = new FastDateFormat((String) null, (TimeZone) null, locale0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.lang3.time.FastDatePrinter", e);
      }
  }
}