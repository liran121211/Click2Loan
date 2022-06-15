# importing required packages
from numpy import newaxis, array, savetxt, reshape
from sklearn.preprocessing import StandardScaler
import csv
import os

file = open(os.path.abspath(os.getcwd())+"\\src\\main\\java\\core\\bin\\metrics\\loan_encoded.csv")
csv_reader = csv.reader(file)
arr = []
for row in csv_reader:
    arr.append(row)

scale = StandardScaler()
np_arr = array(arr[0])  # here should be your X in np.array format
transformed_arr = scale.fit_transform(np_arr[:, newaxis])

savetxt(os.path.abspath(os.getcwd())+"\\src\main\\java\\core\\bin\\metrics\\loan_normalized.csv", transformed_arr.reshape(1,-1), delimiter=",")
file.close()
