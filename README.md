# 🚌 BusSaathi – Real-Time Government Bus Tracking App

**BusSaathi** is a modern Android application built using **Kotlin** and **Jetpack Compose**, following the **MVVM architecture**.  
It solves the problem of *missing government buses* by providing **real-time bus tracking** and **smart route suggestions**, ensuring you’ll *never miss your bus again!*  

## 🎥 Working 

📺 **Watch the full video here:**  
👉 [Working Video Link](https://drive.google.com/file/d/1S1yZbXOTsgJozRmgPgEwN81wn5ncznyr/view?usp=sharing)

## 🔗 Related Repositories

- 🧠 **Backend Repo:** [BusSaathi Backend](https://github.com/karan3613/BusAppBackend)  
- 🚍 **Driver App Repo:** [BusSaathi Driver App](https://github.com/karan3613/DriverApp)

  
## 🚀 Overview

Bussatthi connects passengers and government bus drivers in real time.  
Users can:
- Sign in and allow the app to automatically detect their **current location**.  
- Select their **destination** from nearby options.  
- View a list of **nearby buses** heading toward their destination.  
- See both **immediate** and **later-arriving** buses based on live GPS data.  
- Track the bus on a **live map**, similar to how ride-hailing apps like Uber work.

A companion **Driver App** updates each bus’s location every **3 seconds**, ensuring accurate tracking for passengers.


## 🧩 Features

- 🧭 **Real-Time Tracking** – Live location updates for each bus every 3 seconds.  
- 🗺️ **Smart Bus Recommendations** – Shows nearest and upcoming buses for your route.  
- 🔒 **User Authentication** – Secure sign-in system for passengers and drivers.  
- 📍 **Automatic Location Detection** – Fetches user’s current location seamlessly.  
- 🚍 **Driver App Integration** – Driver app continuously updates bus data.  
- 🖤 **Modern UI** – Built entirely in **Jetpack Compose** with a sleek, intuitive interface.  
- ⚙️ **MVVM Architecture** – Clean separation of concerns for scalability and maintainability.  
- 🌐 **Backend Integration** – Uses REST APIs for communication between apps and server.  


## 🛠️ Tech Stack

| Layer | Technologies |
|-------|---------------|
| **Frontend (Passenger App)** | Kotlin, Jetpack Compose, MVVM, Retrofit, Google Maps API |
| **Backend** | Python / MySQL / Kafka  |
| **Driver App** | Kotlin, MVVM, Location Services, Retrofit |
| **Dependency Injection** | Hilt |
| **State Management** | ViewModel + Immutable State Classes |


## 🧑‍💻 How It Works

1. **Driver App:** Sends live bus location and route ID to the backend every 3 seconds.  
2. **Backend Server:** Stores and updates real-time locations in the database.  
3. **Bussatthi App (Passenger):** Fetches data from the backend and displays active buses on the map, filtered by the selected destination.  
4. **User Experience:** Passengers can choose a bus, view its ETA, and track it live until arrival.  



## 🌟 Future Enhancements

- 🕒 Estimated Time of Arrival (ETA) prediction using AI/ML models.  
- 💬 In-app chat between passengers and bus operators.  
- 🔔 Push notifications for bus arrival alerts.  
- 📶 Offline caching for low-network areas.  



## 🤝 Contributing

Contributions, issues, and feature requests are welcome!  
Feel free to fork the repo and submit a pull request.


## ✨ Author
> "With Bussatthi, missing your bus is now a thing of the past!" 🚍💨


