
**Maxdoro Employer Search**

**EmployerSearch** is an Android application that allows users to query for different employers using the provided API endpoint: [CBA Employers API](https://cba.kooijmans.nl/CBAEmployerservice.svc/rest/employers?filter=Achmea&maxRows=100). Users can input text to specify different filters for their employer search.

# Features
- Query for different employers using text input
- Users can sort list by Name, Discount Percentage and Place
- Display results of the API call in a user-friendly view
- Gracefully handle loading state while fetching data from the server
- Show error message when an error occurs during API request

# Built With 
- Kotlin
- Retrofit
- Coroutines
- Room
- LiveData
- ViewModel
- RecyclerView
- Material Design Components

# Architecture 
The architecture of **EmployerSearch** follows modern Android app development principles:
1. MVVM (Model-View-ViewModel) architecture pattern for separation of concerns and maintainability.
2. Repository pattern for data abstraction and management.
3. LiveData for observing data changes and updating UI accordingly.
4. Room for local database caching and persistence.

A high-level overview of the app's architecture is as follows:

<p align="center">
   <img src="https://example.com/architecture_diagram.png" width="750">
</p>

# Testing 
To ensure reliability and maintainability, **EmployerSearch** includes comprehensive testing:
 - Unit tests for business logic and data manipulation (ViewModel).
