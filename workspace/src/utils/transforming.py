import re

def assert_transform_prompt(str):
    transformed_str = ''
    mutated = False
    
    if re.search("assertTrue", str):
        transformed_str = re.sub(r'assertTrue\(([^)]+)\);', r'assertTrue(\1 != true);', str)
        mutated = True
    elif re.search("assertFalse", str):
        transformed_str = re.sub(r'assertFalse\(([^)]+)\);', r'assertTrue(\1 != false);', str)
        mutated = True
    elif re.search("assertNull", str):
        transformed_str = re.sub(r'assertNull\(([^)]+)\);', r'assertTrue(\1 != null );', str)
        mutated = True
    elif re.search("assertNotNull", str):
        transformed_str = re.sub(r'assertNotNull\(([^)]+)\);', r'assertTrue(\1 != null );', str)
        mutated = True
    elif re.search("assertEquals", str):
        transformed_str = re.sub(r'assertEquals', r'assertNotEquals', str)
        mutated = True
    elif re.search("assertNotEquals", str):
        transformed_str = re.sub(r'assertNotEquals', r'assertEquals', str)
        mutated = True
    elif re.search("assertNotSame", str):
        transformed_str = re.sub(r'assertNotSame', r'assertSame', str)
        mutated = True
    elif re.search("assertSame", str):
        transformed_str = re.sub(r'assertSame', r'assertNotSame', str)
        mutated = True
        
    return mutated, transformed_str

def trycatch_transform_prompt(str):
    mutated = False
    transformed_str = ''
    # Regular expression pattern to find the fail statement outside the catch block
    pattern = r'try {([\s\S]*?)fail\("[^"]+"\);([\s\S]*?)} catch\(([^)]+)\) {([\s\S]*?)}'
    # Find and replace the fail statement
    if re.search(pattern, str):
        transformed_str = re.sub(pattern, r'try {\1} catch(\3) {\n\tfail("Unexpected exception was thorwn!");}', str, count=1)
        mutated = True
    return mutated, transformed_str


def transform(str):
    isMuated_assert, transformed_assert = assert_transform_prompt(str)
    isMuated_trycatch, transformed_trycatch = trycatch_transform_prompt(str)
    
    isMutated= 'No'
    if isMuated_assert and isMuated_trycatch:
        return isMutated, ''
    elif isMuated_assert:
        isMutated = 'assert'
        return isMutated, transformed_assert
    elif isMuated_trycatch:
        isMutated = 'trycatch'
        return isMutated, transformed_trycatch
    else:
        return isMutated, ''