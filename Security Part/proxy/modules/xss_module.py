import re

XSS_PATTERNS = [
    r"(?i)<script.*?>.*?</script>",
    r"(?i)on\w+\s*=",
    r"(?i)alert\s*\(",
    r"(?i)document\.cookie",
    r"(?i)<img\s+.*?onerror\s*=.*?>",
    r"(?i)<.*?javascript:.*?>",
]

def is_xss(query):
    for pattern in XSS_PATTERNS:
        if re.search(pattern, query):
            return True
    return False
