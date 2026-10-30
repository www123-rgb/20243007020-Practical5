# PracticalFive — Chinese Municipalities

An Android application built for Practical 5: *Resources, Intents, and Dynamic UI in Android*.

The app lets the user type the name of one of China's four direct-administered
municipalities — **Beijing, Shanghai, Tianjin or Chongqing** — validates the
input, and opens a second screen with the municipality's image, an
80–120 word factual summary, a themed colour scheme and a **Visit site**
button that opens the municipality's official website.

## Features

- **Main screen** (`MainActivity` + `activity_main.xml`): title, map image and
  a single-line input field. Pressing **Go** on the keyboard validates the input.
- **Validation** (`TextProcessor`): accepts any casing (`beijing`, `BEIJING`,
  `ChOnGqInG`), returns the correctly capitalised name, and returns `null` for
  empty or unsupported input.
- **Navigation** (`Municipality` + `activity_municipality.xml`): an explicit
  Intent passes the normalised name to the second Activity.
- **Dynamic UI**: the name bar, image, information text and **Visit site**
  button are customised per municipality using named colour resources and the
  supplied images.
- **Implicit Intent**: **Visit site** opens the selected municipality's official
  website (`ACTION_VIEW`) in a suitable application.

## Project structure

```
app/src/main/
├── AndroidManifest.xml
├── java/com/example/practicalfive/
│   ├── MainActivity.java
│   ├── Municipality.java
│   └── TextProcessor.java
└── res/
    ├── drawable/          # municipality images + app logo
    ├── layout/            # activity_main.xml, activity_municipality.xml
    ├── mipmap-*/          # launcher icons (adaptive + legacy)
    └── values/            # arrays.xml, colors.xml, strings.xml, themes.xml
```

## Municipality summaries

The string array `municipalities_array` stores the four summaries in the exact
order: **Beijing, Shanghai, Chongqing, Tianjin** (indexes 0–3). `Municipality.java`
maps each validated name to the matching index, image, colour and website.

| Municipality | Colour resource | Image | Official website |
|---|---|---|---|
| Beijing | `municipality_beijing` (red) | `beijing.jpg` | https://english.beijing.gov.cn/ |
| Shanghai | `municipality_shanghai` (magenta) | `shanghai.jpg` | https://english.shanghai.gov.cn/ |
| Tianjin | `municipality_tianjin` (charcoal) | `tianjin.jpg` | https://en.tj.gov.cn/ |
| Chongqing | `municipality_chongqing` (bright orange) | `chongqing.jpg` | https://english.cq.gov.cn/ |

## How to run

1. Open the project folder in Android Studio (a recent version with AGP 9.x).
2. Wait for Gradle to sync, then run the `app` configuration on a device or emulator.

## Testing

| Input | Expected result |
|---|---|
| `Beijing` / `beijing` / `BEIJING` | Beijing information screen |
| `Shanghai` | Shanghai information screen |
| `Tianjin` | Tianjin information screen |
| `Chongqing` | Chongqing information screen |
| `Guangzhou` | "not one of the four municipalities" error |
| Empty / spaces only | "please enter" validation error |

For every valid municipality check that the name, image, summary, background
colours, information text colour and **Visit site** button colour are correct,
and that **Visit site** opens the matching official website.
