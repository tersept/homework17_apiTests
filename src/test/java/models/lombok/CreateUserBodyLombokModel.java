package models.lombok;

import lombok.Data;

@Data
public class CreateUserBodyLombokModel {

    private String name,
            job,
            email,
            password;

}
