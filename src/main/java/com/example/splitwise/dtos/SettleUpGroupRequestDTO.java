package com.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleUpGroupRequestDTO {
    //usually we use the id here because from the id (whatever id) we can get the respective object from the database
    private Long groupId;
}
