# import re
# # Define a function to replace occurrences
# def replace_assertions(line):
#     modified_line = line.replace("assertEquals", "TEMP_EQUALS", 1)
#     modified_line = modified_line.replace("assertSame", "TEMP_SAME", 1)

#     modified_line = modified_line.replace("assertNotEquals", "assertEquals", 1)
#     modified_line = modified_line.replace("assertNotSame", "assertSame", 1)

#     modified_line = modified_line.replace("TEMP_EQUALS", "assertNotEquals", 1)
#     modified_line = modified_line.replace("TEMP_SAME", "assertNotSame", 1)

#     transformed_by_replacement = line != modified_line
#     return transformed_by_replacement, modified_line

# def assert_transform(str):
#     transformed = False
#     # by_regular_expression
#     patterns = [
#         (r'assertTrue\((.*)\);', r'assertTrue((\1) != true);'),
#         (r'assertFalse\((.*)\);', r'assertTrue((\1) != false);'),
#         (r'assertNull\((.*)\);', r'assertTrue((\1) != null);'),
#         (r'assertNotNull\((.*)\);', r'assertTrue((\1) == null);'),
#     ]

#     for pattern, replacement in patterns:
#         if re.search(pattern, str):
#             transformed_str = re.sub(pattern, replacement, str)
#             str = transformed_str
#             transformed = True

#     # by replacement
#     split_str = str.split('\n')
#     for idx, line in enumerate(split_str):
#         transformed_by_replacement, split_str[idx] = replace_assertions(line)
#         if transformed_by_replacement == True:
#             transformed = True
#     transformed_str = '\n'.join(split_str)
#     return transformed, transformed_str

# def trycatch_transform(str):
#     transformed = False
#     transformed_str = ''
#     # Regular expression pattern to find the fail statement outside the catch block
#     pattern = r'try {([\s\S]*?)fail\("[^"]+"\);([\s\S]*?)} catch\(([^)]+)\) {([\s\S]*?)}'
#     # Find and replace the fail statement
#     if re.search(pattern, str):
#         transformed_str = re.sub(pattern, r'try {\1} catch(\3) {\n\tfail("Unexpected exception was thorwn!");}', str, count=1)
#         transformed = True
#     return transformed, transformed_str


# def transform(str):
#     isMuated_assert, transformed_assert = assert_transform(str)
#     isMuated_trycatch, transformed_trycatch = trycatch_transform(transformed_assert)
    
#     isMutated= 'No'
#     if isMuated_assert:
#         isMutated = 'assert'
#         return isMutated, transformed_assert
#     elif isMuated_trycatch:
#         isMutated = 'trycatch'
#         return isMutated, transformed_trycatch
#     else:
#         return isMutated, ''




import re
# Define a function to replace occurrences
def replace_assertions(line):
    modified_line = line.replace("assertEquals", "assertNotEquals")
    transformed_by_replacement = line != modified_line
    if transformed_by_replacement:
        return True, 'assertEquals', modified_line

    modified_line = modified_line.replace("assertSame", "assertNotSame")
    transformed_by_replacement = line != modified_line
    if transformed_by_replacement:
        return True, 'assertSame', modified_line
    
    modified_line = modified_line.replace("assertNotEquals", "assertEquals")
    transformed_by_replacement = line != modified_line
    if transformed_by_replacement:
        return True, 'assertNotEquals', modified_line
    
    modified_line = modified_line.replace("assertNotSame", "assertSame")
    transformed_by_replacement = line != modified_line
    if transformed_by_replacement:
        return True, 'assertNotSame', modified_line

    return False, '', modified_line

def assert_transform(str):
    isTransformed = False
    assertion_type = ''
    # by_regular_expression
    patterns = [
        (r'assertTrue\((.*)\);', r'assertTrue((\1) != true);'),
        (r'assertFalse\((.*)\);', r'assertTrue((\1) != false);'),
        (r'assertNull\((.*)\);', r'assertTrue((\1) != null);'),
        (r'assertNotNull\((.*)\);', r'assertTrue((\1) == null);'),
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
            isTransformed = True
            return isTransformed, assertion_type, transformed_str

    # by replacement
    split_str = str.split('\n')
    for idx, line in enumerate(split_str):
        transformed_by_replacement, assertion_type, split_str[idx] = replace_assertions(line)
        if transformed_by_replacement == True:
            isTransformed = True
            break
    transformed_str = '\n'.join(split_str)
    return isTransformed, assertion_type, transformed_str

def trycatch_transform(str):
    isTransformed = False
    transformed_str = ''
    # Regular expression pattern to find the fail statement outside the catch block
    pattern = r'try {([\s\S]*?)fail\("[^"]+"\);([\s\S]*?)} catch\(([^)]+)\) {([\s\S]*?)}'
    # Find and replace the fail statement
    if re.search(pattern, str):
        transformed_str = re.sub(pattern, r'try {\1} catch(\3) {\n\tfail("Unexpected exception was thorwn!");}', str, count=1)
        isTransformed = True
    return isTransformed,'try_catch', transformed_str


def transform(str):
    isTransformed = False
    
    isTransformed, assertion_type, transformed_str_assert = assert_transform(str)
    if isTransformed:
        return assertion_type, transformed_str_assert
    
    isTransformed, try_catrch_type, transformed_str_trycatch = trycatch_transform(str)
    if isTransformed:
        return try_catrch_type, transformed_str_trycatch
    
    return 'No', ''
