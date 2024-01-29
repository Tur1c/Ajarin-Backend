package co.id.ajarin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRepository {

    private String errorCode;
 
    private String message;

    protected Integer httpCode;

}
