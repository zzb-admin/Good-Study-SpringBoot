package com.zzb.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {
    private String cId;

    private String cName;

    private String tId;

    private static final long serialVersionUID = 1L;
}