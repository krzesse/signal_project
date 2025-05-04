## Patient Identification System

To identify and potentially match patients there need to be existing classes for both instances. Therefore there are Patient and HospitalPatient classes.
They each are a part of a storage class that maps each object with its ID. Those classes are DataStorage and HospitalDataStorage, both able to retrieve information about patient objects.
The identification and matching procedure happens inside the PatientIdentifier class. It uses both storages to find patterns, the logic behind is based on the assumption that patients should have the same IDs in both classes. Methods enable one to find matching patients or ones without a match, then return resulting lists of matching procedures.
The error handling and displaying results are handled by IdentityManager. It has access to PatientIdentifier and uses information gained from its method to validate if results are reliable. For example, it checks if there is no patient in both matched and unmatched categories. These types of mistakes could happen for instance by faulty patient information. Finally, it displays either the results or the error that occurred.

To summarize, the identification process starts by gathering all patients in their respective storages, they are then compared using PatientIdentifier and validated by IdentityManager, to finally be displayed or to trigger an error.

