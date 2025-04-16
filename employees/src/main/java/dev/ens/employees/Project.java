package dev.ens.employees;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    private String name;
    private String customer;
}
