import re

def convert_assert_equals_to_assert_true(input_str):
    # Define regular expressions for the patterns to match
    pattern1 = r'assertEquals\("([^"]+)", (\w+)\);'
    pattern2 = r'assertEquals\(([^,]+), (\w+)\);'
    pattern3 = r'assertEquals\(([^,]+), \(([^,]+)\), ([^)]+)\);'

    # Define replacement patterns
    replacement1 = r'assertTrue(\2.equals("\1"));'
    replacement2 = r'assertTrue(\1 == \2);'
    replacement3 = r'assertTrue(Math.abs(\1 - (\2)) <= \3);'

    # Apply the replacements
    output_str = re.sub(pattern1, replacement1, input_str)
    output_str = re.sub(pattern2, replacement2, output_str)
    output_str = re.sub(pattern3, replacement3, output_str)

    return output_str

# Example usage:
input_code = """
assertEquals("\",\"", string0);
assertEquals(5, a);
assertEquals(1.5, (float b), 0.001);
"""

output_code = convert_assert_equals_to_assert_true(input_code)
print(output_code)
