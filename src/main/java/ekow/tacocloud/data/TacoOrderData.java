package ekow.tacocloud.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class TacoOrderData {

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<TacoData> tacos = new ArrayList<>();

    public void addTaco(TacoData taco){
        this.tacos.add(taco);
    }


}
