import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

void main() throws Exception {

    HttpClient client = HttpClient.newHttpClient();
    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("================================================================");
        System.out.println("please enter the value and currency that you'd like to exchange!");
        System.out.println("Please use: [value] [from] [to] (e.g., 100 USD EUR)");
        System.out.println("type 'exit' to close the application");
        System.out.println("================================================================\n");

        String input = sc.nextLine();

        if (input.equals("exit")) {
            System.out.println("Goodbye!");
            break;
        }

        String[] splitter = input.split(" ");
        double value = Double.parseDouble(splitter[0]);
        String fromCurrency = splitter[1].toUpperCase();
        String toCurrency = splitter[2].toUpperCase();
        double exchangeRate = 0;

        URI address = URI.create("https://open.er-api.com/v6/latest/" + fromCurrency);

        HttpRequest req = HttpRequest.newBuilder()
                .uri(address)
                .GET()
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        String json = resp.body();

        try {
            int SIDX = json.indexOf(toCurrency);
            if (SIDX != -1) {
                SIDX = SIDX + toCurrency.length() + 2;
                int EIDX = json.indexOf(",", SIDX);
                exchangeRate = Double.parseDouble(json.substring(SIDX, EIDX));
            } else {
                System.out.println("Currency not found");
                continue;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("================================================================");
        System.out.printf("%.2f %s = ", value, fromCurrency);
        System.out.printf("%.2f %s\n", value * exchangeRate, toCurrency);
        System.out.printf("Exchange rate: %.2f\n", exchangeRate);
        System.out.println("================================================================\n");
        System.out.println("press Enter to continue...");
        sc.nextLine();


    }
}