import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

void main() throws Exception {

    HttpClient httpClient = HttpClient.newHttpClient();
    Scanner sc = new Scanner(System.in);

    while (true) {
        WelcomeMessage();
        String menu = sc.nextLine().trim();
        if (menu.equals("exchange")) {
            while (true) {
                exchangeWelcomeMessage();

                String input = sc.nextLine().trim();

                if (input.equals("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }

                String[] splitter = input.split(" ");
                if (splitter.length != 3) {
                    System.out.println("Invalid input!");
                    continue;
                }

                exchangeProcess(splitter, httpClient);
                System.out.println("Press enter to continue...");
                sc.nextLine();
            }
        } else if (menu.equals("rates")) {
            while (true) {
                System.out.println("Which currency's exchange rates would you like to see?");
                System.out.println("Type 'exit': to leave.");
                String input = sc.nextLine().trim();

                if (input.equals("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }

                exchangeRates(httpClient, input);

                System.out.println("Press enter to continue...");
                sc.nextLine();
            }


        } else {
            if (menu.equals("exit")) {
                break;
            }else{
                System.out.println("invalid input!");

            }
        }


    }


}

private void WelcomeMessage() {
    System.out.println("================================================================");
    System.out.println("Welcome! What would you like to do?");
    System.out.println("Exchange: Type 'exchange' for exchanging an amonut of currency.");
    System.out.println("Exchange Rates: Type 'rates' for a list of exchange rates.");
    System.out.println("================================================================\n");
}

private void exchangeWelcomeMessage() {
    System.out.println("================================================================");
    System.out.println("Please enter the value and currency that you'd like to exchange!");
    System.out.println("Please use: [value] [from] [to] (e.g., 100 USD EUR)");
    System.out.println("Type 'exit' to close the application");
    System.out.println("================================================================\n");
}

private void exchangeProcess(String[] splitter, HttpClient httpClient) {
    try {
        double value = Double.parseDouble(splitter[0]);
        String fromCurrency = splitter[1].toUpperCase();
        String toCurrency = splitter[2].toUpperCase();
        double exchangeRate;
        String resp = fetchExchangeRate(httpClient, fromCurrency);
        if (resp.contains("error")) {
            System.out.println("Error! Wrong source currency!");
            return;
        }
        int garbageIDX = resp.indexOf("\"rates\"");
        int SIDX = resp.indexOf(toCurrency, garbageIDX);
        if (SIDX != -1) {
            SIDX = SIDX + toCurrency.length() + 2;
            int EIDX = resp.indexOf(",", SIDX);
            exchangeRate = Double.parseDouble(resp.substring(SIDX, EIDX));
        } else {
            System.out.println("Currency not found");
            return;
        }
        System.out.println("================================================================");
        System.out.printf("%.2f %s = ", value, fromCurrency);
        System.out.printf("%.2f %s\n", value * exchangeRate, toCurrency);
        System.out.printf("Exchange rate: %.4f\n", exchangeRate);
        System.out.println("================================================================\n");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

private String fetchExchangeRate(HttpClient httpClient, String fromCurrency) throws Exception {
    URI address = URI.create("https://open.er-api.com/v6/latest/" + fromCurrency);

    HttpRequest req = HttpRequest.newBuilder()
            .uri(address)
            .GET()
            .build();

    HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

    return resp.body();
}

private void exchangeRates(HttpClient httpClient, String fromCurrency) throws Exception {
    if (fromCurrency.isEmpty()) {return;}
    String resp = fetchExchangeRate(httpClient, fromCurrency.toUpperCase());
    if (resp.contains("error")) {
        System.out.println("Error! Wrong source currency!");
        return;
    }
    int SIDX = resp.indexOf("{", 1);
    int EIDX = resp.indexOf("}", SIDX);
    String[] splitter = resp.substring(SIDX+1, EIDX).split(",");
    System.out.println("\n================================================================");
    System.out.println("         Every exchange rate for: " + fromCurrency);
    System.out.println("================================================================");
    for (String s : splitter) {
        System.out.println(s);
    }
}