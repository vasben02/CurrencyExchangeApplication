# Modular Currency Exchange Application

A dynamic, console-based financial utility tool written in **Java** that allows users to perform real-time global currency conversions and query bulk exchange rate listings using dynamic external data inputs.

## Features
- **Dual Mode Interface:** Offers two distinct functionalities based on user preference:
    - **Currency Exchange:** Converts a specific amount from one currency to another.
    - **Bulk Rates Listing:** Displays the entire global market catalog relative to a chosen base currency.
- **Dynamic Live Rates:** Automatically fetches the latest, up-to-date foreign exchange rates from a live API.
- **User-Centric Inputs:** Standardized input scanning that accepts any global currency pair via tickers (e.g., `100 USD EUR` or `HUF`).
- **Robust Algorithmic Parsing:** Core string extraction routines utilize structured token isolating (`indexOf` and `substring`) to process raw data fields without heavy JSON library dependencies.
- **Input Resilience & Case-Insensitivity:** Gracefully handles case variation (e.g., maps `usd` to `USD`) and sanitizes unintended whitespace.
- **Focus & Fault Tolerance:** Integrated input guards and `try-catch` boundaries handle runtime networking anomalies, malformed queries, or console window focus losses seamlessly.

## Tech Stack
- **Language:** Java 25 (Standard Edition)
- **Networking:** Native `java.net.http.HttpClient`, `HttpRequest`, `HttpResponse`
- **Data Source:** Live External REST API (ExchangeRate-API)

## How to Run
1. Clone the repository.
2. Run the `Main` class.
3. Choose your desired action from the main menu by typing `exchange` or `rates`.

### Execution Examples:
- **For Exchange Mode:**
  ```text
  Enter mode: exchange
  Input prompt: 250.50 EUR HUF