/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 29 17:45:21 GMT 2023
 */

package org.apache.commons.lang.exception;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.commons.lang.exception.NestableError;
import org.apache.commons.lang.exception.NestableException;
import org.apache.commons.lang.exception.NestableRuntimeException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.evosuite.runtime.mock.java.io.MockPrintWriter;
import org.evosuite.runtime.mock.java.lang.MockThrowable;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class NestableRuntimeException_ESTest extends NestableRuntimeException_ESTest_scaffolding {

  //Test case number: 0
  /*
   * 12 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: root-Branch
   * Goal 2. Branch org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:printPartialStackTrace(Ljava/io/PrintWriter;)V
   * Goal 3. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 4. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 5. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: Line 200
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: Line 201
   * Goal 9. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 10. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V
   * Goal 11. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 12. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V
   */

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException();
      MockFile mockFile0 = new MockFile("Cfm%={'?D#");
      MockPrintWriter mockPrintWriter0 = new MockPrintWriter(mockFile0);
      nestableRuntimeException0.printPartialStackTrace(mockPrintWriter0);
      assertNull(nestableRuntimeException0.getMessage());
  }

  //Test case number: 1
  /*
   * 12 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;: root-Branch
   * Goal 2. Branch org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:getCause()Ljava/lang/Throwable;
   * Goal 3. [Output]: org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;:Null
   * Goal 4. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 5. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;: Line 102
   * Goal 9. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;)V
   * Goal 10. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;
   * Goal 11. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;)V
   * Goal 12. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;
   */

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException("xWH<5h");
      Throwable throwable0 = nestableRuntimeException0.getCause();
      assertNull(throwable0);
  }

  //Test case number: 2
  /*
   * 15 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: root-Branch
   * Goal 2. org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V: root-Branch
   * Goal 3. Branch org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:printStackTrace(Ljava/io/PrintStream;)V
   * Goal 4. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 5. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: Line 200
   * Goal 9. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: Line 201
   * Goal 10. org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V: Line 186
   * Goal 11. org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V: Line 187
   * Goal 12. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 13. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V
   * Goal 14. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 15. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V
   */

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException();
      ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(770);
      MockPrintStream mockPrintStream0 = new MockPrintStream(byteArrayOutputStream0);
      nestableRuntimeException0.printStackTrace((PrintStream) mockPrintStream0);
      assertNull(nestableRuntimeException0.getMessage());
  }

  //Test case number: 3
  /*
   * 17 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;: root-Branch
   * Goal 2. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: root-Branch
   * Goal 3. Branch org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:<init>(Ljava/lang/Throwable;)V
   * Goal 4. Branch org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:getCause()Ljava/lang/Throwable;
   * Goal 5. [Output]: org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;:NonNull
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 9. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 10. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 81
   * Goal 11. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 82
   * Goal 12. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 83
   * Goal 13. org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;: Line 102
   * Goal 14. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V
   * Goal 15. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;
   * Goal 16. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V
   * Goal 17. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.getCause()Ljava/lang/Throwable;
   */

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      NestableError nestableError0 = new NestableError();
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException(nestableError0);
      Throwable throwable0 = nestableRuntimeException0.getCause();
      assertSame(nestableError0, throwable0);
  }

  //Test case number: 4
  /*
   * 10 covered goals:
   * Goal 1. Branch org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:printStackTrace(Ljava/io/PrintStream;)V
   * Goal 2. printStackTrace(Ljava/io/PrintStream;)V_java.lang.NullPointerException_IMPLICIT
   * Goal 3. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 4. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 5. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V: Line 186
   * Goal 8. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;)V
   * Goal 9. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V
   * Goal 10. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;)V
   */

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException("6M|z5<0OgcP^ ");
      // Undeclared exception!
      try { 
        nestableRuntimeException0.printStackTrace((PrintStream) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.lang.exception.NestableDelegate", e);
      }
  }

  //Test case number: 5
  /*
   * 18 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: root-Branch
   * Goal 2. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: root-Branch
   * Goal 3. Branch org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:printStackTrace(Ljava/io/PrintStream;)V
   * Goal 4. Branch org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:<init>(Ljava/lang/Throwable;)V
   * Goal 5. printStackTrace(Ljava/io/PrintStream;)V_java.lang.NullPointerException_EXPLICIT
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 9. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 10. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 81
   * Goal 11. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 82
   * Goal 12. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 83
   * Goal 13. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: Line 200
   * Goal 14. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: Line 201
   * Goal 15. org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V: Line 186
   * Goal 16. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V
   * Goal 17. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.printStackTrace(Ljava/io/PrintStream;)V
   * Goal 18. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V
   */

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      NestableException nestableException0 = new NestableException();
      MockThrowable mockThrowable0 = new MockThrowable(" >= ", nestableException0);
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException(mockThrowable0);
      BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream((OutputStream) null, 1);
      MockPrintStream mockPrintStream0 = new MockPrintStream(bufferedOutputStream0);
      // Undeclared exception!
      try { 
        nestableRuntimeException0.printStackTrace((PrintStream) mockPrintStream0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.io.BufferedOutputStream", e);
      }
  }

  //Test case number: 6
  /*
   * 10 covered goals:
   * Goal 1. Branch org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:printPartialStackTrace(Ljava/io/PrintWriter;)V
   * Goal 2. printPartialStackTrace(Ljava/io/PrintWriter;)V_java.lang.NullPointerException_IMPLICIT
   * Goal 3. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 4. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 5. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V: Line 200
   * Goal 8. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 9. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.printPartialStackTrace(Ljava/io/PrintWriter;)V
   * Goal 10. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   */

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException();
      // Undeclared exception!
      try { 
        nestableRuntimeException0.printPartialStackTrace((PrintWriter) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  //Test case number: 7
  /*
   * 14 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I4 Branch 1 IFNULL L113 - false
   * Goal 2. Branch org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I4 Branch 1 IFNULL L113 - false in context: org.apache.commons.lang.exception.NestableRuntimeException:getMessage()Ljava/lang/String;
   * Goal 3. [Output]: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:NonEmptyString
   * Goal 4. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 5. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 113
   * Goal 9. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 114
   * Goal 10. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;)V
   * Goal 11. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;
   * Goal 12. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;)V
   * Goal 13. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;
   * Goal 14. Weak Mutation 0: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:113 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException("xWH<5h");
      String string0 = nestableRuntimeException0.getMessage();
      assertEquals("xWH<5h", string0);
  }

  //Test case number: 8
  /*
   * 13 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: root-Branch
   * Goal 2. Branch org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:<init>(Ljava/lang/String;Ljava/lang/Throwable;)V
   * Goal 3. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 4. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 5. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 94
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 95
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 96
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 9. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 10. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 11. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V
   * Goal 12. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 13. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V
   */

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException();
      NestableRuntimeException nestableRuntimeException1 = new NestableRuntimeException("o,Ab42=", nestableRuntimeException0);
      assertFalse(nestableRuntimeException1.equals((Object)nestableRuntimeException0));
  }

  //Test case number: 9
  /*
   * 18 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I4 Branch 1 IFNULL L113 - true
   * Goal 2. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I14 Branch 2 IFNULL L115 - true
   * Goal 3. Branch org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I4 Branch 1 IFNULL L113 - true in context: org.apache.commons.lang.exception.NestableRuntimeException:getMessage()Ljava/lang/String;
   * Goal 4. Branch org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I14 Branch 2 IFNULL L115 - true in context: org.apache.commons.lang.exception.NestableRuntimeException:getMessage()Ljava/lang/String;
   * Goal 5. [Output]: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:Null
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 9. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 10. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 113
   * Goal 11. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 115
   * Goal 12. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 118
   * Goal 13. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 14. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;
   * Goal 15. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>()V
   * Goal 16. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;
   * Goal 17. Weak Mutation 0: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:113 - ReplaceComparisonOperator = null -> != null
   * Goal 18. Weak Mutation 1: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:115 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException();
      String string0 = nestableRuntimeException0.getMessage();
      assertNull(string0);
  }

  //Test case number: 10
  /*
   * 14 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I4 Branch 1 IFNULL L113 - false
   * Goal 2. Branch org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I4 Branch 1 IFNULL L113 - false in context: org.apache.commons.lang.exception.NestableRuntimeException:getMessage()Ljava/lang/String;
   * Goal 3. [Output]: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:EmptyString
   * Goal 4. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 5. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 6. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 7. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 113
   * Goal 9. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 114
   * Goal 10. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;)V
   * Goal 11. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;
   * Goal 12. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;)V
   * Goal 13. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;
   * Goal 14. Weak Mutation 0: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:113 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException("");
      String string0 = nestableRuntimeException0.getMessage();
      assertEquals("", string0);
  }

  //Test case number: 11
  /*
   * 23 covered goals:
   * Goal 1. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: root-Branch
   * Goal 2. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I4 Branch 1 IFNULL L113 - true
   * Goal 3. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I14 Branch 2 IFNULL L115 - false
   * Goal 4. Branch org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: root-Branch in context: org.apache.commons.lang.exception.NestableRuntimeException:<init>(Ljava/lang/Throwable;)V
   * Goal 5. Branch org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I4 Branch 1 IFNULL L113 - true in context: org.apache.commons.lang.exception.NestableRuntimeException:getMessage()Ljava/lang/String;
   * Goal 6. Branch org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: I14 Branch 2 IFNULL L115 - false in context: org.apache.commons.lang.exception.NestableRuntimeException:getMessage()Ljava/lang/String;
   * Goal 7. [Output]: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:NonEmptyString
   * Goal 8. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 47
   * Goal 9. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/String;Ljava/lang/Throwable;)V: Line 53
   * Goal 10. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 47
   * Goal 11. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 53
   * Goal 12. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 81
   * Goal 13. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 82
   * Goal 14. org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V: Line 83
   * Goal 15. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 113
   * Goal 16. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 115
   * Goal 17. org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;: Line 116
   * Goal 18. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V
   * Goal 19. [METHOD] org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;
   * Goal 20. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.<init>(Ljava/lang/Throwable;)V
   * Goal 21. [METHODNOEX] org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;
   * Goal 22. Weak Mutation 0: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:113 - ReplaceComparisonOperator = null -> != null
   * Goal 23. Weak Mutation 1: org.apache.commons.lang.exception.NestableRuntimeException.getMessage()Ljava/lang/String;:115 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      MockThrowable mockThrowable0 = new MockThrowable();
      NestableRuntimeException nestableRuntimeException0 = new NestableRuntimeException(mockThrowable0);
      String string0 = nestableRuntimeException0.getMessage();
      assertEquals("org.evosuite.runtime.mock.java.lang.MockThrowable", string0);
  }
}