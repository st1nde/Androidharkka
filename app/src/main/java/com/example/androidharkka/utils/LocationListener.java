package com.example.androidharkka.utils;

import android.location.Address;
import android.location.Geocoder;

import java.util.List;
import java.util.Locale;

public class LocationListener {

    Geocoder geocoder;
    List<Address> addresses;
    Locale finnish = new Locale("fi", "FI");

    String address = addresses.get(0).getAddressLine(0); // getAddressLine returns a line of the address
    // numbered by the given index
    String city = addresses.get(0).getLocality();
    String country = addresses.get(0).getCountryName();
    String postalCode = addresses.get(0).getPostalCode();
}
