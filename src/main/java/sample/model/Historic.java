package sample.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Historic {

    private ArrayList<String> purchase;
    private ArrayList<String> sold;

    public Historic(ArrayList<String> purchase, ArrayList<String> sold) {
        super();
        this.purchase = purchase;
        this.sold = sold;
    }

    public ArrayList<String> getPurchase() {
        return purchase;
    }

    public ArrayList<String> getSellin() {
        return sold;
    }

    public void addToSold(String sale) {
        //System.out.println(sale);
        sold.add(sale);
        exportHistoric();
    }

    public void addToPurchase(String buy) {
        //System.out.println(buy);
        purchase.add(buy);
        exportHistoric();
    }

    public void exportHistoric(){
        String content = "";
        content += "PURCHASE : \n";
        for (int i = 0; i < purchase.size(); i++){
            content += purchase.get(i);
        }
        content += "SOLD : \n";
        for (int i = 0; i < sold.size(); i++){
            content += sold.get(i);
        }
        try {
            usingFileWriter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void usingFileWriter(String fileContent) throws IOException
    {
        FileWriter fileWriter = new FileWriter("./historic.txt");
        fileWriter.write(fileContent);
        fileWriter.close();
    }



}
