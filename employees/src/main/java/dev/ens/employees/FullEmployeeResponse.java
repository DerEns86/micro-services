package dev.ens.employees;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullEmployeeResponse {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String position;
    private List<Project> projects;
}
