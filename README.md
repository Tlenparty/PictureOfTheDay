# Picture of the Day

An Android app that shows the Astronomy Picture of the Day
([NASA APOD](https://apod.nasa.gov/apod/)): the image, title and description
are loaded through the [NASA Open API](https://api.nasa.gov/). The project also serves as a
sandbox for Material Components, animations and text handling.

## Features

- NASA APOD picture of the day with title and description (ViewPager2 + TabLayout)
- Wikipedia term search right from the main screen
- Splash screen with animation
- Bottom navigation, BottomAppBar with a FAB and a sliding menu (BottomSheet)
- Theme selection ("Space" / "Pink Planet"), stored in SharedPreferences
- Demo screens: animations and transitions, RecyclerView with drag & drop and swipe,
  Spannable text and downloadable fonts

## Tech stack

| Area           | Technologies                                 |
|----------------|----------------------------------------------|
| Language       | Kotlin                                       |
| Architecture   | MVVM (ViewModel + LiveData)                  |
| Networking     | Retrofit, OkHttp, Gson                       |
| Images         | Coil                                         |
| UI             | ViewBinding, Material Components, ViewPager2, CircleIndicator |
| Build          | Gradle (Groovy DSL)                          |

## Project structure

```
app/src/main/java/com/tlenparty/pictureoftheday/
├── model/          # Retrofit client and NASA APOD models
├── viewmodel/      # screen states (Loading / Success / Error)
├── picture/        # main screen and bottom menu
├── viewpager/      # picture-of-the-day pages
├── api/            # Earth / Mars / Weather screens
├── settings/       # theme selection
├── animations/     # animation and transition demos
├── recycler/       # RecyclerView: drag & drop, swipe, DiffUtil
└── span/           # Spannable text
```

## Build

Requires JDK 11–17 and Android SDK 31.

The NASA API key is set in the `apikey.properties` file in the project root
(the file is listed in `.gitignore`); without it the public `DEMO_KEY` is used, which has strict rate limits:

```properties
NASA_API_KEY="your_key"
```

```bash
./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.
