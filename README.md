# Picture of the Day

Android-приложение, которое показывает «Астрономическую картинку дня»
([NASA APOD](https://apod.nasa.gov/apod/)): снимок, заголовок и описание
загружаются через [NASA Open API](https://api.nasa.gov/). Проект также служит
песочницей для Material Components, анимаций и работы с текстом.

## Возможности

- Картинка дня NASA APOD с заголовком и описанием (ViewPager2 + TabLayout)
- Поиск термина в Wikipedia прямо с главного экрана
- Splash-экран с анимацией
- Нижняя навигация, BottomAppBar с FAB и выезжающее меню (BottomSheet)
- Выбор темы оформления («Космос» / «Розовая планета»), сохраняется в SharedPreferences
- Демо-экраны: анимации и переходы, RecyclerView с drag & drop и swipe,
  Spannable-текст и загружаемые шрифты

## Стек

| Область        | Технологии                                   |
|----------------|----------------------------------------------|
| Язык           | Kotlin                                       |
| Архитектура    | MVVM (ViewModel + LiveData)                  |
| Сеть           | Retrofit, OkHttp, Gson                       |
| Изображения    | Coil                                         |
| UI             | ViewBinding, Material Components, ViewPager2, CircleIndicator |
| Сборка         | Gradle (Groovy DSL)                          |

## Структура

```
app/src/main/java/com/tlenparty/pictureoftheday/
├── model/          # Retrofit-клиент и модели NASA APOD
├── viewmodel/      # состояния экрана (Loading / Success / Error)
├── picture/        # главный экран и нижнее меню
├── viewpager/      # страницы с картинкой дня
├── api/            # экраны Earth / Mars / Weather
├── settings/       # выбор темы
├── animations/     # демо анимаций и переходов
├── recycler/       # RecyclerView: drag & drop, swipe, DiffUtil
└── span/           # Spannable-текст
```

## Сборка

Требуется JDK 11–17 и Android SDK 31.

Ключ NASA API задаётся в файле `apikey.properties` в корне проекта
(файл в `.gitignore`); без него используется публичный `DEMO_KEY` с жёсткими лимитами:

```properties
NASA_API_KEY="ваш_ключ"
```

```bash
./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.
