package com.completablefuture.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("EMPLOYEE")
@JsonInclude(JsonInclude.Include.NON_NULL) //remove null value from json to reduce bandwidth
public class Employee implements Serializable {
    @Id
    private Long id;
    private String name;
    private Integer empAge;
    private Long favouriteMovieId;
}
