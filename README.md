# csc326-202-HW3P2-02

##Justification for JUnit Tests For Modified Java Files

Below are all the Java files that we created/modified, excluding files intended for testing, interface or enum. The files with 80%+ coverage are bolded.

* iTrust/src/main/edu/ncsu/csc/itrust/controller/officeVisit/OfficeVisitController.java
* **iTrust/src/main/edu/ncsu/csc/itrust/controller/officeVisit/OfficeVisitForm.java**
* **iTrust/src/main/edu/ncsu/csc/itrust/model/officeVisit/OfficeVisit.java**
* iTrust/src/main/edu/ncsu/csc/itrust/model/officeVisit/OfficeVisitMySQL.java
* **iTrust/src/main/edu/ncsu/csc/itrust/model/officeVisit/OfficeVisitSQLLoader.java**
* **iTrust/src/main/edu/ncsu/csc/itrust/model/officeVisit/OfficeVisitValidator.java**

We achieved less than 80% statment coverage in the following classes:

* iTrust/src/main/edu/ncsu/csc/itrust/model/officeVisit/OfficeVisitMySQL.java

Justification: We only added a single method `getPatientDOB()` in this class, which is thoroughly tested in `OfficeVisitMySQLTest.java`.

* iTrust/src/main/edu/ncsu/csc/itrust/controller/officeVisit/OfficeVisitController.java

Justification: we added an utility method `getPatientDOB()`, which simply calls the `getPatientDOB()` from `OfficeVisitMySQL.java`
