import re

COMMAND_INJECTION_PATTERNS = [
    r"(?i);",               # Command separator
    r"(?i)\|",              # Pipe
    r"(?i)&",               # Logical operator
    r"(?i)>",               # Output redirection
    r"(?i)<",               # Input redirection
    r"(?i)\$",              # Shell variable
    r"(?i)`",               # Command substitution
    r"(?i)\/bin\/bash",     # Bash path (common for attacks)
    r"(?i)\/bin\/sh",       # Shell path (common for attacks)
    r"(?i)ls\s+-la",        # Detect `ls -la`
    r"(?i)cat\s+\S+",       # Detect `cat` command with arguments
    r"(?i)rm\s+-rf",        # Detect `rm -rf`
    r"(?i)wget\s+\S+",      # Detect `wget` command
    r"(?i)curl\s+\S+"       # Detect `curl` command
]

def is_command_injection(query):
    for pattern in COMMAND_INJECTION_PATTERNS:
        if re.search(pattern, query):
            return True
    return False
