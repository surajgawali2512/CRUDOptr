package com.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialsConfig {
    private   String Uname;
    private   String password;
    private   String MySqlUrl;
    public  CredentialsConfig(@Value("spring.datasource.username")String Uname,
                            @Value("spring.datasource.password")String password,
                            @Value("spring.datasource.url")String MySqlUrl){
        this.Uname=Uname;
        this.password=password;
        this.MySqlUrl=MySqlUrl;

    }


}
