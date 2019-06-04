# TempleApp

This app is a cost-efficient way of maintaining records of poojas, donations made by people and also all the financial records in temples. Using this app, people in the temple counter will be able to enter all the details regarding the poojas they want or some donations they want to do for the temple. This app is connected to a printer so that it will print the details of pooja registrations of users.
The app has a login interface and only some people like General secretary, president and treasurer of the temple can log in and be able to access the complete database.
## Getting Started

These instructions will get you a copy of the project up and be running on your local machine for development and testing purposes.
### Prerequisites
[Android Studio](https://developer.android.com/studio), with a recent version of the Android SDK.

### Setting up your development environment

- Download and install Git.

- Fork the TempleApp project from``` https://github.com/amfoss/TempleApp```

- Clone your fork of the project locally. At the command line:

```git clone https://github.com/YOUR-GITHUB-USERNAME/TempleApp```

If you prefer not to use the command line, you can use Android Studio to create a new project from version control using ```https://github.com/YOUR-GITHUB-USERNAME/TempleApp```

Open the project in the folder of your clone from Android Studio and build the project. If there are any missing dependencies, install them first by clicking on the links provided by Android studio. Once the project is built successfully, run the project by clicking on the green arrow at the top of the screen.

## Deployment

Google Sheets provides us with an already familiar interface to create, edit, and view all our data in columns. We can search, order, and even do bulk operations. Also, Google provides an API to use these sheets in a programmatic way, which we are going to use for this application. Then by using OKHTTP, values of each columns are parsed and using list view all the registered things are shown. In this application, there are two options- one to *register for a new pooja* and another option is for *making donations* to the temple and the user can select either of the option. If registering for new pooja option is selected then the values added for the registration will be added to the google sheet and if details of certain user have to be read , read option can be selected and id of the user should be given so that the details of the user will be searched from the sheet and will be shown. Also, any registration can be deleted by giving the ID of the user whose registration is to be deleted. If the user has to make some donations to the temple, there is an option to enter the amount of money they wish to donate. After the user makes all the selections, it will be printed on a form of a receipt and will be handed over to them. The app has a login interface and only certain people(temple authorities) can log in to it and can view details of all the registrations or donations.

**Workflow of the app:**
```
.......
│   
├── Activities
│   ├── DeleteData.java
│   ├── InsertData.java
│   ├── MainActivity.java
│   ├── ReadAllData.java
│   ├── ReadSingleData.java
│   └── UpdateData.java
├── Adapter
│   ├── MyArrayAdapter.java
│   ├── MyDataModel.java
└── Json_API
    ├── Controller.java
    └── InternetConnection.java 
 ```
## Dependencies
- [appcompat-v7:28.0.0](https://mvnrepository.com/artifact/com.android.support/appcompat-v7/28.0.0-alpha1)
- [junit:4.12](https://mvnrepository.com/artifact/junit/junit/4.12)
- [okhttp](https://square.github.io/okhttp/)
- [cardview-v7:28.0.0](https://mvnrepository.com/artifact/com.android.support/cardview-v7/28.0.0-alpha1)

