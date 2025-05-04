## Alert Generation System

The class that tracks and outputs all alerts is AlertManager. It is composed of four alarm generators. If any of them generates an alarm, it communicates it to the user.
Each alarm generator corresponds to the specific data that can be assigned to each patient. Each of the generators has a method that evaluates if data is in the correct treshold etc.
They all inherit from the abstract AlertGenerator class which gives a general structure of the alert generator.
It has a method that outputs the corresponding Alert object. Furthermore, it has its own DataStorage object to access all the necessary information for correct assessment.
The DataStorage object has a hashmap with all the Patients in some system, which is later accessed by AlertGenerator. It can retrieve all the Patients needed, in this case for the evaluation if the alert is necessary.
Finally, each patient is represented by the Patient object, which is composed of the PatientRecord object. This enables to store and access data necessary for this process.

To summarize, the patient object has its own record containing all the important information, Patients are stored in DataStorage, which is accessed by a particular AlertGenerator and evaluated, if necessary the Alarm is triggered and output is shown by AlertManager. 
 