"# MoviesApp" 
# MoviesApp

What is MVVM
MVVM stands for Model View ViewModel and it is an architectural pattern for implementing user interfaces.

Model–view–viewmodel is a software architectural pattern.

Components of MVVM pattern
Model
Model refers either to a domain model, which represents real state content (an object-oriented approach), or to the data access layer, which represents content (a data-centric approach).[citation needed]
View
As in the model-view-controller (MVC) and model-view-presenter (MVP) patterns, the view is the structure, layout, and appearance of what a user sees on the screen.[6] It displays a representation of the model and receives the user's interaction with the view (clicks, keyboard, gestures, etc.), and it forwards the handling of these to the view model via the data binding (properties, event callbacks, etc.) that is defined to link the view and view model.
View model
The view model is an abstraction of the view exposing public properties and commands. Instead of the controller of the MVC pattern, or the presenter of the MVP pattern, MVVM has a binder, which automates communication between the view and its bound properties in the view model. The view model has been described as a state of the data in the model.[7]
The main difference between the view model and the Presenter in the MVP pattern, is that the presenter has a reference to a view whereas the view model does not. Instead, a view directly binds to properties on the view model to send and receive updates. To function efficiently, this requires a binding technology or generating boilerplate code to do the binding.[6]


# references

https://medium.com/mindorks/using-mvvm-android-jetpack-ebf1d9c1477c <br/>
Using MVVM |Android Jetpack

https://www.youtube.com/watch?v=QrbhPcbZv0I <br/>
Droidcon NYC 2016 - A Journey Through MV Wonderland (updated)

https://medium.com/upday-devs/android-architecture-patterns-part-1-model-view-controller-3baecef5f2b6 <br/>
Model-View-Controller

https://medium.com/upday-devs/android-architecture-patterns-part-3-model-view-viewmodel-e7eeee76b73b <br/>
Model-View-ViewModel

https://medium.com/upday-devs/android-architecture-patterns-part-2-model-view-presenter-8a6faaae14a5 <br/>
The Model-View-Presenter Pattern

https://medium.com/@margaretmz/exploring-the-android-architecture-components-117515acfa8 <br/>
MVVM on Android with the Architecture Components

# instructions

Getting Started
App uses The Movie Database API. You have to enter your API key in order to run the app. You can create your own one very easy! https://www.themoviedb.org/account/signup?language=en-EN. When you get it, just set it here: package eu.artandroidapps.mvvm_tmdb.moviesapp.repository;  

    <!--Insert the key. -->
       private static final String API_KEY = Insert the key here.;
       
       

       
       
