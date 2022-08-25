package com.lite.common.dto.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenWrapper {

    private JwtToken access;

    private JwtToken refresh;

}
