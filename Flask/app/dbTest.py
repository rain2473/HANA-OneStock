# 모듈 importing
from pykrx import stock
import pandas as pd
import oracledb
import sys


oracledb.version = "8.3.0"
sys.modules["cx_Oracle"] = oracledb

conn=oracledb.connect(
     user="DA23",
     password='Data23',
     dsn="dinkdb_medium",
     config_dir="/Users/pkd/Oracle/instantclient_19_8/network/admin",
     wallet_location="/Users/pkd/Oracle/instantclient_19_8/network/admin",
     wallet_password="da23"
     )

curs = conn.cursor()

# Execute the SQL query and read the results into a Pandas DataFrame
sql = 'SELECT * FROM emp'
result = curs.execute(sql)
df = pd.DataFrame(curs.fetchall(), columns=result.keys())

print(df)

# Close the cursor and connection
curs.close()
conn.close()