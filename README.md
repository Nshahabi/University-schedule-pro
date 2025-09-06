# Java Schedule Pro

Ever tried to juggle classes, labs, and credits, only to realize two of them overlap? This project is my take on solving that problem in code. It’s a simple scheduling system built in Java where students can manage their courses, check for conflicts, and even calculate their GPA — all from the command line.

I originally built this as part of a class, but I decided to polish it up into something recruiters (and other developers) could actually play with. Think of it as a pocket-sized student registration system.

---

## What it Does
- Add and drop courses for a student
- Prevent double-booking with time conflict detection
- Keep an eye on credit limits
- Calculate GPA on the classic 4.0 scale
- Save and load schedules as JSON files
- Comes with unit tests and CI for reliability

---

## Getting Started
Clone the repo and run it with Maven:

```bash
mvn -q package
java -cp target/java-schedule-pro-1.0.0.jar edu.name.schedule.cli.Main
