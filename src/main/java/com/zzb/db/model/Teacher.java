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
public class Teacher implements Serializable {
    private String tId;

    private String tName;

    private static final long serialVersionUID = 1L;
}