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
public class Score implements Serializable {
    private String sId;

    private String cId;

    private Integer sScore;

    private static final long serialVersionUID = 1L;
}