package co.id.ajarin.model.account;

import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginModel extends OutputRepositoryModel {
    
    private String email;
    private String password;
    
}
