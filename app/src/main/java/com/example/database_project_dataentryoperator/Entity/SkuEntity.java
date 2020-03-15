package com.example.database_project_dataentryoperator.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class SkuEntity
{
    @PrimaryKey
    private int id;

    //@ForeignKey(parentColumns = )//
    //private int company_id;
}
