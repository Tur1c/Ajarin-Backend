package co.id.ajarin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapperModel<T extends OutputRepositoryModel> extends BaseResponseWrapperModel {
    
    protected T outputSchema;
    
}
