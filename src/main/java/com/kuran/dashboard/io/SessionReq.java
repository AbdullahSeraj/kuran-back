package com.kuran.dashboard.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionReq {
    private String name;
    private String centerId;
    private String teacherId;
}
