# Modular Currency Exchange Application

A dynamic, console-based financial utility tool written in **Java** that allows users to perform real-time global currency conversions using dynamic external data inputs.

## Features
- **Dynamic Live Rates:** Automatically fetches the latest, up-to-date foreign exchange rates.
- **User-Centric Inputs:** Standardized input scanning (`VALUE FROM_CURRENCY TO_CURRENCY`) that accepts any global currency pair (e.g., `100 USD EUR`).
- **Robust Parsing:** Built-in isolated algorithmic parsing routine using custom string manipulation (`indexOf` and `substring`).
- **Input Resilience:** Handles case-insensitivity seamlessly (e.g., automatically converts `usd` to `USD`).
- **Fault Tolerance:** Integrated `try-catch` structures to isolate potential runtime networking anomalies safely.

## Tech Stack
- **Language:** Java 25 (Standard Edition)
- **Networking:** Native `java.net.http.HttpClient`, `HttpRequest`, `HttpResponse`
- **Data Source:** Live External REST API (ExchangeRate-API)

## How to Run
1. Clone the repository.
2. Run the `Main` class.
3. Input your desired amount and currency tickers when prompted. Example: `250.50 EUR HUF`