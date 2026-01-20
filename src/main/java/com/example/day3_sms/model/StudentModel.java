package com.example.day3_sms.model;

import lombok.AllArgsConstructor; //jitne bhi parameter h usn constructor apne aap
import lombok.Data; //getter, setter , to string typs method
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="students")
public class StudentModel {

    @Id
    private String id;
    private String name;
    private int age;
    private String email;

}
