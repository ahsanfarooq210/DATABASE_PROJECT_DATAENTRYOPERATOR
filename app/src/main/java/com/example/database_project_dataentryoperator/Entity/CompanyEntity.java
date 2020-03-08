package com.example.database_project_dataentryoperator.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "company")
public class CompanyEntity
{
    @PrimaryKey
    private int id;

    @ColumnInfo(name="company_name")
    private String name;

    public CompanyEntity()
    {
    }

    public CompanyEntity(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
