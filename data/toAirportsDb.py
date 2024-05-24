import pandas as pd
import mysql.connector

# Function to insert data in batches
def insert_data_in_batches(data, cursor, batch_size=100):
    num_rows = len(data)
    for i in range(0, num_rows, batch_size):
        batch_data = data.iloc[i:min(i+batch_size, num_rows)]
        values_list = []
        for _, row in batch_data.iterrows():
            row = row.where(pd.notnull(row), None)  # Replace NaN with None
            values_tuple = tuple(row[field] for field in fields)
            values_list.append(values_tuple)
        cursor.executemany(f'''
            INSERT INTO airports ({', '.join(fields)})
            VALUES ({', '.join(['%s' for _ in fields])})
            ''', values_list)

# Reading the data from the CSV file
data = pd.read_csv('./airports.csv', low_memory=False)

# List of fields to be included in the SQL insert statements
fields = ['id', 'ident', 'type', 'name', 'latitude_deg', 'longitude_deg', 'continent', 'iso_country', 'iso_region', 'municipality']

# List of types to include in the SQL insert statements
airport_types = ['small_airport', 'medium_airport', 'large_airport']

# Filter the data to include only the specified airport types
data_filtered = data[data['type'].isin(airport_types)]

# Connect to the MySQL database
conn = mysql.connector.connect(
    host='localhost',      # e.g., 'localhost'
    user='root',           # e.g., 'root'
    password='',
    database='jetblue'
)
cursor = conn.cursor()

# Create the table if it doesn't exist
create_table_query = '''
CREATE TABLE IF NOT EXISTS airports (
    id INT PRIMARY KEY,
    ident VARCHAR(10),
    type VARCHAR(50),
    name VARCHAR(100),
    latitude_deg DOUBLE,
    longitude_deg DOUBLE,
    continent VARCHAR(10),
    iso_country VARCHAR(10),
    iso_region VARCHAR(10),
    municipality VARCHAR(100)
)
'''
cursor.execute(create_table_query)

# Insert data in batches
insert_data_in_batches(data_filtered, cursor, batch_size=100)

# Commit the transaction
conn.commit()

# Close the connection
conn.close()
