# TempleApp
[![Watchers](https://img.shields.io/github/watchers/amfoss/TempleApp.svg?style=social&label=Watchers&maxAge=2592000)](https://GitHub.com/amfoss/TempleApp/watchers/)
[![Star Gazers](https://img.shields.io/github/stars/amfoss/TempleApp.svg?style=social&label=Stars&maxAge=2592000)](https://GitHub.com/amfoss/TempleApp/stargazers/)
[![Forks](https://img.shields.io/github/forks/amfoss/TempleApp.svg?style=social&label=Forks&maxAge=2592000)](https://GitHub.com/amfoss/TempleApp/network/members/)


[![License: MIT](https://img.shields.io/badge/License-MIT-1f425f.svg)](https://opensource.org/licenses/MIT)
[![Gitter](https://badges.gitter.im/amfoss/TempleApp.svg)](https://gitter.im/amfoss/TempleApp)
[![Code of Conduct](https://img.shields.io/badge/%E2%9D%A4-code%20of%20conduct-e04545.svg?style=flat)](https://github.com/amfoss/TempleApp/blob/master/CODE_OF_CONDUCT.md)
[![Travis CI](https://travis-ci.com/amfoss/TempleApp.svg?branch=master)](https://travis-ci.com/amfoss/TempleApp)
[![Open Issues](https://img.shields.io/github/issues/amfoss/TempleApp.svg)](https://GitHub.com/amfoss/TempleApp/issues/)
[![PRs](https://img.shields.io/github/issues-pr/amfoss/TempleApp.svg)](https://GitHub.com/amfoss/TempleApp/pulls/)
[![Contributors](https://img.shields.io/github/contributors/amfoss/TempleApp.svg)](https://GitHub.com/amfoss/TempleApp/graphs/contributors/)

This app is a cost-efficient way of maintaining records of poojas, donations made by people and also all the financial records in temples. Using this app, people in the temple counter will be able to enter all the details regarding the poojas they want or some donations they want to do for the temple. This app is connected to a printer so that it will print the details of pooja registrations of users.

The app has a login interface and only some people like General secretary, president and treasurer of the temple can log in and be able to access the complete database.

## Getting Started

These instructions will get you a copy of the project up and be running on your local machine for development and testing purposes.

### Prerequisites

[Android Studio](https://developer.android.com/studio), with a recent version of the Android SDK.

### Setting up your development environment

- Download and install Git.

- Fork the [TempleApp project](https://github.com/amfoss/TempleApp)

- Clone your fork of the project locally. At the command line:
    ```
    $ git clone https://github.com/YOUR-GITHUB-USERNAME/TempleApp
    ```

If you prefer not to use the command line, you can use Android Studio to create a new project from version control using 
```
https://github.com/YOUR-GITHUB-USERNAME/TempleApp
```

Open the project in the folder of your clone from Android Studio and build the project. If there are any missing dependencies, install them first by clicking on the links provided by Android studio. Once the project is built successfully, run the project by clicking on the green arrow at the top of the screen.

## How it Works?

Google Sheets provides us with an already familiar interface to create, edit, and view all our data in columns. We can search, order, and even do bulk operations. Also, Google provides an API to use these sheets in a programmatic way, which we are going to use for this application. 

Then by using OKHTTP, values of each columns are parsed and using list view all the registered things are shown. In this application, there are two options- one to *register for a new pooja* and another option is for *making donations* to the temple and the user can select either of the option. 
If registering for new pooja option is selected then the values added for the registration will be added to the google sheet and if details of certain user have to be read, read option can be selected and id of the user should be given so that the details of the user will be searched from the sheet and will be shown. 

Also, any registration can be deleted by giving the ID of the user whose registration is to be deleted. If the user has to make some donations to the temple, there is an option to enter the amount of money they wish to donate. After the user makes all the selections, it will be printed on a form of a receipt and will be handed over to them. The app has a login interface and only certain people(temple authorities) can log in to it and can view details of all the registrations or donations.

#### Workflow of the app

```
.......
│   
├── activities
│   ├── DeleteData.java
│   ├── InsertData.java
│   ├── MainActivity.java
│   ├── ReadAllData.java
│   ├── ReadSingleData.java
│   └── UpdateData.java
├── adapter
│   ├── MyArrayAdapter.java
│   ├── MyDataModel.java
└── json_api
    ├── Controller.java
    └── InternetConnection.java 
 ```

## Screenshots

<table>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/43814992/59095046-2d6e8300-8935-11e9-8cdf-5646353246ad.png" height = "480" width="270"></td>
    <td><img src="https://user-images.githubusercontent.com/43814992/59095110-50993280-8935-11e9-8e35-1faa939cc111.png" height = "480" width="270"></td>
    <td><img src="https://user-images.githubusercontent.com/43814992/59095377-ee8cfd00-8935-11e9-9b67-a85faa3a7460.png" height = "480" width="270"></td>
  </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/43814992/59095468-357af280-8936-11e9-8449-9276066f8ea8.png" height = "480" width="270"></td>
    <td><img src="https://user-images.githubusercontent.com/43814992/59155368-79d8d080-8aa5-11e9-9b3b-9fa402d86aa4.png" height = "480" width="270"></td>
    <td><img src="https://user-images.githubusercontent.com/43814992/59095744-db2e6180-8936-11e9-8d6f-80ab44bbc3f4.png" height = "480" width="270"></td>
  </tr>
</table>

## Dependencies
- [appcompat-v7:28.0.0](https://mvnrepository.com/artifact/com.android.support/appcompat-v7/28.0.0-alpha1)
- [junit:4.12](https://mvnrepository.com/artifact/junit/junit/4.12)
- [okhttp](https://square.github.io/okhttp/)
- [cardview-v7:28.0.0](https://mvnrepository.com/artifact/com.android.support/cardview-v7/28.0.0-alpha1)

## License
This project is licensed under the [MIT LICENSE](https://github.com/amfoss/TempleApp/blob/master/LICENSE).
