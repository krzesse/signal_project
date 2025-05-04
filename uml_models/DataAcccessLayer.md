## Data Access Layer

The process of retrieving and implementing changes to data begins with listeners. The Listener interface defines the listen method, which returns the data in raw format, string.
This string is then parsed by the DataParse class, it simply uses the output of the listeners method and outputs the PatientRecord object. It can be associated with a Patient object.
The matching procedure happens inside DataSourceAdapter, it gets the patient ID from PatientRecord and finds an adequate Patient inside the DataStorage hashmap. It is then necessary to compose a DataSourceAdapter and DataStorage instance. The method findPatientBasedOnId implements this logic. If a match is successful storeRecord method accesses the Patient object to use the addRecord method which results in storing retrieved information with association to the Patient.

To summarize, listeners extract raw string, which is parsed into PatientRecord by DataParse. DataSourceAdapter uses this PatientRecord and DataStorage to find the matching Patient object and add a record to it. 