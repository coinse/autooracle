import re
# Define a function to replace occurrences
def assert_transform(str):
    isTransformed = False
    assertion_type = ''
    # by_regular_expression
    patterns = [
        (r'assertTrue\((.*)\);', r'assertFalse((\1));'),
        (r'assertFalse\((.*)\);', r'assertTrue((\1));'),
        (r'assertNull\((.*)\);', r'assertNotNull((\1));'),
        (r'assertNotNull\((.*)\);', r'assertNull((\1);'),
        (r'assertEquals\((.*)\);', r'assertNotEquals((\1);'),
        (r'assertNotEquals\((.*)\);', r'assertEquals((\1);'),
        (r'assertSame\((.*)\);', r'assertNotSame((\1);'),
        (r'assertNotSame\((.*)\);', r'assertSame((\1);'),
    ]

    for idx, pattern in enumerate(patterns):
        if re.search(pattern[0], str):
            transformed_str = re.sub(pattern[0], pattern[1], str)
            str = transformed_str
            if idx == 0:
                assertion_type = 'assertTrue'
            if idx == 1:
                assertion_type = 'assertFalse'
            if idx == 2:
                assertion_type = 'assertNull'
            if idx == 3:
                assertion_type = 'assertNotNull'
            if idx == 4:
                assertion_type = 'assertEquals'
            if idx == 5:
                assertion_type = 'assertNotEquals'
            if idx == 6:
                assertion_type = 'assertSame'
            if idx == 7:
                assertion_type = 'assertNotSame'
            isTransformed = True
            return isTransformed, assertion_type, transformed_str
        
    return False,"",""

def trycatch_transform(str):
    isTransformed = False
    transformed_str = ''
    # Regular expression pattern to find the fail statement outside the catch block
    pattern = r'try {([\s\S]*?)fail\("[^"]+"\);([\s\S]*?)} catch\(([^)]+)\) {([\s\S]*?)}'
    # pattern = r'// Undeclared exception!([\s\S]*?)try {([\s\S]*?)fail\(([\s\S]*?)\);([\s\S]*?)} catch\(([\s\S]*?)\) {([\s\S]*?)}'
    # Find and replace the fail statement
    match = re.search(pattern, str)
    if match:
        transformed_str = re.sub(pattern, r'try {\1} catch(\3) {\n\tfail("Unexpected exception was thorwn!");}', str, count=1)
        # operation_method = match.group(2).strip()
        # unexpected_exception = match.group(3).strip().replace('\"',"").split(':')[1].strip()
        # transformed_str = re.sub(pattern, f'{match.group(1)}//{operation_method} should not throw the {unexpected_exception}.{match.group(1)}{operation_method}', str, count=1)
        isTransformed = True
    return isTransformed,'try_catch', transformed_str

def transform(str):
    isTransformed = False
    
    isTransformed, assertion_type, transformed_str_assert = assert_transform(str)
    if isTransformed:
        return assertion_type, transformed_str_assert
    
    # isTransformed, try_catrch_type, transformed_str_trycatch = trycatch_transform(str)
    # if isTransformed:
    #     return try_catrch_type, transformed_str_trycatch
    
    return 'No', ''
