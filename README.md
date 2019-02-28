# NowPlaying
A simple andriod app that shows the current popular movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).
---

### User Stories
- User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.
- Views are responsive for both landscape/portrait mode.
   - In portrait mode, the poster image, title, and movie overview is shown.
   - In landscape mode, the rotated alternate layout uses the backdrop image instead and shows the title and movie overview to the right of it.
- Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- Allow video posts to be played in full-screen using the YouTubePlayerView.

### App Walkthough GIF

<img src="https://github.com/MysticalLatios/NowPlaying/blob/master/demo1.gif" width=250><br>

### Notes
Notes go here

### Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
