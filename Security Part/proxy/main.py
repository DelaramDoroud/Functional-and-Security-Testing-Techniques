from flask import Flask, request, jsonify
import requests
import os
import importlib
import os

SQLI_ENABLED = os.environ.get('SQLI_ENABLED', 'false').lower() == 'true'
XSS_ENABLED = os.environ.get('XSS_ENABLED', 'false').lower() == 'true'
CMDI_ENABLED = os.environ.get('CMDI_ENABLED', 'false').lower() == 'true'
DOS_ENABLED = os.environ.get('DOS_ENABLED', 'false').lower() == 'true'

app = Flask(__name__)

WEB_SERVER_URL = os.environ.get('WEB_SERVER_URL', 'http://web/index.php')

def load_module(module_name):
    try:
        return importlib.import_module(f"modules.{module_name}")
    except ImportError:
        return None

sqli_module = load_module('sqli_module')
xss_module = load_module('xss_module')
command_injection_module = load_module('command_injection_module')
dos_module = load_module('dos_module')

@app.route('/', methods=['GET'])
def proxy_get():
    name = request.args.get('name', '')
    cmd = request.args.get('cmd', '')
    client_ip = request.remote_addr

    if sqli_module and SQLI_ENABLED and sqli_module.is_sqli(name):
        return jsonify({"error": "SQL Injection detected!"}), 403
    if command_injection_module and CMDI_ENABLED and command_injection_module.is_command_injection(cmd):
        return jsonify({"error": "Command Injection detected!"}), 403
    if dos_module and DOS_ENABLED and dos_module.is_dos(client_ip, WEB_SERVER_URL):
        return jsonify({"error": "DoS attack detected!"}), 429

    try:
        response = requests.get(WEB_SERVER_URL, params=request.args)
        return response.text, response.status_code
    except requests.exceptions.RequestException:
        return jsonify({"error": "Webserver unreachable"}), 502

@app.route('/xss', methods=['GET'])
def proxy_xss():
    q = request.args.get('q', '')
    client_ip = request.remote_addr
    if xss_module and XSS_ENABLED and xss_module.is_xss(q):
        return jsonify({"error": "XSS attack detected!"}), 403
    # if is_dos(client_ip, "http://web/xss.php"):
    #     return jsonify({"error": "DoS attack detected!"}), 429

    try:
        response = requests.get("http://web/xss.php", params={'q': q})
        return response.text, response.status_code
    except requests.exceptions.RequestException:
        return jsonify({"error": "Webserver unreachable"}), 502

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
