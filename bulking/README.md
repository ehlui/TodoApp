# Description

*bulk.py* is a script for load and create many tasks without doing it by hand.

## Usage

    ```
    $ python bulk.py  <Integer>|<csv_file>
    ```
- Without arguments:
    - It won't work
- With arguments
    - `.csv` files will be read (Root directory for now).
    - It can have any file name but with its extension!
        - It will read all the tasks and its descriptions so that make a POST request to the server.
    - Integers 
        - This will load random tasks to the server  
