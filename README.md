# TempleApp

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Gitter](https://badges.gitter.im/amfoss/TempleApp.svg)](https://gitter.im/amfoss/TempleApp)
[![Code of Conduct](https://img.shields.io/badge/%E2%9D%A4-code%20of%20conduct-e04545.svg?style=flat)](https://github.com/amfoss/TempleApp/blob/master/CODE_OF_CONDUCT.md)


This app is a cost-efficient way of maintaining records of poojas, donations made by people and also all the financial records in temples. Using this app, people in the temple counter will be able to enter all the details regarding the poojas they want or some donations they want to do for the temple. This app is connected to a printer so that it will print the details of pooja registrations of users.

The app has a login interface and only some people like General secretary, president and treasurer of the temple can log in and be able to access the complete database.

Here is the **[link](https://drive.google.com/open?id=1BXv93pydW1ZQzPpljQ9ZILmTqYcAJjW_)** to the apk of the latest release of the app.

## Getting Started

These instructions will get you a copy of the project up and be running on your local machine for development and testing purposes.

### Prerequisites

[Android Studio](https://developer.android.com/studio), with a recent version of the Android SDK.

### Setting up your development environment

- Download and install Git.

- Fork the [TempleApp project](https://gitlab.com/amfoss/TempleApp)

- Clone your fork of the project locally. At the command line:
    ```
    $ git clone https://gitlab.com/YOUR-GITLAB-USERNAME/TempleApp
    ```

If you prefer not to use the command line, you can use Android Studio to create a new project from version control using 
```
https://gitlab.com/YOUR-GITLAB-USERNAME/TempleApp
```

Open the project in the folder of your clone from Android Studio and build the project. If there are any missing dependencies, install them first by clicking on the links provided by Android studio. Once the project is built successfully, run the project by clicking on the green arrow at the top of the screen.

### Why TempleApp ?

There are a lot of temples in India which are facing issues like 
* No Proper management system
* Extensive usage of paper 
* Management of donation
* Network of temple
* Auditing of the Revenue Generated

TempleApp is primarily built to handle the above mentioned problems by offering a user friendly android application to manage temples better.

## How it Works?

Users can use their email ID to sign-up/sign-in. Once in dashboard, users can navigate between adding poojas, donations or income. The floating action button (+) can be used to do the same.

For the devs: MVVM architecture is followed in this application. Firebase is being used for user account authentication and database management, which includes searching, ordering and bulk operations.   

#### Project structure

```
.......
templeapp   
├── expenses
│   └── ExpensesFragment.java
├── home
│   ├── HomeActivity.java
│   ├── LoginActivity.java
│   ├── SectionsPagerAdapter.java
│   └── UserModel.java
├── income
│   ├── adapter
│   │    ├── DonationAdapter.java
│   │    └── DonationModel.java
│   ├── addDonation
│   │    ├── AddDonationActivity.java
│   │    └── ConfirmDetailsDonationActivity.java
│   ├── viewmodels
│   │    └── IncomeViewModel.java
│   └── IncomeFragment.java
└── poojas
    ├── adapter
    │    ├── PoojaAdapter.java
    │    └── PoojaModel.java
    ├── addPooja
    │    ├── AddPoojaActivity.java
    │    └── ConfirmDetailsPoojaActivity.java
    ├── viewmodels
    │    └── PoojaViewModel.java
    └── PoojaFragment.java
 ```

## Screenshots

<table border="0">
  <tr>
    <td><img src="https://user-images.githubusercontent.com/20596763/78275472-46cd6500-752f-11ea-92e0-15eb11d35da7.png" width="300"></td>
    <td><img src="https://user-images.githubusercontent.com/20596763/78275150-d9b9cf80-752e-11ea-8e62-34b9475f1e82.png" width="300"></td>
    <td><img src="https://user-images.githubusercontent.com/20596763/78275535-564cae00-752f-11ea-88a7-e368e6f3c3f0.png" width="300"></td>
  </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/20596763/78274948-90698000-752e-11ea-9116-195940b9ffde.png" width="300"></td>
    <td><img src="https://user-images.githubusercontent.com/20596763/78275923-e5f25c80-752f-11ea-8f40-10c4859155eb.png" width="300"></td>
    <td><img src="https://user-images.githubusercontent.com/20596763/78275791-b5122780-752f-11ea-9280-291fe083ce7a.png" width="300"></td>
  </tr>
</table>

## Dependencies
- [appcompat-v7:28.0.0](https://mvnrepository.com/artifact/com.android.support/appcompat-v7/28.0.0-alpha1)
- [junit:4.12](https://mvnrepository.com/artifact/junit/junit/4.12)
- [material_text_view](https://material.io/develop/android/components/material-text-view/)
- [cardview-v7:28.0.0](https://mvnrepository.com/artifact/com.android.support/cardview-v7/28.0.0-alpha1)
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [retrofit](https://square.github.io/retrofit/)
- [glide](https://bumptech.github.io/glide/)
- [butterknife](https://jakewharton.github.io/butterknife/)


## License
This project is licensed under the [GNU General Public License v3.0](https://gitlab.com/amfoss/TempleApp/blob/master/LICENSE).