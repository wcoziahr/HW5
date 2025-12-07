# HW5
Overview
This Java Swing application reads a text file containing real numbers (one per line), stores them in a custom LinkedList implementation, and computes the mean and sample standard deviation (using n-1). The GUI uses a JFileChooser to select the input file and displays results and invalid-line summaries.

Requirements satisfied
- Custom LinkedList implementation (src/com/example/statsapp/LinkedList.java)
- JFileChooser GUI (src/com/example/statsapp/MainFrame.java)
- Single-pass statistics (Welford's algorithm) in StatsCalculator
- Error checking and invalid-line reporting
- JUnit tests for core functionality

Build & run
- Java 11 or newer recommended.
- Compile:
  javac -d out src/com/example/statsapp/*.java
- Run:
  java -cp out com.example.statsapp.MainFrame

Testing
- Tests use JUnit 5. Run tests with your preferred setup (IDE or Maven/Gradle).

Notes
- Default: sample standard deviation (n-1). If you prefer population std dev, change StatsCalculator accordingly.
- The parser ignores blank lines (counts them as empty) and reports invalid lines with line numbers.
