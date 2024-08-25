package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private String description;
    private int amount;
    @ManyToOne
    private User createdBy;
    //say, restaurant expense, we come to know who
    //are all users paid for this expense(paid by) and who are all users owing the money (had to pay)
    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> expenseUserList;

    @ManyToOne
    private Group group;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
}

/*
   private User createdBy
   This user is created the Expense, so, at a time 1 expense will be created by 1 user only but 1 user will do the multiple expense.
Expense : User
     1  :  1 => one particular expense created by 1 user only
     m  :  1 => one user can create multiple expense
     -------
     m  :  1
     -------
 */
/*
Expense : ExpenseUser
     1  :  m => one expense have  multiple expenseUser
     1  :  1 => one ExpenseUser will have one particular expense only
     -------
     1  :  m
     -------
 */
/*
Expense  :  Group
     1   :   1 one particular expense will be a part of one particular group
     m   :   1 one group can have multiple expense
     ---------
     m   :   1
     ---------
 */
