package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//Group is reserved keyword of sql, so change the entity name
@Entity(name = "splitwise_groups")
public class Group extends BaseModel {
    private String name;
    private String description;
    @ManyToOne
    private User admin;
    @ManyToMany
    private List<User> memberList;
    @OneToMany(mappedBy = "group") //group is attribute name here not a class name...See Expense.java
    private List<Expense> expenseList;

}

/*
Group : Admin(User is also a admin)

   1  :  1
   m  :  1
   -------
   m  :  1
   -------

Group  :  User
    1  :  m
    m  :  1
    -------
    m  :  m
    -------

Group  :  Expense
   1   :   m
   1   :   1
   ---------
   1   :   m
   ---------
 */
