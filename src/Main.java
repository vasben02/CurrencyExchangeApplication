import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

void main(String[] args) throws Exception {
    System.out.println("please enter the value and currency that you'd like to exchange!");
    System.out.println("format example : 100 USD HUF");
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    String [] splitter = input.split(" ");
    double value = Double.parseDouble(splitter[0]);
    String fromCurrency = splitter[1].toUpperCase();
    String toCurrency = splitter[2].toUpperCase();

    URI address = URI.create("https://open.er-api.com/v6/latest/"+fromCurrency);

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest req = HttpRequest.newBuilder()
            .uri(address)
            .GET()
            .build();

    HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
    String json = resp.body();
    double exchangeRate=0;
    try{
        int SIDX = json.indexOf(toCurrency);
        if(SIDX != -1){
            SIDX = SIDX + toCurrency.length()+2;
            int EIDX = json.indexOf(",", SIDX);
            exchangeRate = Double.parseDouble(json.substring(SIDX, EIDX));
        }
        else {
            System.out.println("Currency not found");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    System.out.printf("%.2f %s\n",value,fromCurrency);
    System.out.printf("%.2f %s\n",value*exchangeRate,toCurrency);

}