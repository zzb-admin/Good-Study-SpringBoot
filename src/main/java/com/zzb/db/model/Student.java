package com.zzb.db.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private String sId;

    private String sName;

    private String sBirth;

    private String sSex;

    private static final long serialVersionUID = 1L;
}