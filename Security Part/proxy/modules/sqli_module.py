import re

SQLI_PATTERNS = [
    r"(?i)(\bor\b|\band\b)\s+['\"]?\w+['\"]?\s*=\s*['\"]?\w+['\"]?",
    r"(?i)union\s+select",
    r"(?i)--",
    r"(?i)sleep\(",
    r"(?i)drop\s+table",
]

def is_sqli(query):
    for pattern in SQLI_PATTERNS:
        if re.search(pattern, query):
            return True
    return False
