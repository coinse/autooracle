/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 29 09:50:15 GMT 2023
 */

package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SerializationUtils_ESTest extends SerializationUtils_ESTest_scaffolding {

  //Test case number: 0
  /*
   * 12 covered goals:
   * Goal 1. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I3 Branch 4 IFNONNULL L131 - true in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I43 Branch 5 IFNULL L144 - false in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 3. serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V_java.lang.NullPointerException_IMPLICIT
   * Goal 4. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 131
   * Goal 5. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 134
   * Goal 6. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 137
   * Goal 7. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 138
   * Goal 8. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 144
   * Goal 9. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 145
   * Goal 10. [METHOD] org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 11. Weak Mutation 8: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:131 - ReplaceComparisonOperator != null -> = null
   * Goal 12. Weak Mutation 10: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:144 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream((OutputStream) null);
      // Undeclared exception!
      try { 
        SerializationUtils.serialize((Serializable) (byte)16, (OutputStream) bufferedOutputStream0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.io.BufferedOutputStream", e);
      }
  }

  //Test case number: 1
  /*
   * 37 covered goals:
   * Goal 1. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: root-Branch
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: I3 Branch 1 IFNONNULL L79 - true in context: org.apache.commons.lang3.SerializationUtils:clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 3. Branch org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: I47 Branch 2 IFNULL L104 - false in context: org.apache.commons.lang3.SerializationUtils:clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 4. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 79
   * Goal 5. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 82
   * Goal 6. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 83
   * Goal 7. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 85
   * Goal 8. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 88
   * Goal 9. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 95
   * Goal 10. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 96
   * Goal 11. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 104
   * Goal 12. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 105
   * Goal 13. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 109
   * Goal 14. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 162
   * Goal 15. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 163
   * Goal 16. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 164
   * Goal 17. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 131
   * Goal 18. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 134
   * Goal 19. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 137
   * Goal 20. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 138
   * Goal 21. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 144
   * Goal 22. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 145
   * Goal 23. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 149
   * Goal 24. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 151
   * Goal 25. [METHOD] org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 26. [METHODNOEX] org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 27. Weak Mutation 0: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:79 - ReplaceComparisonOperator != null -> = null
   * Goal 28. Weak Mutation 1: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:96 - ReplaceVariable readObject -> object
   * Goal 29. Weak Mutation 2: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:104 - ReplaceComparisonOperator = null -> != null
   * Goal 30. Weak Mutation 8: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:131 - ReplaceComparisonOperator != null -> = null
   * Goal 31. Weak Mutation 10: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:144 - ReplaceComparisonOperator = null -> != null
   * Goal 32. Weak Mutation 12: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 0
   * Goal 33. Weak Mutation 13: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 1
   * Goal 34. Weak Mutation 14: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> -1
   * Goal 35. Weak Mutation 15: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 511
   * Goal 36. Weak Mutation 16: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 513
   * Goal 37. Weak Mutation 23: org.apache.commons.lang3.SerializationUtils$ClassLoaderAwareObjectInputStream.resolveClass(Ljava/io/ObjectStreamClass;)Ljava/lang/Class;:266 - ReplaceConstant - 0 -> 1
   */

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Integer integer0 = new Integer(338);
      Integer integer1 = SerializationUtils.clone(integer0);
      assertTrue(integer1.equals((Object)integer0));
  }

  //Test case number: 2
  /*
   * 17 covered goals:
   * Goal 1. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I43 Branch 5 IFNULL L144 - false
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I3 Branch 4 IFNONNULL L131 - true in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 3. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I43 Branch 5 IFNULL L144 - false in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 4. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 131
   * Goal 5. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 134
   * Goal 6. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 137
   * Goal 7. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 138
   * Goal 8. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 144
   * Goal 9. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 145
   * Goal 10. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 147
   * Goal 11. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 149
   * Goal 12. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 150
   * Goal 13. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 151
   * Goal 14. [METHOD] org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 15. [METHODNOEX] org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 16. Weak Mutation 8: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:131 - ReplaceComparisonOperator != null -> = null
   * Goal 17. Weak Mutation 10: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:144 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("The InputStream must not be null");
      BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(mockFileOutputStream0);
      SerializationUtils.serialize((Serializable) 2, (OutputStream) bufferedOutputStream0);
      SerializationUtils.serialize((Serializable) "The InputStream must not be null", (OutputStream) bufferedOutputStream0);
  }

  //Test case number: 3
  /*
   * 13 covered goals:
   * Goal 1. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I73 Branch 6 IFNULL L144 - true
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I3 Branch 4 IFNONNULL L131 - true in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 3. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I73 Branch 6 IFNULL L144 - true in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 4. serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V_java.lang.NullPointerException_EXPLICIT
   * Goal 5. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 131
   * Goal 6. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 134
   * Goal 7. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 137
   * Goal 8. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 143
   * Goal 9. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 144
   * Goal 10. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 149
   * Goal 11. [METHOD] org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 12. Weak Mutation 8: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:131 - ReplaceComparisonOperator != null -> = null
   * Goal 13. Weak Mutation 11: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:144 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      DataOutputStream dataOutputStream0 = new DataOutputStream((OutputStream) null);
      MockPrintStream mockPrintStream0 = new MockPrintStream(dataOutputStream0);
      // Undeclared exception!
      try { 
        SerializationUtils.serialize((Serializable) (byte)8, (OutputStream) mockPrintStream0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.io.DataOutputStream", e);
      }
  }

  //Test case number: 4
  /*
   * 17 covered goals:
   * Goal 1. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I73 Branch 6 IFNULL L144 - false
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I3 Branch 4 IFNONNULL L131 - true in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 3. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I73 Branch 6 IFNULL L144 - false in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 4. serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V_org.evosuite.runtime.mock.java.lang.MockRuntimeException_EXPLICIT
   * Goal 5. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 131
   * Goal 6. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 134
   * Goal 7. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 137
   * Goal 8. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 138
   * Goal 9. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 140
   * Goal 10. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 141
   * Goal 11. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 143
   * Goal 12. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 144
   * Goal 13. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 145
   * Goal 14. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 149
   * Goal 15. [METHOD] org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 16. Weak Mutation 8: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:131 - ReplaceComparisonOperator != null -> = null
   * Goal 17. Weak Mutation 11: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:144 - ReplaceComparisonOperator = null -> != null
   */

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("wX", false);
      // Undeclared exception!
      try { 
        SerializationUtils.serialize((Serializable) (byte)119, (OutputStream) mockFileOutputStream0);
        fail("Expecting exception: RuntimeException");
      
      } catch(RuntimeException e) {
         //
         // org.evosuite.runtime.mock.java.lang.MockThrowable: Error in writing to file
         //
         verifyException("org.apache.commons.lang3.SerializationUtils", e);
      }
  }

  //Test case number: 5
  /*
   * 8 covered goals:
   * Goal 1. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: I3 Branch 1 IFNONNULL L79 - false
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: I3 Branch 1 IFNONNULL L79 - false in context: org.apache.commons.lang3.SerializationUtils:clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 3. [Output]: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:Null
   * Goal 4. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 79
   * Goal 5. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 80
   * Goal 6. [METHOD] org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 7. [METHODNOEX] org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 8. Weak Mutation 0: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:79 - ReplaceComparisonOperator != null -> = null
   */

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      String string0 = SerializationUtils.clone((String) null);
      assertNull(string0);
  }

  //Test case number: 6
  /*
   * 37 covered goals:
   * Goal 1. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: root-Branch
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: I3 Branch 1 IFNONNULL L79 - true in context: org.apache.commons.lang3.SerializationUtils:clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 3. Branch org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: I47 Branch 2 IFNULL L104 - false in context: org.apache.commons.lang3.SerializationUtils:clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 4. [Output]: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:NonNull
   * Goal 5. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 79
   * Goal 6. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 82
   * Goal 7. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 83
   * Goal 8. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 85
   * Goal 9. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 88
   * Goal 10. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 95
   * Goal 11. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 96
   * Goal 12. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 104
   * Goal 13. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 105
   * Goal 14. org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;: Line 109
   * Goal 15. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 162
   * Goal 16. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 163
   * Goal 17. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 164
   * Goal 18. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 131
   * Goal 19. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 134
   * Goal 20. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 137
   * Goal 21. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 138
   * Goal 22. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 144
   * Goal 23. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 145
   * Goal 24. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 149
   * Goal 25. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 151
   * Goal 26. [METHOD] org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 27. [METHODNOEX] org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;
   * Goal 28. Weak Mutation 0: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:79 - ReplaceComparisonOperator != null -> = null
   * Goal 29. Weak Mutation 1: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:96 - ReplaceVariable readObject -> object
   * Goal 30. Weak Mutation 2: org.apache.commons.lang3.SerializationUtils.clone(Ljava/io/Serializable;)Ljava/io/Serializable;:104 - ReplaceComparisonOperator = null -> != null
   * Goal 31. Weak Mutation 8: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:131 - ReplaceComparisonOperator != null -> = null
   * Goal 32. Weak Mutation 10: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:144 - ReplaceComparisonOperator = null -> != null
   * Goal 33. Weak Mutation 12: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 0
   * Goal 34. Weak Mutation 13: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 1
   * Goal 35. Weak Mutation 14: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> -1
   * Goal 36. Weak Mutation 15: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 511
   * Goal 37. Weak Mutation 16: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 513
   */

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      String string0 = SerializationUtils.clone("6");
      assertEquals("6", string0);
  }

  //Test case number: 7
  /*
   * 8 covered goals:
   * Goal 1. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I3 Branch 4 IFNONNULL L131 - false
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: I3 Branch 4 IFNONNULL L131 - false in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 3. serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V_java.lang.IllegalArgumentException_EXPLICIT
   * Goal 4. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 131
   * Goal 5. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 132
   * Goal 6. [METHOD] org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V
   * Goal 7. Weak Mutation 8: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:131 - ReplaceComparisonOperator != null -> = null
   * Goal 8. Weak Mutation 9: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:132 - ReplaceConstant - The OutputStream must not be null -> 
   */

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      // Undeclared exception!
      try { 
        SerializationUtils.serialize((Serializable) null, (OutputStream) null);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // The OutputStream must not be null
         //
         verifyException("org.apache.commons.lang3.SerializationUtils", e);
      }
  }

  //Test case number: 8
  /*
   * 25 covered goals:
   * Goal 1. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: root-Branch
   * Goal 2. Branch org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: root-Branch in context: org.apache.commons.lang3.SerializationUtils:serialize(Ljava/io/Serializable;)[B
   * Goal 3. [Output]: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:NonEmptyArray
   * Goal 4. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 162
   * Goal 5. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 163
   * Goal 6. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B: Line 164
   * Goal 7. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 131
   * Goal 8. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 134
   * Goal 9. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 137
   * Goal 10. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 138
   * Goal 11. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 144
   * Goal 12. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 145
   * Goal 13. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 149
   * Goal 14. org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V: Line 151
   * Goal 15. [METHOD] org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B
   * Goal 16. [METHOD] org.apache.commons.lang3.SerializationUtils.ClassLoaderAwareObjectInputStream.<init>(Ljava/io/InputStream;Ljava/lang/ClassLoader;)V
   * Goal 17. [METHODNOEX] org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B
   * Goal 18. [METHODNOEX] org.apache.commons.lang3.SerializationUtils.ClassLoaderAwareObjectInputStream.<init>(Ljava/io/InputStream;Ljava/lang/ClassLoader;)V
   * Goal 19. Weak Mutation 8: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:131 - ReplaceComparisonOperator != null -> = null
   * Goal 20. Weak Mutation 10: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;Ljava/io/OutputStream;)V:144 - ReplaceComparisonOperator = null -> != null
   * Goal 21. Weak Mutation 12: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 0
   * Goal 22. Weak Mutation 13: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 1
   * Goal 23. Weak Mutation 14: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> -1
   * Goal 24. Weak Mutation 15: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 511
   * Goal 25. Weak Mutation 16: org.apache.commons.lang3.SerializationUtils.serialize(Ljava/io/Serializable;)[B:162 - ReplaceConstant - 512 -> 513
   */

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      byte[] byteArray0 = SerializationUtils.serialize((Serializable) (byte)126);
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
      ClassLoader classLoader0 = mock(ClassLoader.class, new ViolatedAssumptionAnswer());
      SerializationUtils.ClassLoaderAwareObjectInputStream serializationUtils_ClassLoaderAwareObjectInputStream0 = new SerializationUtils.ClassLoaderAwareObjectInputStream(byteArrayInputStream0, classLoader0);
  }

  //Test case number: 9
  /*
   * 2 covered goals:
   * Goal 1. [METHOD] org.apache.commons.lang3.SerializationUtils.<init>()V
   * Goal 2. [METHODNOEX] org.apache.commons.lang3.SerializationUtils.<init>()V
   */

  @Test(timeout = 4000)
  public void test9()  throws Throwable  {
      SerializationUtils serializationUtils0 = new SerializationUtils();
  }
}