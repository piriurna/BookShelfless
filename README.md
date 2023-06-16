# BookShelfless

BookShelfless is a comprehensive Android application for exploring and saving your favorite books about mobile development. Utilizing Google's Books API, the app displays a two-column list of available books, fetches and displays book thumbnails, and dynamically loads more books as you scroll through the list.

## Features

- **Efficient Book Browsing:** The application provides a clean, simple 2-column list of available books about mobile development. As you scroll through the list, the app dynamically fetches and displays more books, ensuring a smooth and seamless browsing experience.

- **Favorites:** The app allows you to mark books as favorites. You can filter the book list to display only your favorite books.

- **Book Details:** When you select a book from the list, the app presents a detailed view displaying the most relevant information of the book, including Title, Author, and Description. If available, a Buy link is also provided.

- **Buy Link:** Clicking on the Buy link opens the link in Safari/Chrome, providing a convenient way to purchase your favorite books.

- **Persisting Favorites:** The app stores your favorite selections locally, so they persist through each app usage.

## Technologies Used

The app is built using modern Android development tools and libraries, and follows the clean architecture principles with the usage of use cases:

- **Dagger 2** for dependency injection
- **Android Data Binding** for efficient UI development
- **JSON** for data interchange
- **Retrofit** for network requests
- **MVVM architecture** for separation of concerns and easier testing
- **RxJava** for handling asynchronous data streams
- **Room** for local data storage
- **Use Cases** for encapsulating each business rule as a separate testable unit

## REST API

The application is built on top of Google's Books API.

Example API Call:
`https://www.googleapis.com/books/v1/volumes?q=ios&maxResults=20&startIndex=0`

More information on the Google Books API can be found [here](https://developers.google.com/books/docs/v1/getting_started#REST).

## How to Use

Download or clone this repository and import into your Android Studio. Build the project and run on your Android device or emulator.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

MIT
