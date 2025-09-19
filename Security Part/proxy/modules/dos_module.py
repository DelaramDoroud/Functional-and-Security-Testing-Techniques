import time

TIME_WINDOW = 10     
MAX_REQUESTS = 20    

request_counter = {}

def is_dos(client_ip, endpoint):
    now = time.time()
    
    if client_ip not in request_counter:
        request_counter[client_ip] = {}
    
    if endpoint not in request_counter[client_ip]:
        request_counter[client_ip][endpoint] = []

    #filttering old requests
    request_log = request_counter[client_ip][endpoint]
    request_log = [t for t in request_log if now - t < TIME_WINDOW]

    #add new request time
    request_log.append(now)
    request_counter[client_ip][endpoint] = request_log

    #check the number of requests in specific interval
    if len(request_log) > MAX_REQUESTS:
        return True
    return False
