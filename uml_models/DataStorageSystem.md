## Data Storage System

The whole data storage and retrieval system is based on the DataStorage class. It is an object that stores in a hashmap all the Patient Objects. Where each Patient object is composed of PatientRecord, containing all the potentially wanted information to retrieve.
Then there is the PatientData class which is composed of DataStorage. It enables returning specific data and deleting old data.
To use PatientData methods with more flexibility it should be accessed by DataRetriever class. It lets set up variables to specify data search/deletion and access PatientData.
To ensure different access scopes each DataReciever is assigned to a User class. Based on the role of a user DataRetriever is able to judge if access can be granted.

To summarize, each User object has its own DataRetriever, which specifies to the PatientData what data to retrieve through DataStorage, DataStorage accesses all the data directly through Patient objects.