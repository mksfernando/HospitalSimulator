# Hospital Simulator
Thanks for sending me this assessment. Please find the below sections for Build / Run steps and my Assumptions on implementation.

## Build and Run
- Build  : mvn clean install
- Run    : mvn spring-boot:run

## Assumptions
- Patients' Health Status Codes and Drug Codes are case-sensitive.
- Added a loop to maintain the continuity of the program.
- To break the loop and exit the program, press Enter without any command-line input.

## Rules defined in Adjacency  List
- F -> [{[P, An], X}, {[As], H}, {[P], H}, {[], F}]
- T -> [{[P, An], X}, {[An], H}, {[], T}]
- D -> [{[P, An], X}, {[I]}, D}, {[], X}]
- H -> [{[P, An], X}, {[I, An], F}, {[], H}]
- X -> [{[P, An], X}, {[], X}]