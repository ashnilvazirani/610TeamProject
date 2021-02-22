import java.util.HashMap;
import java.util.Map;

public class StringSplit { 
    public static void main(String args[]) 
    {
        String yourString = "address: 718 park point drive, apartment: unit 1, city: Rochester, state: NY, zip: 14623";
        Map<String, String> address = new HashMap<>();
        String[] strings = yourString.split(",");
        for (String a : strings){
            // System.out.println(a.trim());
            String key = a.trim().split(":")[0];
            String value = a.trim().split(":")[1];
            System.out.println(key.trim());
            System.out.println(value.trim());
            address.put(key, value);
        }
        address.forEach((key, value) -> System.out.println(key + ":" + value));
    }
}