"""
Script for bulking tasks to the api

usage:
    Without passing argumments:
    - It won't work
    
    Passing arguments:
    - execute the script with an integer : Adds X tasks 
        python3 bulk.py 20

    - execute the script with a file
        python3 bulk.py tasks.csv

csv structure:
    name;description

"""
import requests, json, sys, os

BASE_URI ='http://localhost'
PORT = 8118
SERVICE_ENDPOINT ='todoApp/TaskService/'
URL_SERVICE_ENDPOINT =f'{BASE_URI}:{PORT}/{SERVICE_ENDPOINT}'


def read_csv(fileName, delimiter = ';'):
    with open(fileName,mode='r') as f:
        for line in f:
            if not line.isspace():
                clean_pair_list = line.replace('\n','').split(delimiter)
                yield {
                    "name" :  clean_pair_list[0],
                    "description" : clean_pair_list[1]
                    }


def parse_bulk_arg(bulk_elements = 10,file_extension = '.csv'):
    if len(sys.argv) > 1:
        input_arg = sys.argv[1]
        if file_extension in input_arg:
            is_file = os.path.isfile(input_arg)
            if is_file:	
                csv_generator = read_csv(input_arg)
                return None, csv_generator

        if input_arg.isnumeric():
            try:
                bulk_input = int(input_arg)
                bulk_elements =  bulk_input if bulk_input > 0 else None
            except ValueError as ve:
                print(f'Error while casting to integer: \nmsg=>{ve}')
            return bulk_elements,  None
    return None, None

def post(payload = None):
    response = requests.post (
            URL_SERVICE_ENDPOINT, data = json.dumps(payload)
    )
    log = payload if response.status_code == 200 else 'error'
    print(f'STATUS_CODE={response.status_code} :: {log}')
      

def bulk_by_range(num):
    for e in range(num):
        payload = { "name" : f"{e}", "description": f"example-{e}" }
        post(payload)

def bulk_by_csv(csv_generator):
    for line in csv_generator:
        post(payload=line)

def bulk(elements_num = None, csv_generator=None):
    if elements_num is not None:
        bulk_by_range(elements_num)
    if csv_generator is not None:
        bulk_by_csv(csv_generator)

def main():
    bulk_elements, csv_generator = parse_bulk_arg()

    if not bulk_elements and not csv_generator:
        print("We cannot bulk ...")
        return

    bulk(bulk_elements, csv_generator)

if __name__ =='__main__':
    main()