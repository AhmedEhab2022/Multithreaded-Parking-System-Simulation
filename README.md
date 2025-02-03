# Multithreaded Parking System Simulation

This project simulates a multithreaded parking system in which cars arrive at 3 different gates, wait for 4 available parking spots, park for a specified duration, and then leave. The system uses custom semaphores to manage parking spots and ensures thread-safe operations.

---

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [How It Works](#how-it-works)
4. [Project Structure](#project-structure)
5. [Input File Format](#input-file-format)
6. [Output](#output)
7. [How to Run](#how-to-run)
8. [Dependencies](#dependencies)
9. [Contributing](#contributing)
10. [License](#license)

---

## Overview

This project is a simulation of a parking system with multiple gates and a limited number of parking spots. Cars arrive at different gates, wait for an available spot, park for a specified time, and then leave. The system is implemented using multithreading and a custom semaphore to manage the parking spots efficiently.

---

## Features

- **Multithreaded Simulation**: Each car and gate runs in its own thread, simulating real-world concurrency.
- **Custom Semaphore**: A custom implementation of a semaphore is used to manage parking spots.
- **Dynamic Input**: The system reads car details (gate number, car number, arrival time, and parking duration) from an input file.
- **Logging**: All events (arrival, parking, waiting, and departure) are logged to both the console and an output file.
- **Thread Safety**: The custom semaphore ensures thread-safe access to shared resources (parking spots).

---

## How It Works

1. **Input Parsing**: The system reads car details from an input file (`input.txt`).
2. **Gate Initialization**: Gates are initialized with the cars assigned to them.
3. **Car Simulation**:
   - Each car arrives at its assigned gate after a specified arrival time.
   - If no parking spot is available, the car waits until a spot is free.
   - Once parked, the car stays for the specified duration and then leaves, freeing up the spot.
4. **Logging**: All events are logged to `output.txt` and displayed in the console.
5. **Summary**: At the end of the simulation, a summary of the total cars served and the current state of the parking lot is displayed.

---

## Project Structure

```
Multithreaded-Parking-System-Simulation/
├── src/
│   ├── parkingComponents/
│   │   ├── Car.java          # Car thread implementation
│   │   ├── CustomSemaphore.java # Custom semaphore implementation
│   │   ├── Gate.java         # Gate thread implementation
│   │   ├── Spots.java        # Parking spots management
│   ├── Main.java             # Main class to run the simulation
│   ├── input.txt             # Input file for car details
│   ├── output.txt            # Output file for logs
├── .gitignore                # Specifies files and directories to be ignored by Git (e.g., IDE files, compiled binaries)
├── LICENSE                   # License file detailing the terms under which the project is distributed
├── README.md                 # Project documentation
```

---

## Input File Format

The input file (`input.txt`) should contain car details in the following format:

```
Gate <gateNumber>, Car <carNumber>, Arrive <arrivalTime>, Parks <parkingDuration>
```

Example:
```
Gate 1, Car 1, Arrive 2, Parks 5
Gate 2, Car 2, Arrive 3, Parks 4
Gate 3, Car 3, Arrive 1, Parks 3
```

---

## Output

The system logs all events to both the console and `output.txt`. The output includes:

- Car arrival and parking status.
- Waiting times for cars if no spots are available.
- Departure of cars and the current parking status.
- A summary of the total cars served and the current state of the parking lot.

Example Output:
```
Car 1 from Gate 1 arrived at time 2.
Car 1 from Gate 1 parked. (Parking Status: 1 spots occupied)
Car 1 from Gate 1 left after 5 units of time. (Parking Status: 0 spots occupied)
...
Total Cars Served: 3
Current Cars in Parking: 0
Details:
- Gate 1 served 1 cars
- Gate 2 served 1 cars
- Gate 3 served 1 cars
```

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/AhmedEhab2022/Multithreaded-Parking-System-Simulation.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Multithreaded-Parking-System-Simulation
   ```
3. Compile the Java files:
   ```bash
   javac src/Main.java
   ```
4. Run the simulation:
   ```bash
   java -cp src Main
   ```
5. Check the output in `output.txt` and the console.

---

## Dependencies

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed.
- **Input File**: Provide an `input.txt` file with car details.

---

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes with clear and descriptive messages.
4. Push your changes to your fork.
5. Submit a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
