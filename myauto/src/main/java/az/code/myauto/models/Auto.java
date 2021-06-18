package az.code.myauto.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auto {
    private long id;
    private Make make;
    private int year;
    private int price;
    private int milage;
    private FuelType fueltype;
    private BodyType bodyType;
    private Color color;
    private GearBox gearBox;
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;

}
