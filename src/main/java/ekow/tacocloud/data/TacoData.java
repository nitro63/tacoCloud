package ekow.tacocloud.data;

import lombok.Data;

import java.util.List;

@Data
public class TacoData {
    private String name;
    private List<IngredientData> ingredients;
}
