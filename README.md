# Magister's Months - Development Roadmap

## Version 1.0:

### Accelerated Datetime

- Time advancement at 3x speed, calculated deterministically from a configurable epoch.
- In system, a 24-hour day takes places over 8 real-time hours.
- In system, a 375-day year takes place over (approx.) 4 real-time months.

### Fantasy Calendar

https://forgottenrealms.fandom.com/wiki/Calendar_of_Harptos

- Implementation of a non-Gregorian calendar system with twelve 30-day months.
- Year, month, week, day & military time output based on the calculated date-time.
- (Experimental): Holidays existing outside the normal month structure, e.g. Midwinter occurs between 30/01 and 01/02.
  - Each holiday should occur over 24 real-time hours.

### Daylight Cycle Manipulation

- Each day should be divided into 5 hours of daytime and 3 hours of nighttime (real-time).
  - As the daylight cycle is currently divided equally based on current tick ranging from 0 - 24000, tick progression will require regulation.
- Sunset and sunrise should appear as natural as possible, with minimal jumping.
- (Future): Impact on game mechanics will have to be addressed (e.g. crop growth).

### Plugin Integration & Date-time Mechanics

- The plugin will need to be able to provide date-time information for other plugins.
    - For example, to calculate the progression of a Character's age.

## Version 2.0 and beyond

- Festivals
  - Configurable.
- Tablists
  - Will most likely require a separate Tab List plugin to incorporate other information.
- Seasons
  - Crop growth, weather patterns, etc.
  - Configurable.
- Block manipulation
  - e.g. Gates that open at a specific time (may also be better in a separate plugin).
- Area events
  - Flavour text and player interaction based on date-time.
- Lunar cycles
