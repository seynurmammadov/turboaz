package az.code.myauto.utils;

import az.code.myauto.models.Listing;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.FuelType;
import org.springframework.data.jpa.domain.Specification;

/**
 * This class is for defining specification methods for detailed search.
 */
public class SpecificationUtil {

    public static Specification<Listing> sameOption(String name,Boolean option) {
        return (root, query, criteriaBuilder) -> {
            if (option != null)
                return criteriaBuilder.equal(root.get(name), option);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> sameAutoParam(String name, String data) {
        return (root, query, criteriaBuilder) -> {
            if (data != null)
                return criteriaBuilder.equal(root.get("auto").get(name).get("name"), data);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> between(String name,Integer min, Integer max) {
        return (root, query, criteriaBuilder) -> {
            if (min == null && max == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.between(root.get("auto").get(name), min != null ? min : 0,
                    max != null ? max : Integer.MAX_VALUE);
        };
    }

    public static Specification<Listing> sameCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city != null)
                return criteriaBuilder.equal(root.get("city").get("name"), city);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> sameBodyType(String bodyType) {
        return (root, query, criteriaBuilder) -> {
            if (bodyType != null)
                return criteriaBuilder.equal(root.get("auto").get("bodyType"), BodyType.valueOf(bodyType));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> sameFuelType(String fuelType) {
        return (root, query, criteriaBuilder) -> {
            if (fuelType != null)
                return criteriaBuilder.equal(root.get("auto").get("fueltype"), FuelType.valueOf(fuelType));
            return criteriaBuilder.conjunction();
        };
    }

}
