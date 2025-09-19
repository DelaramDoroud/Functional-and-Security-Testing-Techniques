import os
import subprocess

def run_attack(choice):
    if choice == "1":
        subprocess.run(["python3", "attack_command_injection.py"])
    elif choice == "2":
        subprocess.run(["python3", "attack_sql_injection.py"])
    elif choice == "3":
        subprocess.run(["python3", "attack_xss.py"])
    elif choice == "4":
        subprocess.run(["python3", "attack_dos.py"])
    else:
        print("Invalid ATTACK_TYPE. Use 1 to 4.")

if __name__ == "__main__":
    attack_type = os.environ.get("ATTACK_TYPE")
    if not attack_type:
        print("ATTACK_TYPE environment variable not set.")
    else: 
        print(f"Running attack type: {attack_type}")
        run_attack(attack_type)
