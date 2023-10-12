import re

input_texts = [
    "org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z",
    "org.apache.commons.lang3.StringUtils.isEmpty(Ljava/lang/String;)Z",
    "com.example.MyClass.myMethod(Ljava/lang/String;I)V",
    # Add more input texts with different patterns as needed
]

# Define the regular expression pattern to capture the class name
pattern = r'^([^(\s]+)'

for input_text in input_texts:
    match = re.match(pattern, input_text)

    if match:
        class_name = match.group(1)

        print("Class Name:", class_name)
    else:
        print("No match found for input:", input_text)
