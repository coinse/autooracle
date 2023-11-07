import re

prompt_str ="""
@Test(timeout = 4000)
  public void test00()  throws Throwable  {
      try { 
        NumberUtils.createNumber("#Array cannot be empty.");
        fail("Expecting exception: NumberFormatException");
      
      } catch(NumberFormatException e) {
         //
         // For input string: \"rray ca\"
         //
         verifyException("java.lang.NumberFormatException", e);
      }
  }
"""

# Regular expression pattern to find the fail statement outside the catch block
pattern = r'try {([\s\S]*?)fail\("[^"]+"\);([\s\S]*?)} catch\(([^)]+)\) {([\s\S]*?)}'

# Find and replace the fail statement
prompt_str = re.sub(pattern, r'try {\1} catch(\3) {\2\tfail("Expecting No exception");}', prompt_str)

# Print the modified text
print(prompt_str)