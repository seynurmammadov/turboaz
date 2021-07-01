package az.code.myauto;

import az.code.myauto.models.*;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.enums.*;
import az.code.myauto.repositories.*;
import az.code.myauto.services.interfaces.SearchService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static az.code.myauto.utils.SpecificationUtil.*;
import static az.code.myauto.utils.SpecificationUtil.sameOption;
import static org.junit.Assert.assertEquals;

@SpringBootTest
class MyautoApplicationTests {
    @Autowired
    SearchService searchService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CityRepo cityRepo;
    @Autowired
    ListingRepo listingRepo;
    @Autowired
    MakeRepo makeRepo;
    @Autowired
    ModelRepo modelRepo;
    @Autowired
    EquipmentRepo equipmentRepo;

    @Test
    void search() {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        List<Model> models = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Instant instant = Instant.ofEpochMilli(faker.date().birthday().getTime());
            users.add(User.builder()
                    .username(faker.name().username() + i)
                    .name(faker.name().firstName())
                    .surname(faker.name().lastName())
                    .balance(faker.number().numberBetween(200, 2000))
                    .createdAt(LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
                    .email(faker.internet().emailAddress())
                    .phoneNumber("+994595928405")
                    .isActive(true)
                    .build());
            cities.add(
                    City.builder().name(faker.address().city()).build()
            );
            models.add(Model.builder().name(faker.name().name()).build());
        }
        models = modelRepo.saveAll(models);
        cities = cityRepo.saveAll(cities);
        users = userRepo.saveAll(users);
        List<Make> makes = new ArrayList<>();
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            makes.add(Make.builder().name(faker.name().name()).models(Collections.singletonList(Model.builder().id(models.get(0).getId()).build())).build());
            equipments.add(Equipment.builder().name(faker.name().name()).build());
        }

        makes = makeRepo.saveAll(makes);
        equipments = equipmentRepo.saveAll(equipments);
        List<Listing> listings = new ArrayList<>();
        int c =0;
        for (int i = 0; i < 10; i++) {
            if(i==4){
                c=1;
            }
            Instant instant = Instant.ofEpochMilli(faker.date().birthday().getTime());
            listings.add(
                    Listing.builder()
                            .auto(Auto.builder()
                                    .make(makes.get(i))
                                    .year(faker.number().numberBetween(1990, 2021))
                                    .price(faker.number().numberBetween(100,200))
                                    .mileage(faker.number().numberBetween(100,200))
                                    .fueltype(FuelType.values()[c])
                                    .bodyType(BodyType.values()[c])
                                    .color(Color.values()[c])
                                    .gearBox(GearBox.values()[c])
                                    .equipments(Collections.singletonList(equipments.get(i)))
                                    .model(models.get(i))
                                    .build())
                            .auto_pay(true)
                            .creditOption(true)
                            .barterOption(true)
                            .leaseOption(true)
                            .cashOption(true)
                            .description(faker.gameOfThrones().quote())
                            .type(ListingType.VIP)
                            .images(Collections.singletonList(Image.builder().name("url").build()))
                            .createdAt(LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
                            .updatedAt(LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
                            .User(users.get(i))
                            .city(cities.get(0))
                            .build()
            );
        }
        listingRepo.saveAll(listings);
       List<ListingListDTO> listingListDTOS = searchService.search(sameAutoParam("make", makes.get(0).getName())
                        .and(sameAutoParam("model", models.get(0).getName()))
                        .and(sameCity(cities.get(0).getName()))
                        .and(between("year", 1990, 2021))
                        .and(between("price", 100, 200))
                        .and(between("mileage", 100, 200))
                        .and(sameFuelType(FuelType.PETROL.getName()))
                        .and(sameBodyType(BodyType.SEDAN.getName()))
                        .and(sameOption("creditOption", true))
                        .and(sameOption("barterOption", true))
                        .and(sameOption("leaseOption", true))
                        .and(sameOption("cashOption", true)),
                PageRequest.of(0, 10));
        assertEquals(listingListDTOS.size(), 1);

    }

}
