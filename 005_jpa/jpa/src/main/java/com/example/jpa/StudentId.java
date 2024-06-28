package com.example.jpa;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class StudentId implements Serializable {
    @Column private Integer grade; // 학년
    @Column private Integer cls; // 반(class)
    @Column private Integer number; // 번호
}
