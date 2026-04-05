# 🧪 Functional and Security Testing Project

This repository contains two main parts developed for the **Functional and Security Testing Techniques** course (Spring 2025).  
It demonstrates both **end-to-end functional testing** of web applications and **security testing** of common web vulnerabilities.

---

## ✅ Functional Testing Part
This assignment is to design and implement end-to-end test suites for four different web applications, measuring the effort required in **manual development** vs **AI-assisted development**.

### Applications Tested
- **ExpressCart**  
- **Joomla**  
- **Kanboard**  
- **MediaWiki**

### Approach
- **Manual Development**
  - Disabled GitHub Copilot.  
  - Read each Gherkin scenario from the BEWT Specifications repository.  
  - Wrote Java test methods manually in VS Code using Selenium WebDriver and JUnit 5.  

- **AI-Assisted Development**
  - Enabled Copilot’s online editor chat.  
  - For each Gherkin scenario, prompted Copilot with the full text.  
  - Refined or corrected the generated test scripts until all passed successfully.  

### Tools & Frameworks
- **Java**  
- **Selenium WebDriver**  
- **JUnit 5**  
- **VS Code with Copilot integration**

---

## 🔐 Security Part
This project simulates and tests common **web vulnerabilities** within a Dockerized environment, along with a custom proxy service to detect and filter malicious requests.

### Vulnerabilities Implemented
- **SQL Injection (SQLi)**  
- **Cross-Site Scripting (XSS)**  
- **Command Injection (CMDi)**  
- **Denial of Service (DoS)**  

### System Architecture
- **Web Server** → Vulnerable service (`http://localhost:8000/`)  
- **Proxy** → Filters requests using regex-based rules (`http://localhost:5000/`)  
- **Attacker** → Sends malicious requests for testing  

### Features
- **Environment Variables** (`docker-compose.yml`) control which attacks are detected:  
  - `SQLI_ENABLED`, `XSS_ENABLED`, `CMDI_ENABLED`, `DOS_ENABLED`  
- **Dynamic Configuration** → Attacks can be toggled on/off at runtime.  
- **Extensibility** → New modules can be added easily for other vulnerabilities.
- **Dynamic Attack Selection** → You can dynamically choose which attack script to execute using the `ATTACK_TYPE` environment variable:

- `1` → Command Injection (`attack_command_injection.py`)  
- `2` → SQL Injection (`attack_sql_injection.py`)  
- `3` → XSS (`attack_xss.py`)  
- `4` → DoS (`attack_dos.py`)  

### Running the Project
```bash
docker-compose up --build
