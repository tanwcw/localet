package homefulfriends.localet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WikiScraper {
//run using java -cp .:/home/celesteanglm/Documents/techcrunch/jsoup-1.9.2.jar WikiScraper Golden_Gate_Bridge history

    public static String main(String[] args) {

        Document doc;
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> info = new ArrayList<String>();
        String placeName = args[0];
        String detail = args[1];

        try {

            //get all images
            String url = "https://en.wikipedia.org/wiki/" + placeName;
            doc = Jsoup.connect(url).get();
            Element contentDiv = doc.select("div[id=content]").first();
            Element facts = contentDiv.select("div[id=mw-content-text]").first();
            Elements rows = facts.select("tr");
            //Elements headers = facts.select("th[scope=row]").not(".navbox-group");
            //headers = headers.select("class:not(.navbox-group)");
            //System.out.println(facts.toString());
            //Elements text = facts.select("td");

            for (Element row : rows) {
                //System.out.println("Row:");
                Element header = row.select("th[scope=row]").not(".navbox-group").first();
                if (header != null) {
                    //System.out.println("header:");
                    //System.out.println(header.text().toString());
                    titles.add(header.text().toString());
                    //ystem.out.println();
                    Elements text = row.select("td");
                    for (Element t : text) {
                        //System.out.println("text:");
                        //System.out.println(t.text().toString());
                        info.add(t.text().toString());
                        //System.out.println();
                    }
                }
            }
            //System.out.println(titles);
            //System.out.println(info);

            if (detail.equals("history")) {
                ArrayList<String> keywords = new ArrayList<>(Arrays.asList("Designer", "Opened"));
                for (String word : keywords) {
                    System.out.println(word);
                    int index = titles.indexOf(word);
                    System.out.println(info.get(index));
                    String result = word + " " + info.get(index);
                    return result;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}