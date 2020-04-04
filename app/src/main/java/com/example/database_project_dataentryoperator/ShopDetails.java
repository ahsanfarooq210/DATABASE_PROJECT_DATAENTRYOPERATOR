package com.example.database_project_dataentryoperator;

import com.google.android.gms.maps.model.LatLng;

public class ShopDetails
{
    private String id;
    private LatLng latLng;
    private String adress,ownerName,ownerCnic,shopName,ownerMobile;


    public ShopDetails(String id, LatLng latLng, String adress, String ownerName, String ownerCnic, String shopName, String ownerMobile)
    {
        this.id = id;
        this.latLng = latLng;
        this.adress = adress;
        this.ownerName = ownerName;
        this.ownerCnic = ownerCnic;
        this.shopName = shopName;
        this.ownerMobile = ownerMobile;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public LatLng getLatLng()
    {
        return latLng;
    }

    public void setLatLng(LatLng latLng)
    {
        this.latLng = latLng;
    }

    public String getAdress()
    {
        return adress;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOwnerCnic()
    {
        return ownerCnic;
    }

    public void setOwnerCnic(String ownerCnic)
    {
        this.ownerCnic = ownerCnic;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getOwnerMobile()
    {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile)
    {
        this.ownerMobile = ownerMobile;
    }
}
