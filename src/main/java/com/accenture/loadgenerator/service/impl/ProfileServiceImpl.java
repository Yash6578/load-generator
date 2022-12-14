package com.accenture.loadgenerator.service.impl;

import com.accenture.contractlibrary.models.Address;
import com.accenture.contractlibrary.models.Name;
import com.accenture.contractlibrary.models.Profile;
import com.accenture.loadgenerator.service.ProfileService;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProfileServiceImpl implements ProfileService {
    FakeValuesService faker = new FakeValuesService(new Locale("en-US"), new RandomService());

    @Override
    public Profile generateRandomProfile() {

        String[] type = {"individual", "business"};
        int typeIndex = getRandomInt(0, 2);

        return Profile.builder()
                .id(faker.bothify("####?????#####??????"))
                .type(type[typeIndex])
                .nameList(getRandomNameList())
                .addressList(getRandomAddressList())
                .eventTime(ZonedDateTime.now().plusMinutes(getRandomInt(0, 1000)))
                .build();
    }

    @Override
    public List<Profile> generateRandomProfiles(Long n) {
        List<Profile> profileList = new ArrayList<>();

        for(int i = 0;i < n;i++)
            profileList.add(generateRandomProfile());

        return profileList;
    }

    List<Name> getRandomNameList() {
        List<Name> nameList = new ArrayList<>();
        Name name = null;

        int n = getRandomInt(1, 11);

        for(int i = 0;i < n;i++) {
            name = Name.builder()
                    .firstName(faker.letterify("?????"))
                    .lastName(faker.letterify("???????"))
                    .lastActive(ZonedDateTime.now().plusMinutes(getRandomInt(0, 1000)))
                    .type("Name")
                    .build();

            nameList.add(name);
        }

        //Make one name as active
        int activeIndex = getRandomInt(0, n);
        nameList.get(activeIndex).setActive(true);

        return nameList;
    }

    List<Address> getRandomAddressList() {
        List<Address> addressList = new ArrayList<>();
        Address address = null;
        int n = getRandomInt(1, 11);

        for(int i = 0;i < n;i++) {
            address = Address.builder()
                    .line1(faker.bothify("### ???????????? ??????"))
                    .line2(faker.bothify("### ???????????? ??????"))
                    .line3(faker.bothify("### ???????????? ??????"))
                    .line4(faker.bothify("### ???????????? ??????"))
                    .line5(faker.bothify("### ???????????? ??????"))
                    .line6(faker.bothify("### ???????????? ??????"))
                    .line7(faker.bothify("### ???????????? ??????"))
                    .line8(faker.bothify("### ???????????? ??????"))
                    .line9(faker.bothify("### ???????????? ??????"))
                    .line10(faker.bothify("### ???????????? ??????"))
                    .lastActive(ZonedDateTime.now().plusMinutes(getRandomInt(0, 1000)))
                    .type("Address")
                    .build();

            addressList.add(address);
        }

        //Make one address as active
        int activeIndex = getRandomInt(0, n);
        addressList.get(activeIndex).setActive(true);

        return addressList;
    }

    /**
     * It gives random value between min to max
     * @param min - inclusive
     * @param max - exclusive
     * @return
     */
    int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
